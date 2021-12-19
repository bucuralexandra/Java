package com.nagarro.remotelearning.week10.p1;

import java.util.Comparator;
import java.util.List;

public class CountrySearcher {

    public static int binarySearch(String capital, List<Country> countries) {
        Comparator comparator = new CapitalComparator();
        Country aux = new Country("", capital);
        return binarySearch(0, countries.size() - 1, aux, countries, comparator);
    }

    private static int binarySearch(int left, int right, Country country, List<Country> countries, Comparator comparator) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (comparator.compare(countries.get(mid), country) == 0) {
                return mid;
            }

            if (comparator.compare(countries.get(mid), country) < 0) {
                return binarySearch(left, mid - 1, country, countries, comparator);
            }
            return binarySearch(mid + 1, right, country, countries, comparator);
        }
        return -1;
    }
}
