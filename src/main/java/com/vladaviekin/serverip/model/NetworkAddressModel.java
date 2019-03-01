package com.vladaviekin.serverip.model;

import lombok.Data;

import java.util.List;

@Data
public class NetworkAddressModel {

    private String displayName;
    private String name;

    private List<String> inetAddress;

    private String addressInet4;
    private String addressInet6;

    private NetworkConnectModel networkConnect;

    public NetworkAddressModel() {
    }
}
