package com.vladaviekin.serverip.controller;

import com.vladaviekin.serverip.model.NetworkAddressModel;
import com.vladaviekin.serverip.service.NetworkAddressService;
import com.vladaviekin.serverip.service.NetworkIpManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private NetworkAddressService networkAddressService;

    @Autowired
    private NetworkIpManagementService netIpManager;


    @GetMapping("/")
    public String index(@RequestParam(required = false) String name,
            Model model) throws IOException, InterruptedException {

        List<String> networkNameList = new ArrayList<>();
        networkNameList.add("all");
        networkNameList.addAll(networkAddressService.netNamesNotNull());
        model.addAttribute("networkNameList", networkNameList);

        if (name != null && !name.equals("all")){
            NetworkAddressModel networkAddressMode = networkAddressService.name(name);
            List<NetworkAddressModel> networkAddressModelList = new ArrayList<>();
            networkAddressModelList.add(networkAddressMode);

            model.addAttribute("networkAddressModelList", networkAddressModelList);
            model.addAttribute("name", name);
        } else {
            List<NetworkAddressModel> networkAddressModelList = networkAddressService.allNotNull();

            model.addAttribute("networkAddressModelList", networkAddressModelList);
            model.addAttribute("name", "all");
        }

        return "index";
    }

    @PostMapping("/")
    public String networkConnDiscon(@RequestParam String status,
                                    Model model) throws IOException, InterruptedException {

        if (status.equals("1")){
            //netIpManager.connect();
            System.out.println("Con");
        }

        if (status.equals("0")){
            //netIpManager.disconnect();
            System.out.println("DisCon");
        }

        return "redirect:/";
    }
}
