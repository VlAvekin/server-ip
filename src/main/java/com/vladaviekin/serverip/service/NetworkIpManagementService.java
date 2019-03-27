package com.vladaviekin.serverip.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NetworkIpManagementService {

    private final String CONNECT_IP = "iptables -I FORWARD -s %s -i wlan0 -j ACCEPT;";
    private final String DISCONNECT_IP = "iptables -I FORWARD -s %s -i wlan0 -j DROP;";
    private final String SAVE = "service iptables save;";

    public void connect(final String ip) throws IOException, InterruptedException {
        CommandExecutionUtils.run(String.format(CONNECT_IP, ip));
    }

    public void disconnect(final String ip) throws IOException, InterruptedException {
        CommandExecutionUtils.run(String.format(DISCONNECT_IP, ip));
    }
}
