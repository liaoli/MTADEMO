package com.example.mtademo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    //此为提供方的包名
    private static String PREFERENCE_PACKAGE="com.xinhui.upgradeapp";
    public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;

    TextView textView ;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        boolean result = SystemManager.getRoot(this);


     //   textView.setText("Root result = " + result);



//        try {
//            c =this.createPackageContext(PREFERENCE_PACKAGE, Context.CONTEXT_IGNORE_SECURITY);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        sp=c.getSharedPreferences("device_no_pref",MODE);
//        String content=sp.getString("device_no_key","");
//        textView.setText(content);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkhttpManager.getInstance().getNetTime("http://www.baidu.com", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {


                        Headers headers = response.headers();

                        Date date = headers.getDate("Date");


                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Log.e("currentTimeMillis", System.currentTimeMillis()+"");

                        Log.e("data------>","mill = " + date.getTime()  + "===");

                    }
                });

               new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String webUrl2 = "http://www.baidu.com";// 百度
//                        String webUrl3 = "http://www.taobao.com";// 淘宝
//                        String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
//                        String webUrl5 = "http://www.360.cn";// 360
//                        String webUrl6 = "http://www.beijing-time.org";// beijing-time
//                        String webUrl7 = "http://www.163.com/";// 网易
//                        String webUrl8 = "https://www.tmall.com/";// 天猫
                       System.out.println(NetTimeUtil.getNetworkTime(webUrl2) + " [百度]");
                        Log.e("currentTimeMillis", System.currentTimeMillis()+"");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl3) + " [淘宝]");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl4) + " [中国科学院国家授时中心]");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl5) + " [360安全卫士]");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl6) + " [beijing-time]");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl7) + " [网易]");
//                        System.out.println(NetTimeUtil.getNetworkTime(webUrl8) + " [天猫]");
//
                    }
                }).start();
            }

        });

    }
}
