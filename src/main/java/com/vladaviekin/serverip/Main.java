package com.vladaviekin.serverip;

import com.vladaviekin.serverip.model.NetworkAddressModel;
import com.vladaviekin.serverip.service.NetworkAddressService;
import com.vladaviekin.serverip.service.NetworkConnectService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        NetworkAddressService networkAddressService = new NetworkAddressService();
        NetworkAddressModel wlan1 = networkAddressService.name("wlan1");

        List<NetworkAddressModel> x = networkAddressService.allNotNull();

        for (NetworkAddressModel nam: x) {
            System.out.println(nam.getNetworkConnect().toString());
        }
//
//        NetworkConnectService networkConnectService = new NetworkConnectService();
//
//        System.out.println(networkConnectService.address(wlan1.getAddressInet4()));
        //System.out.println(networkConnectService.all());


//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("192.168.137.1", 8888));
//        System.out.println(socket.getLocalAddress());


//
//        Enumeration e = NetworkInterface.getNetworkInterfaces();
//        while(e.hasMoreElements())
//        {
//            NetworkInterface n = (NetworkInterface) e.nextElement();
//            Enumeration ee = n.getInetAddresses();
//            while (ee.hasMoreElements())
//            {
//                InetAddress i = (InetAddress) ee.nextElement();
//                System.out.println(i.getHostAddress());
//            }
//        }


        //        ProcessBuilder pb = new ProcessBuilder("");
//        pb.redirectErrorStream();
//        Process p = pb.start();
//        BufferedReader br = new BufferedReader (new InputStreamReader(p.getInputStream()));
//        String l=null;
//
//        while(br.readLine().isEmpty())
//        {
//            try
//            {
//                l = br.readLine();
//                System.out.println(l);
//            }
//            catch (IOException ex) {}
//
//        }
    }

}
