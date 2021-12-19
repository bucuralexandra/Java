package com.nagarro.remotelearning.week7.p2;

import java.time.LocalDate;

public class ComputeDateFormat {

    public void outputFinalDate(LocalDate localDate) {
        System.out.println("Time computed: ");
        System.out.println("Year: " + localDate.getYear());
        System.out.println("Month: " + Months.values()[localDate.getMonthValue() - 1]);
        System.out.println("Day: " + Days.values()[localDate.getDayOfWeek().getValue() - 1]);
    }
}
