package com.kangwencai.common.net_retrofit.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kangwencai.common.utils.LogUtils;


import java.io.IOException;

/**
 * Created by kangwencai on 2016/11/10.
 * 请求返回的数据会先通过这个类从String合成json，所以可以通过这个工具将有效数据和标志数据分开来
 */
public class ItemTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);
                //TODO: 不同的公司封装的数据格式不一样，这个拦截器对应的是ApiResponse，如果是其他的则要修改格式
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
//                    LogUtils.e("返回数据是一个json数据" + jsonObject.toString());
                    //当后台结果的result返回为null的时候，这个时候不能转为jsonobject就会当作服务器返回异常
                    if (jsonObject.has("result") && jsonObject.get("result").toString().equalsIgnoreCase("null")) {
                        jsonObject.remove("result");
//                        LogUtils.e("返回数据，运行到这里" + jsonObject.toString());
                        return delegate.fromJsonTree(elementAdapter.fromJson(jsonObject.toString()));

                    } else {
                        JsonElement jsonElement02 = elementAdapter.fromJson(jsonObject.toString());
                        if (jsonElement02.isJsonObject()) {
                            LogUtils.e("返回的数据是json格式：" + jsonObject.toString());
                        } else {
                            LogUtils.e("返回的数据不是json格式：");
                        }
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
