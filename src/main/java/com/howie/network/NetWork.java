package com.howie.network;

import java.io.*;
import java.net.*;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-05-01
 * @Time 17:30
 */
public class NetWork {
    public static void main(String[] args) throws IOException, URISyntaxException {
        new NetWork().socket("哈哈哈哈");
    }

    /**
     * 由域名获得 ip 地址
     *
     * @param netAddress 域名 网址
     * @return ip 地址
     */
    public String getIPAddress(String netAddress) throws UnknownHostException {
        //获取InetAddress对象
        InetAddress inetAddress = InetAddress.getByName(netAddress);
        //然后调用InetAddress对象中的getHostAddress()方法获得IP
        return inetAddress.getHostAddress();
    }

    /**
     * 获得本机 ip 地址
     *
     * @return ip 地址
     */
    public String getLocalHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * DatagramSocket 发送信息, 发送从键盘录入的字符
     *
     * @param message 要发送的信息
     * @param host    目标 ip 地址
     * @param port    目标端口
     */
    public void sendMessage(String message, String host, int port) throws IOException {
        //创建Socket服务
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] buf = message.getBytes();
        //把数据转换成字节数据包
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length,
                InetAddress.getByName(host), port);
        //发送数据
        datagramSocket.send(datagramPacket);
        //关闭资源
        datagramSocket.close();
    }

    /**
     * DatagramSocket 获取信息, 接收从键盘录入的字符
     *
     * @param port 当前端口号
     */
    public void getMessage(int port) throws IOException {
        //建立服务监视这个端口
        DatagramSocket datagramSocket = new DatagramSocket(port);
        //定义一个缓冲区用于接收数据
        byte[] buf = new byte[1024];//可以乘以64，因为一个包在64K以内。
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(datagramPacket);
        //获取IP，这是一个习惯，因为要知道这数据是从那里来的
        String ip = datagramPacket.getAddress().getHostAddress();
        System.out.println("来自" + ip + "的数据是");
        //把数据打印出来
        System.out.println(new String(buf, 0, datagramPacket.getLength()));
        //关闭资源
        datagramSocket.close();
    }

    /**
     * socket 发送信息（TCP）
     */
    public void socket(String message) {
        // 创建服务
        Socket s = null;
        try {
            s = new Socket(getLocalHost(), 8080);
            //把数据转换成字节数组
            byte[] buf = message.getBytes();
            //获取输出流
            OutputStream out = s.getOutputStream();
            //发送数据
            out.write(buf);
            //关闭发送流
            s.shutdownOutput();
            //获取输入流，获取反馈信息
            InputStream in = s.getInputStream();
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            //打印反馈信息
            System.out.println(new String(buffer, 0, len));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

        }
    }

    /**
     * socket 接收信息 （TCP）
     */
    public void serverSocket() throws IOException {
        //创建服务
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        String ip = socket.getInetAddress().getHostAddress();
        //输出链接上来的机器
        System.out.println(ip + "-----connected");
        //获取输入流
        InputStream in = socket.getInputStream();
        //读取数据
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) != -1) {
            //打印
            System.out.println(new String(buf, 0, len));
        }
        socket.shutdownInput();
        //发送反馈信息
        OutputStream out = socket.getOutputStream();
        out.write("服务端收到".getBytes());
        //关闭资源
        socket.close();
        serverSocket.close();
    }
}
