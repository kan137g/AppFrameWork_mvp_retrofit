package com.vanke.commonlib.net;


import com.alibaba.fastjson.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanke.commonlib.net.cookie.CookieJarImpl;
import com.vanke.commonlib.net.cookie.store.MemoryCookieStore;
import com.vanke.commonlib.net.rest.HttpsUtils;
import com.vanke.commonlib.net.rest.ItemTypeAdapterFactory;


import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by kangwencai on 2016/11/9.
 */

public class RetrofitUtil {
    public static String BASE_URL = "http://kms.dev.cn/";
    //    public static APIManagerService apiManagerService;
    private static Retrofit retrofit;

//    public RetrofitRest() {
//        apiManagerService = getInstance().create(APIManagerService.class);
//    }

    public static synchronized Retrofit getInstance() {
        if (retrofit == null) {
            changeBaseUrl(BASE_URL);
        }

        return retrofit;
    }

    public static void changeBaseUrl(String domain) {
        BASE_URL = domain;
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                    .addConverterFactory(StringconverterF)
                .client(initClient())
                .build();
    }

    /**
     * 初始化证书和client
     */
    private static OkHttpClient initClient() {
//        InputStream[] streams = new InputStream[1];
//        String CER_EXPRESS_GOLD = "-----BEGIN CERTIFICATE REQUEST-----\n" +
//                "MIIBrjCCARcCAQAwbjELMAkGA1UEBhMCVVMxDTALBgNVBAgMBE1hcnMxEzARBgNV\n" +
//                "BAcMCmlUcmFuc3dhcnAxEzARBgNVBAoMCmlUcmFuc3dhcnAxEzARBgNVBAsMCmlU\n" +
//                "cmFuc3dhcnAxETAPBgNVBAMMCCouZGV2LmNuMIGfMA0GCSqGSIb3DQEBAQUAA4GN\n" +
//                "ADCBiQKBgQDNNr8BDAY2hU7XQBvmT0HOX3AkbWk6U1Qbw942sKH+vn9IdGBaM2jd\n" +
//                "oaGZd1VEHhcxMuForb6GA1vvS+/zv78mn4nDMt/HvBZmAZV2gSyxmw631zYkQcpv\n" +
//                "xNkUhBWI0yu/UBofp0pEUiKgakPYxBS6Fv+HLJ8PxlsysWTj6oLJ8wIDAQABoAAw\n" +
//                "DQYJKoZIhvcNAQELBQADgYEAOdbQLwh1FPLIOtii6eIWKynaRvzN7HD+t1A1/ixG\n" +
//                "JgkXUdbpWalwwHZqGwHcokMNsiq8KREU3h44p4Wz4htPQ16N+a0Z9qu6a02kJ8mJ\n" +
//                "JHYiYc5gEXnOl6UNlG4k8d7EH7NLS0Z/+3uBg3qvfReIqZCZ0D9TeZXt7wWNv9ql\n" +
//                "pfk=\n" +
//                "-----END CERTIFICATE REQUEST-----\n";
//        streams[0] = new Buffer().writeUtf8(CER_EXPRESS_GOLD).inputStream();
//
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(streams, null, null);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());

        return new OkHttpClient.Builder()
                .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                .readTimeout(20000L, TimeUnit.MILLISECONDS)
                //cookie加不加没那么重要
                .cookieJar(cookieJar1)
//                .hostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session) {
//                        return true;
//                    }
//                })
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                添加可信任网点的域名和公钥hash码
//                .certificatePinner(new CertificatePinner.Builder()
//                        .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
//                        .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
//                        .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
//                        .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
//                        .build())
                .build();
    }


}
