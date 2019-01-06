package com.howie.java.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description socket 聊天: 使用 UDP 协议（DatagramSocket）
 *              Socket是一种基于TCP的套接字，这种方法的使用每个连接需要花费一定的时间，要减少这种开销，
 *              于是有了第二种套接字：自寻址套接字，它是使用UDP发送寻址信息，不同的是他可以发送多IP信息包。
 * @Date 2018-05-03
 * @Time 22:35
 */
public class ChatUDP implements Runnable {
    public static void main(String[] args) {
        new ChatUDP().chat();
    }

    public void chat() {
        //创建读取线程
        new Thread(new ChatUDP()).start();
        //获取键盘录入信息
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取数据
        String line;
        //建立服务
        try (DatagramSocket ds = new DatagramSocket()) {
            while ((line = br.readLine()) != null) {
                //把读取的资源打包
                byte[] buf = line.getBytes();
                // 打包数据并指定IP和端口
                DatagramPacket dp = new DatagramPacket(buf, buf.length,
                        InetAddress.getByName(new NetWork().getLocalHost()),
                        8080);
                //发送出去
                ds.send(dp);
            }
        } catch (Exception e) {
            System.out.println("数据发送失败！");
        } finally {
            //关闭资源
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("关闭资源失败！");
            }
        }
    }

    /**
     * 建立线程，接收 socket 信息
     */
    @Override
    public void run() {
        while (true) {
            //创建服务
            DatagramSocket ds = null;
            DatagramPacket dp;
            try {
                ds = new DatagramSocket(8090);
                //创建缓冲区
                byte[] buf = new byte[1024];
                //接收数据
                dp = new DatagramPacket(buf, buf.length);
                ds.receive(dp);
                String ip = dp.getAddress().getHostName();
                //输出数据到控制台上
                System.out.println(ip + " :" + new String(buf, 0, dp.getLength()));
            } catch (Exception e) {
                System.out.println("出问题了！");
            } finally {
                //关闭资源
                if (ds != null) {
                    ds.close();
                }
            }
        }
    }
}
