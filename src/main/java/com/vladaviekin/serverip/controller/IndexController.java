package com.vladaviekin.serverip.controller;

import com.vladaviekin.serverip.model.AddressConnectModel;
import com.vladaviekin.serverip.service.NetworkConnectService;
import com.vladaviekin.serverip.service.NetworkIpManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @Autowired
    private NetworkConnectService networkConnect;

    @Autowired
    private NetworkIpManagementService netIpManager;


    @GetMapping("/")
    public String index(@RequestParam(required = false) String status,
            Model model) throws IOException, InterruptedException {

        List<AddressConnectModel> addressConnectList = networkConnect.networkName();

        model.addAttribute("addressConnectList", addressConnectList);

        if (status != null){
            if (status.equals("0")){
                model.addAttribute("disconnect", "disconnect");
                model.addAttribute("connect", null);
            }

            if (status.equals("1")){
                model.addAttribute("connect", "connect");
                model.addAttribute("disconnect", null);
            }
        }

        return "index";
    }

    @PostMapping("/")
    public String networkConnDisconn(@RequestParam String status,
                                     @Valid String ip,
                                     Model model) throws IOException, InterruptedException {

        if (status.equals("1")) {
            netIpManager.connect(ip);
            return "redirect:/?status=1";
        }
        if (status.equals("0")) {
            netIpManager.disconnect(ip);
            return "redirect:/?status=0";
        }
        return "redirect:/";
    }
}
