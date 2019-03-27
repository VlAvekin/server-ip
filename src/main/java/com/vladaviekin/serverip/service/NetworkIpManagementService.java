package com.vladaviekin.serverip.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NetworkIpManagementService {

    private final String CONNECT_IP = "iptables -I FORWARD -s 192.168.220.54 -i wlan0 -j ACCEPT;";
    private final String DISCONNECT_IP = "iptables -I FORWARD -s 192.168.220.54 -i wlan0 -j DROP;";
    private final String SAVE = "service iptables save;";

    public void connect() throws IOException, InterruptedException {
        CommandExecutionUtils.run(CONNECT_IP);
    }

    public void disconnect() throws IOException, InterruptedException {
        CommandExecutionUtils.run(DISCONNECT_IP);
    }
}
