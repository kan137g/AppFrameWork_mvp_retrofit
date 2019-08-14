package com.vanke.commonlib.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: 根据values/dimens.xml, 自动计算比例并生成不同分辨率的dimens.xml
 * 注意用dp和sp，不要用dip，否则生成可能会出错；xml值不要有空格
 * Author: kangwencai
 * DATA: Date on 2018/6/19.
 * PS: Not easy to write code, please indicate.
 */
public class DimenTool {

    public static void gen() {


//添加分辨率
        StringBuilder ldpiContent = new StringBuilder();
        StringBuilder mdpiContent = new StringBuilder();
        StringBuilder hdpiContent = new StringBuilder();
        StringBuilder hdpi_960_540Content = new StringBuilder();
        StringBuilder xhdpiContent = new StringBuilder();
        StringBuilder xhdpi_960_640Content = new StringBuilder();
        StringBuilder xhdpi_1280_720Content = new StringBuilder();
        StringBuilder xxhdpi_1812_1080Content = new StringBuilder();
        StringBuilder xxhdpi_1920_1080Content = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");
            String tempString;
            tempString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<resources>";
            String start = "<dimen name=\"px_";
            String end = "dp</dimen>";

            ldpiContent.append(tempString).append("\n");
            mdpiContent.append(tempString).append("\n");
            hdpiContent.append(tempString).append("\n");
            hdpi_960_540Content.append(tempString).append("\n");

            xhdpiContent.append(tempString).append("\n");
            xhdpi_960_640Content.append(tempString).append("\n");
            xhdpi_1280_720Content.append(tempString).append("\n");
            xxhdpi_1812_1080Content.append(tempString).append("\n");

            xxhdpi_1920_1080Content.append(tempString).append("\n");
            //生成从0～1024的DP对照表
            for (int i = 0; i <= 1024; i++) {

                ldpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                mdpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                hdpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                hdpi_960_540Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xhdpiContent.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xhdpi_960_640Content.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                xhdpi_1280_720Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xxhdpi_1812_1080Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xxhdpi_1920_1080Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");


            }
            start = "<dimen name=\"px_text_";
            end = "sp</dimen>";

            //生成从0～50的SP对照表
            for (int i = 0; i <= 60; i++) {

                ldpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                mdpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                hdpiContent.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                hdpi_960_540Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xhdpiContent.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xhdpi_960_640Content.append(start).append(i + "\">").append((i * 0.5)).append(end).append("\n");
                xhdpi_1280_720Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xxhdpi_1812_1080Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");
                xxhdpi_1920_1080Content.append(start).append(i + "\">").append((i * 0.5625)).append(end).append("\n");


            }


            tempString = "</resources>";
            ldpiContent.append(tempString).append("\n");
            mdpiContent.append(tempString).append("\n");
            hdpiContent.append(tempString).append("\n");
            hdpi_960_540Content.append(tempString).append("\n");
            xhdpiContent.append(tempString).append("\n");
            xhdpi_960_640Content.append(tempString).append("\n");
            xhdpi_1280_720Content.append(tempString).append("\n");
            xxhdpi_1812_1080Content.append(tempString).append("\n");


            xxhdpi_1920_1080Content.append(tempString).append("\n");


//            System.out.println("<!--  sw480 -->");
//            System.out.println(sw480);
//            System.out.println("<!--  sw600 -->");
//            System.out.println(sw600);
//
//            System.out.println("<!--  sw720 -->");
//            System.out.println(sw720);
//            System.out.println("<!--  sw800 -->");
//            System.out.println(sw800);

            String ldpi = "./app/src/main/res/values-ldpi/dimens.xml";
            String mdpi = "./app/src/main/res/values-mdpi/dimens.xml";
            String hdpi = "./app/src/main/res/values-hdpi/dimens.xml";
            String hdpi_960_540 = "./app/src/main/res/values-hdpi-960x540/dimens.xml";
            String xhdpi = "./app/src/main/res/values-xhdpi/dimens.xml";
            String xhdpi_960_640 = "./app/src/main/res/values-xhdpi-960x640/dimens.xml";
            String xhdpi_1280_720 = "./app/src/main/res/values-xhdpi-1280x720/dimens.xml";
            String xxhdpi_1812_1080 = "./app/src/main/res/values-xxhdpi-1812x1080/dimens.xml";
            String xxhdpi_1920_1080 = "./app/src/main/res/values-xxhdpi-1920x1080/dimens.xml";
//            values-hdpi-960x540
            writeFile(ldpi, ldpiContent.toString());
            writeFile(mdpi, mdpiContent.toString());
            writeFile(hdpi, hdpiContent.toString());
            writeFile(hdpi_960_540, hdpi_960_540Content.toString());
            writeFile(xhdpi, xhdpiContent.toString());
            writeFile(xhdpi_960_640, xhdpi_960_640Content.toString());
            writeFile(xhdpi_1280_720, xhdpi_1280_720Content.toString());
            writeFile(xxhdpi_1812_1080, xxhdpi_1812_1080Content.toString());
            writeFile(xxhdpi_1920_1080, xxhdpi_1920_1080Content.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            File realFile = new File(file);
            String str = realFile.getParent();
            realFile = new File(str);
            if (!realFile.exists()) {
                realFile.mkdirs();

            }
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }

    public static void main(String[] args) {
        gen();
    }
}