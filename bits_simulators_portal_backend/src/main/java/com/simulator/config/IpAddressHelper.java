package com.simulator.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddressHelper {

    public static String getIpAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            // Gérer l'exception si l'adresse IP ne peut pas être obtenue
            e.printStackTrace();
            return "Unknown";
        }
    }
}
