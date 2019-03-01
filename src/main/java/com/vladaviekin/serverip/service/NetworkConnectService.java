package com.vladaviekin.serverip.service;

import com.vladaviekin.serverip.model.AddressConnectModel;
import com.vladaviekin.serverip.model.NetworkConnectModel;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkConnectService {

    private List<NetworkConnectModel> netConnectList = new ArrayList<>();

    public NetworkConnectModel address(String ip) {
        List<NetworkConnectModel> all = all();
        for (NetworkConnectModel ncm: all) {
            if (ncm.getAddress().equals(ip)){
                return ncm;
            }
        }
        return null;
    }

    public List<NetworkConnectModel> all() {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("arp -a");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            NetworkConnectModel ncm = null;
            String address = "";

            while ((s = br.readLine()) != null) {

                if ((s.indexOf("Interface") != -1)) {
                    int begin = s.indexOf(":") + 2;
                    int ent = s.indexOf("-") - 1;
                    address = s.substring(begin, ent);

                    if (ncm != null){
                        netConnectList.add(ncm);
                    }

                    ncm = new NetworkConnectModel();
                    ncm.setAddress(address);
                } else if ((s.equals("  Internet Address      Physical Address      Type"))) {
                    //
                } else if (!s.equals("")) {
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
            p.waitFor();
            p.destroy();
        } catch (Exception e) {}

        return netConnectList;
    }

    private String addressIp4(String address) {
        String[] result = getAddressStrings(address);
        return result[3];
    }

    private String addressIp3(String address) {
        String[] result = getAddressStrings(address);
        return result[2];
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
