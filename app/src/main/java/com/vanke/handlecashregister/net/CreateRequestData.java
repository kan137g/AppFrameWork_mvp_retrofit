package com.vanke.handlecashregister.net;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.vanke.handlecashregister.Constants;
import com.vanke.handlecashregister.bean.GoodsInfo;
import com.vanke.handlecashregister.encrypt.RSAService;
import com.vanke.handlecashregister.utils.CalculateAmountTools;
import com.vanke.handlecashregister.utils.DateFormatUtils;
import com.vanke.handlecashregister.utils.OutTradeNo;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/12
 */
public class CreateRequestData {

    public static JSONObject packageData(JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("data", data);
        result.put("sign", "1E25A523DD1DE5EBAEB291667A6C0AED");
        result.put("timestamp", System.currentTimeMillis() / 1000);
        return result;
    }

    /**
     * {
     * "accDate": "2019-08-08",
     * "cdKey": "11111111",
     * "cshCode": "",
     * "orgCode": "1001003",
     * "posNo": "12",
     * "wuuid": "18:bb:26:a5:41:ed"
     * }
     *
     * @return
     */
    public static JSONObject getBeginParams() {


        JSONObject data = new JSONObject();

        data.put("accDate", DateFormatUtils.formatDate());
        data.put("cdKey", "11111111");
        data.put("cshCode", "");
        data.put("orgCode", "1001003");
        data.put("posNo", "12");
        data.put("wuuid", "18:bb:26:a5:41:ed");


        return packageData(data);
    }

    /**
     * {
     * "orgCode": "1001003",
     * "pluCode": "000964",
     * "posNo": "12",
     * "saleNo": "HG4ivYcoxThs/ORvUy33Yx8u5fhSNZf+WNyMnIEeLcvQyWKE71OSCaOVYZ1ueXfj4gVnDuZW5CUpV4aeR/HN2O9SCbKCmfFPgWfbZ7LMZ5kgnTF5rvqhtsd24HeWeCDw+okq4UTa1l0jciaUOWsbQKGLe72nBVmWc+3hhXyh/2Q=",
     * "shopCode": "01"
     * }
     *
     * @return
     */
    public static JSONObject getQueryAndAddParams(String saleNo) {
        JSONObject data = new JSONObject();
        data.put("orgCode", "1001003");
        data.put("pluCode", "000964");
        data.put("posNo", "12");
        data.put("saleNo", RSAService.CRM_RSA.encrypt(saleNo));
        data.put("shopCode", "01");
        return packageData(data);
    }


