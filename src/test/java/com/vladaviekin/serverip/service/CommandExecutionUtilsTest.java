package com.vladaviekin.serverip.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandExecutionUtilsTest {

    @Autowired
    private CommandExecutionUtils ces;

    @Test
    public void runNotNull() throws IOException, InterruptedException {

        List<String> run = ces.run("arp -a");

        System.out.println(run.toString());

        Assert.assertFalse(run.isEmpty());
        Assert.assertNotNull(run);

    }

    @Test
    public void runNull() throws IOException, InterruptedException {

        List<String> run = ces.run("");

        Assert.assertTrue(run.isEmpty());

    }
}