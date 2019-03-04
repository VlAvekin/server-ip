package com.vladaviekin.serverip.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutionUtils {

    private static List<String> result;

    static List<String> run(final String command) throws IOException, InterruptedException {
        result = new ArrayList<>();

        Process p = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
        String s;
        while ((s = br.readLine()) != null) {
            result.add(s);
        }
        p.waitFor();
        p.destroy();

        return result;
    }

    public static List<String> getResult() {
        return result;
    }
}
