package com.vladaviekin.serverip.service;

import com.vladaviekin.serverip.model.NetworkAddressModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NetworkAddressServiceTest {

    @Autowired
    private NetworkAddressService nas;

    @Test
    public void all() throws IOException, InterruptedException {
        List<NetworkAddressModel> all = nas.all();

        System.out.println(all.toString());
    }

    @Test
    public void allNotNull() {
    }
}