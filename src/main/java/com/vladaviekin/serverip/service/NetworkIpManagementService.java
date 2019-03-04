package com.vladaviekin.serverip.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NetworkIpManagementService {

    private final String CONNECT_IP = "iptables -I INPUT -s 192.168.244.134 -j ACCEPT;";
    private final String DISCONNECT_IP = "iptables -I INPUT -s 192.168.244.134 -j DROP;";
    private final String SAVE = "service iptables save;";

    public void connect() throws IOException, InterruptedException {
        CommandExecutionUtils.run(CONNECT_IP + "\n" + SAVE);
    }

    public void disconnect() throws IOException, InterruptedException {
        CommandExecutionUtils.run(DISCONNECT_IP + "\n" + SAVE);
    }
}
