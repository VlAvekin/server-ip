package com.vladaviekin.serverip.service;

import com.vladaviekin.serverip.model.AddressConnectModel;
import com.vladaviekin.serverip.model.NetworkConnectModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkConnectService {

    private List<NetworkConnectModel> netConnectList = new ArrayList<>();

    public NetworkConnectModel address(String ip) throws IOException, InterruptedException {
        List<NetworkConnectModel> all = all();
        for (NetworkConnectModel ncm: all) {
            if (ncm.getAddress().equals(ip)){
                return ncm;
            }
        }
        return null;
    }

    public List<NetworkConnectModel> all() throws IOException, InterruptedException {

        List<String> list = CommandExecutionUtils.run("arp -a");

        NetworkConnectModel ncm = null;
        String address = "";

        for (String s: list) {
            if ((s.indexOf("Interface") != -1)) {
                int begin = s.indexOf(":") + 2;
                int ent = s.indexOf("-") - 1;
                address = s.substring(begin, ent);

                if (ncm != null){
                    netConnectList.add(ncm);
                }

                ncm = new NetworkConnectModel();
                ncm.setAddress(address);
            }
            else if ((s.equals("  Internet Address      Physical Address      Type"))) {
                //
            }
            else if (!s.equals("")) {
                String[] a = s.split(" ");
                AddressConnectModel acm = new AddressConnectModel();
                getAcm(a, acm);

                if (addressIp3(address).equals(addressIp3(acm.getInternetAddress())) &&
                        !addressIp4(acm.getInternetAddress()).equals("255") &&
                        !addressIp4(acm.getInternetAddress()).equals("0")) {
                    ncm.addAddress(acm);
                }
            }
        }

        return netConnectList;
    }


    private String addressIp4(String address) {
        String[] result = getAddressStrings(address);
        return result[3];
    }

    private String addressIp3(String address) {
        String[] result = getAddressStrings(address);
        return result[0] + result[1] + result[2];
    }

    private String[] getAddressStrings(String address) {
        String res = address.replace('.', '-');
        return res.split("-");
    }

    private void getAcm(String[] a, AddressConnectModel acm) {
        for (String aa: a) {
            if (!aa.equals("")){
                if ((aa.indexOf(".") != -1)) {
                    acm.setInternetAddress(aa);
                } else if ((aa.indexOf("-") != -1)) {
                    acm.setPhysicalAddress(aa);
                } else {
                    acm.setType(aa);
                }
            }
        }
    }

}