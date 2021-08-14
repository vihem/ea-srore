package com.ea.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NetUtils {
    public static void checkPort(int port){
        if (!NetUtil.isUsableLocalPort(port)){
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
    }

    public static int choosePort(int defaultPort){
        int port;
        Future<Integer> future = ThreadUtil.execAsync(()->{
            int res;
            Scanner sc = new Scanner(System.in);
            while (true){
                String strPort = sc.nextLine();
                if(!NumberUtil.isInteger(strPort)){
                    System.err.println("只能是数字");
                } else {
                    res = Convert.toInt(strPort);
                    sc.close();
                    break;
                }
            }
            return res;
        });
        try {
            port = future.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            port = defaultPort;
        }
        checkPort(port);
        System.out.println("----------------"+port);
        return port;
    }
}
