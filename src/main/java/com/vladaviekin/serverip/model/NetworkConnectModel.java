package com.vladaviekin.serverip.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NetworkConnectModel {

    private String address;
    private List<AddressConnectModel> addressConnectModelList;

    public NetworkConnectModel() {
        this.addressConnectModelList = new ArrayList<>();
    }

    public void addAddress(AddressConnectModel addressConnectModel) {
        this.addressConnectModelList.add(addressConnectModel);
    }

}
