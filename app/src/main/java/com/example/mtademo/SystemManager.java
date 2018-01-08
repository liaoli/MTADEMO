package com.example.mtademo;

import android.content.Context;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/12/15.
 */

public class SystemManager {
    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath(); RootCommand(apkRoot);
     * @return 应用程序是/否获取Root权限
     */
    public static boolean RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }

    public static boolean getRoot(Context activity) {
        String apkRoot = "chmod 777 " + activity.getPackageCodePath();
        return SystemManager.RootCommand(apkRoot);
    }


    public static int enableAdbTcpipListening(int port) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("su");
        DataOutputStream dos = new DataOutputStream((OutputStream) process.getOutputStream());
        dos.writeBytes("setprop service.adb.tcp.port " + port + "\n");
        dos.flush();
        dos.writeBytes("stop adbd\n");
        dos.flush();
        dos.writeBytes("start adbd\n");
        dos.flush();
        dos.writeBytes("exit\n");
        dos.flush();
        process.waitFor();
        return process.exitValue();
    }

}
