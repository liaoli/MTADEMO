package com.example.mtademo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 廖理 on 2018/1/8.
 */

public class NetTimeUtil {

    public static void main(String[] args) {
        String webUrl2 = "http://www.baidu.com";// 百度
        String webUrl3 = "http://www.taobao.com";// 淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
        String webUrl5 = "http://www.360.cn";// 360
        String webUrl6 = "http://www.beijing-time.org";// beijing-time
        String webUrl7 = "http://www.163.com/";// 网易
        String webUrl8 = "https://www.tmall.com/";// 天猫
        System.out.println(getNetworkTime(webUrl2) + " [百度]");
        System.out.println(getNetworkTime(webUrl3) + " [淘宝]");
        System.out.println(getNetworkTime(webUrl4) + " [中国科学院国家授时中心]");
        System.out.println(getNetworkTime(webUrl5) + " [360安全卫士]");
        System.out.println(getNetworkTime(webUrl6) + " [beijing-time]");
        System.out.println(getNetworkTime(webUrl7) + " [网易]");
        System.out.println(getNetworkTime(webUrl8) + " [天猫]");
    }

    public static String getNetworkTime(String webUrl) {
        try {
            URL url = new URL(webUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            System.out.println("dateL = " + dateL);
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
