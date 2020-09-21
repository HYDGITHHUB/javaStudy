package com.pong.netStudy;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class NetStudy {
    public static void main(String[] args) {
        try {
            //InetAddress 根据 IP 或者域名返回 IP
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);

            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",80);
            System.out.println(inetSocketAddress);
            System.out.println(inetSocketAddress.getAddress());
            System.out.println(inetSocketAddress.getHostName());
            System.out.println(inetSocketAddress.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