    public static JSONObject getTotalReductionParams(List<GoodsInfo> goodsInfos) {

        JSONObject data = new JSONObject();
        data.put("cardType", "10");
        data.put("orgCode", "1001003");
        data.put("posNo", "12");
        data.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));
        data.put("saleTime", DateFormatUtils.formatDateTime());

        data.put("plu", createPlu(goodsInfos));
        return packageData(data);
    }

    public static JSONObject getPayPayParams(List<GoodsInfo> goodsInfos) {
        JSONObject data = new JSONObject();
        // TODO:这个要扫码之后填入
        data.put("authCode", RSAService.CPM_RSA.encrypt(Constants.payCode));
        // 这个为空
        data.put("billNo", RSAService.CPM_RSA.encrypt(""));

        Map<String, String> map = CalculateAmountTools.calculate(goodsInfos);
        data.put("total", RSAService.CPM_RSA.encrypt(map.get("total")));
        data.put("dscAmount", RSAService.CPM_RSA.encrypt(map.get("dscAmount")));
        data.put("undscAmount", RSAService.CPM_RSA.encrypt(map.get("undscAmount")));


        data.put("cshCode", "9902");
        data.put("posNo", "12");
        data.put("orgCode", Constants.orgCode);
        data.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));


        long date = System.currentTimeMillis();
        data.put("saleTime", DateFormatUtils.formatDateTime(date));
        data.put("outTradeNo", RSAService.CPM_RSA.encrypt(OutTradeNo.createTradNo(date, Constants.orgCode)));

        data.put("payMethod", "$wx");
        data.put("scene", "bar_code");
        data.put("subject", "微信支付");
        data.put("terminalId", "");
        data.put("terminalParams", "");

        data.put("plu", createPlu(goodsInfos));
        return packageData(data);

    }


    /**
     * @param jsonObject {
     *                   "totalDscSelf": "0",
     *                   "saleTime": "2019-08-13 15:42:09",
     *                   "total": "0.01",
     *                   "tradeNo": "4200000399201908137109150503",
     *                   "outTradeNo": "2019081315420810010035836",
     *                   "buyerUserId": "",
     *                   "totalDscChannel": "0",
     *                   "totalRecv": "0.01",
     *                   "buyerLogonId": ""
     *                   }
     * @return
     */
    public static JSONObject getSalePayParams(JSONObject jsonObject) {

        JSONObject data = new JSONObject();
        data.put("cshCode", "");
        data.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));

        data.put("pay", createPay(jsonObject));
        return packageData(data);

    }

    public static JSONObject getSubmitParams(List<GoodsInfo> goodsInfos, JSONObject jsonObject) {
        JSONObject data = new JSONObject();


        data.put("dataType", 0);
        data.put("pluDsc", new JSONArray());
        data.put("plu", createPlu(goodsInfos));

        JSONArray pay = new JSONArray();
        pay.add(createPay(jsonObject));
        data.put("pay", pay);

        JSONArray sale = new JSONArray();
        sale.add(createSale(jsonObject));
        data.put("sale", sale);
        return packageData(data);
    }


    private static JSONArray createPlu(List<GoodsInfo> goodsInfos) {
        JSONArray plu = new JSONArray();


        for (GoodsInfo info : goodsInfos) {
            JSONObject tempJS = new JSONObject();

            tempJS.put("barCode", info.getBarCode());
            tempJS.put("classCode", info.getClassCode());
            // 支付接口需要的
            tempJS.put("itemNo", 1);
            tempJS.put("pageNo", 1);
            tempJS.put("pluName", info.getPluName());
            tempJS.put("pluCode", RSAService.CPM_RSA.encrypt(info.getPluCode()));
            tempJS.put("price", RSAService.CPM_RSA.encrypt(info.getPrice()));
            tempJS.put("quantity", RSAService.CPM_RSA.encrypt(info.getQuantity()));
            tempJS.put("total", RSAService.CPM_RSA.encrypt(info.getTotal()));
            // 某个接口需要的
            tempJS.put("tagPrice", RSAService.CPM_RSA.encrypt(info.getPrice()));
            tempJS.put("pluId", RSAService.CPM_RSA.encrypt(info.getPluId()));
            tempJS.put("shopCode", info.getShopCode());
            tempJS.put("shopId", info.getShopId());
            tempJS.put("depId", info.getDepId());
            tempJS.put("depCode", info.getDepCode());
            tempJS.put("brandCode", info.getBrandCode());

            // TODO:这些硬编码需要进一步确认
            tempJS.put("clkCode", "9902");
            tempJS.put("clkId", "10000000185");
            tempJS.put("depName", info.getDepName());
            tempJS.put("invNo", "");
            tempJS.put("pluType", 0);
            tempJS.put("remark", "");
            tempJS.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));
            tempJS.put("shopCode", RSAService.CPM_RSA.encrypt(info.getShopCode()));
            tempJS.put("spec", info.getSpec());
            tempJS.put("tagPrice", info.getPrice());

            tempJS.put("totalDsc", RSAService.CPM_RSA.encrypt(info.getTotalDsc()==null ?"":info.getTotalDsc()));
            tempJS.put("totalRecv", RSAService.CPM_RSA.encrypt(info.getTotal()));
            tempJS.put("totalRound", RSAService.CPM_RSA.encrypt("0"));
            tempJS.put("tranType", "1");
            tempJS.put("unit", info.getUnit());
            tempJS.put("orgCode", Constants.orgCode);
            plu.add(tempJS);

        }

        return plu;
    }

    private static JSONObject createPay(JSONObject jsonObject) {

        JSONObject pay = new JSONObject();

        pay.put("orgCode", Constants.orgCode);


        pay.put("payCode", "92");
        pay.put("payCode", RSAService.CPM_RSA.encrypt("92"));
        pay.put("payName", "微信");
        pay.put("remark", "$wx");
        pay.put("serialNo", "0");
        pay.put("udp4", jsonObject.getString("tradeNo"));
        pay.put("payNo", RSAService.CPM_RSA.encrypt("0"));
        pay.put("totalPaid", RSAService.CPM_RSA.encrypt(jsonObject.getString("total")));
        pay.put("totalRecv", RSAService.CPM_RSA.encrypt(jsonObject.getString("totalRecv")));
        pay.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));
        pay.put("tranType", "1");

        return pay;
    }

    /**
     * @param jsonObject {
     *                   "totalDscSelf": "0",
     *                   "saleTime": "2019-08-13 15:42:09",
     *                   "total": "0.01",
     *                   "tradeNo": "4200000399201908137109150503",
     *                   "outTradeNo": "2019081315420810010035836",
     *                   "buyerUserId": "",
     *                   "totalDscChannel": "0",
     *                   "totalRecv": "0.01",
     *                   "buyerLogonId": ""
     *                   }
     * @return
     */
    private static JSONObject createSale(JSONObject jsonObject) {
        JSONObject sale = new JSONObject();
        sale.put("accDate", DateFormatUtils.formatDate());
        if (Constants.userInfo != null) {
            sale.put("cardNo", RSAService.CPM_RSA.encrypt(Constants.userInfo.cardNo));
            sale.put("cardType", "10");

        } else {
            sale.put("cardNo", RSAService.CPM_RSA.encrypt(""));
            sale.put("cardType", "");
        }

        sale.put("cshCode", "9902");
        sale.put("cshId", "10000000185");
        sale.put("orgCode", Constants.orgCode);
        sale.put("posNo", Constants.posNo);
        sale.put("remark", "");
        sale.put("saleDate", DateFormatUtils.formatDateTime());
        sale.put("dataSrc", "3");
        sale.put("tranType", "1");


        sale.put("saleNo", RSAService.CPM_RSA.encrypt(Constants.saleNo));
        sale.put("total", RSAService.CPM_RSA.encrypt(jsonObject.getString("total")));
        // 找零
        sale.put("totalChange", RSAService.CPM_RSA.encrypt("0"));
        sale.put("totalDsc", RSAService.CPM_RSA.encrypt(jsonObject.getString("totalDscChannel")));

        sale.put("totalPaid", RSAService.CPM_RSA.encrypt(jsonObject.getString("total")));
        sale.put("totalRecv", RSAService.CPM_RSA.encrypt(jsonObject.getString("totalRecv")));

        sale.put("totalRound", RSAService.CPM_RSA.encrypt("0"));

        return sale;
    }
}
