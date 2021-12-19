package com.nagarro.remotelearning.week15.p2;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File file = new File("integers.txt");
        FileManager fileManager = new FileManager();
        fileManager.writeData(file);
        fileManager.readData();
    }
}