package com.vanke.handlecashregister.utils;



import com.vanke.handlecashregister.bean.GoodsInfo;

import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: Copyright (c) 2017
 * Company: www.kuaidijin.com
 *
 * @author kangwencai
 * @version 1.0
 * @date 2019/8/13
 */
public class CalculateAmountTools {

    public static Map<String, String> calculate(List<GoodsInfo> goodsInfos) {
        double total = 0, undscAmount = 0, dscAmount = 0;

        for (GoodsInfo info : goodsInfos) {
            total += parseDouble(info.getPrice()) * parseDouble(info.getQuantity());
            dscAmount += parseDouble(info.getDscPrice()) * parseDouble(info.getQuantity());

//            dscAmount += parseDouble(info.getTotalDsc());
        }
        undscAmount = total - dscAmount;
        Map<String, String> result = new HashMap<String, String>();
        result.put("total", String.valueOf(total));
        result.put("undscAmount", String.valueOf(undscAmount));
        result.put("dscAmount", String.valueOf(dscAmount));

        System.out.println("总金额：" + total + "  折扣后金额：" + dscAmount + "    减免金额：" + undscAmount);
        return result;
    }

    private static double parseDouble(String number) {

        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
