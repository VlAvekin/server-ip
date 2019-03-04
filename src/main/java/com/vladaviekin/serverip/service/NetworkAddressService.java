package com.vladaviekin.serverip.service;

import com.vladaviekin.serverip.model.NetworkAddressModel;
import com.vladaviekin.serverip.model.NetworkConnectModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@Service
public class NetworkAddressService {

    private Enumeration<NetworkInterface> nets;
    private List<NetworkAddressModel> networkAddressModelList;
    private NetworkAddressModel networkAddressModel;

    public NetworkAddressService() throws SocketException {
        this.nets =  NetworkInterface.getNetworkInterfaces();
        this.networkAddressModelList = new ArrayList<>();
    }

    public NetworkAddressModel name(String name) throws IOException, InterruptedException {
        List<NetworkAddressModel> networkAddressModels = allNotNull();
        for (NetworkAddressModel nm: networkAddressModels) {
            if (nm.getName().equals(name)){
                return nm;
            }
        }
        return null;
    }

    public List<NetworkAddressModel> all() throws SocketException {
        for (NetworkInterface netint : Collections.list(nets)){
            NetworkAddressModel network = network(netint);
            networkAddressModelList.add(network);
        }
        return networkAddressModelList;
    }

    public List<NetworkAddressModel> allNotNull() throws IOException, InterruptedException {
        for (NetworkInterface netint : Collections.list(nets)){
            NetworkAddressModel network = network(netint);
            String addressInet4 = network.getAddressInet4();

            if (addressInet4 != null &&
                    !network.getAddressInet4().equals("127.0.0.1")){

                NetworkConnectModel address = new NetworkConnectService().address(addressInet4);

                if (address != null) {
                    network.setNetworkConnect(address);

                    networkAddressModelList.add(network);
                }
            }
        }
        return networkAddressModelList;
    }

    public List<String> netNamesNotNull() throws IOException, InterruptedException {
        return getNetName(allNotNull());
    }

    public List<String> netNames() throws SocketException {
        return getNetName(all());
    }

    private List<String> getNetName(List<NetworkAddressModel> networkAddressModels) throws SocketException {
        List<String> list = new ArrayList<>();
        for (NetworkAddressModel nm : networkAddressModels) {
            list.add(nm.getName());
        }

        return list;
    }

    private NetworkAddressModel network(NetworkInterface netint) {

        this.networkAddressModel = new NetworkAddressModel();

        networkAddressModel.setDisplayName(netint.getDisplayName());
        networkAddressModel.setName(netint.getName());

        List<String> list = new ArrayList<>();
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            String substring = String.valueOf(inetAddress).substring(1);

            if (substring.indexOf(".") != -1){
                networkAddressModel.setAddressInet4(substring);
            }
            if (substring.indexOf(":") != -1){
                networkAddressModel.setAddressInet6(substring);
            }

            list.add(substring);
        }

        networkAddressModel.setInetAddress(list);
        return networkAddressModel;
    }
}
