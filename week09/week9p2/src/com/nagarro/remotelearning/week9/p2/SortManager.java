package com.nagarro.remotelearning.week9.p2;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SortManager {

    public static <T extends Comparable<T>> void bubbleSort(Collection<T> values) {
        if ((null == values) || (values.size() < 1)) {
            return;
        }
        List<T> list = (List<T>) values;
        int length = values.size();
        boolean isOrdered;
        for (int i = 0; i < length; i++) {
            isOrdered = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    isOrdered = false;
                    Collections.swap(list, j, j + 1);
                }
            }
            if (isOrdered) {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] values) {
        int length = values.length;
        int count = 0;
        boolean isOrdered;

        for (int i = 1; i < length; i++) {
            isOrdered = true;

            for (int j = 0; j < length - i; j++) {
                count++;
                if (values[j].compareTo(values[j + 1]) > 0) {
                    isOrdered = false;
                    swap(values, j, j + 1);
                }
            }

            if (isOrdered) {
                break;
            }
        }
    }

    private static <T> void swap(T[] values, int first, int second) {
        T temp = values[first];

        values[first] = values[second];
        values[second] = temp;
    }
}

