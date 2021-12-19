package com.nagarro.remotelearning.week9.p2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(2);
        list.add(1);
        list.add(90);

        Integer[] sir1 = new Integer[] { 1, 2, 3, 4, 5 };
        SortManager.bubbleSort(sir1);
        for(int i = 0; i < sir1.length; i++){
            System.out.print(sir1[i] + " ");
        }
        System.out.println();
        Integer[] sir2 = new Integer[] {3, 1, 5, 4, 2 };
        SortManager.bubbleSort(sir2);
        for(int i = 0; i < sir2.length; i++){
            System.out.print(sir2[i] + " ");
        }

        System.out.println();
        String[] s = new String[] { "c", "a", "e", "d", "b" };
        SortManager.bubbleSort(s);
        for(int i = 0; i < s.length; i++){
            System.out.print(s[i] + " ");
        }

        System.out.println();
        SortManager.bubbleSort(list);
        System.out.println(list.toString());

    }
}
