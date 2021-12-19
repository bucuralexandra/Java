package com.nagarro.remotelearning.week10.p1;

import java.util.Comparator;

public class CapitalComparator implements Comparator<Country> {

    @Override
    public int compare(Country o1, Country o2) {
        if(o1.getCapital().equals(o2.getCapital())){
            return 0;
        }
        return o1.getCapital().compareTo(o2.getCapital());
    }
}
