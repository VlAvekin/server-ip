package com.vladaviekin.serverip.model;

import lombok.Data;

@Data
public class AddressConnectModel {

    private String name;
    private String internetAddress;
    private String physicalAddress;
    private String type;

    public AddressConnectModel() {
    }

}
