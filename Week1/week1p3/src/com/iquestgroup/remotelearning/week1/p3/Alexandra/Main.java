package com.iquestgroup.remotelearning.week1.p3.Alexandra;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        int num;
        if (args.length == 0)
            throw new IllegalArgumentException("No argument passed");
        try {
            num = parseInt(args[0]);
            System.out.println(transformBinary(num));
            System.out.println(Integer.toBinaryString(num));
        } catch (NumberFormatException e) {
            System.out.println("Wrong input, could not convert.");
        }
    }

    public static String transformBinary(int n) {

        StringBuilder binaryString = new StringBuilder();
        if (n == 0)
            return "0";
        while (n > 0) {
            binaryString.append(n % 2);
            n /= 2;
        }
        binaryString.reverse();
        return binaryString.toString();
    }
}
