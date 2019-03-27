package com.vladaviekin.serverip.service;

import com.vladaviekin.serverip.model.AddressConnectModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkConnectService {

    private List<AddressConnectModel> addressConnectlList = new ArrayList<>();

    public List<AddressConnectModel> networkName() throws IOException, InterruptedException {

        allAddressConnect();

        List<AddressConnectModel> addressConnectModels = new ArrayList<>();

                String buf;
        String temp;

        for (AddressConnectModel acm: addressConnectlList) {
            if (acm.getType().equals("wlan0")) {
                addressConnectModels.add(acm);
            }
        }

        return addressConnectModels;
    }

    private List<AddressConnectModel> allAddressConnect() throws IOException, InterruptedException {

        List<String> list = CommandExecutionUtils.run("arp -a");

        String name;
        String internetAddress;
        String physicalAddress;
        String type;

        for (String str: list) {
            if (str != null){
                AddressConnectModel acm = new AddressConnectModel();

                name = str.substring(0, str.indexOf(' '));
                internetAddress = str.substring(str.indexOf('(') + 1, str.indexOf(')'));
                physicalAddress = str.substring(str.indexOf("at ") + 3, str.indexOf(" ["));
                type = str.substring(str.indexOf("on ") + 3, str.length());

                acm.setName(name);
                acm.setInternetAddress(internetAddress);
                acm.setPhysicalAddress(physicalAddress);
                acm.setType(name);

                addressConnectlList.add(acm);
            }
        }

        return addressConnectlList;
    }
}