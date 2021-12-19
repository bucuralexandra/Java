package com.nagarro.remotelearning.week7.p2;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        ComputeDateFormat computeDateFormat = new ComputeDateFormat();

        computeDateFormat.outputFinalDate(LocalDate.of(2017, 1, 13));
        System.out.println();

        computeDateFormat.outputFinalDate(LocalDate.now().minusDays(3));
    }
}
