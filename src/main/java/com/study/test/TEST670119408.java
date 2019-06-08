package com.study.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by LiBingyi on 2019/6/9 5:24
 */
public class TEST670119408 {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("www.baidu.com", 80));
        while (!socketChannel.finishConnect()) {
            // 没连接上,则一直等待
            Thread.yield();
        }
        // 发送内容
        String req = "GET http://www.baidu.com HTTP/1.1\r\n" +
                "Host: www.baidu.com\r\n" +
                "User-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36n\r\n" +
                "\r\n";
        ByteBuffer buffer = ByteBuffer.wrap(req.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
        // 读取响应
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (requestBuffer.position() > 0) break;
        }
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        String resp = new String(content);
        String[] arr = resp.split("\r\n");
        String result = "我的QQ号：670119408，我的解析到百度服务器server类型是：";
        if (arr != null && arr.length > 8 && arr[7] != null && arr[7].startsWith("Server:")) {
            System.out.println(result + arr[7]);
        } else if (arr != null) {
            for (String s : arr) {
                if (s.startsWith("Server:")) {
                    System.out.println(result + s);
                }
            }
        }
        socketChannel.close();
    }

}
