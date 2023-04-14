package com.DevFox.biz.exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SplitTextByLine {
    public static void main(String[] args) {
        String filePath = "src/main/resources/test.txt";
        List<String> group1 = new ArrayList<>();
        List<String> group2 = new ArrayList<>();

        int lineNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (lineNumber % 2 == 1) {
                    group1.add(line);
                } else {
                    group2.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        for (int i = 0; i<  lineNumber / 2 ; i++) {
            System.out.println(group1.get(i));
            System.out.println(group2.get(i));
        }
    }
}