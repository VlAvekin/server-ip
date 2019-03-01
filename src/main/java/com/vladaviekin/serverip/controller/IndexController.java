package com.vladaviekin.serverip.controller;

import com.vladaviekin.serverip.model.NetworkAddressModel;
import com.vladaviekin.serverip.model.NetworkConnectModel;
import com.vladaviekin.serverip.service.NetworkAddressService;
import com.vladaviekin.serverip.service.NetworkConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private NetworkAddressService networkAddressService;


    @GetMapping("/")
    public String index(Model model) throws SocketException {

        List<String> networkNameList = new ArrayList<>();
        networkNameList.add("all");
        networkNameList.addAll(networkAddressService.netNamesNotNull());
        model.addAttribute("networkNameList", networkNameList);

        List<NetworkAddressModel> networkAddressModelList = networkAddressService.allNotNull();

        for (NetworkAddressModel nam: networkAddressModelList) {
            System.out.println(nam.getNetworkConnect().toString());
        }

        model.addAttribute("networkAddressModelList", networkAddressModelList);

        return "index";
    }
}
