package com.vladaviekin.serverip.model;

import lombok.Data;

import java.util.Objects;

@Data
public class AddressConnectModel {

    private String internetAddress;
    private String physicalAddress;
    private String type;

    public AddressConnectModel() {
    }

}
