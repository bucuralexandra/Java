package com.nagarro.remotelearning.week9.p1;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        MyArray<Integer> array = new MyArray();
        array.add(2);
        array.add(5);
        System.out.println(array.getSize());

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(5);
        System.out.println(array.containsAll(integerArrayList));

        integerArrayList.add(20);
        System.out.println(array.containsAll(integerArrayList));

        array.addAll(integerArrayList);
        System.out.println(array.getSize());

        MyArray<String> stringMyArray = new MyArray<>();
        LinkedList<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        stringMyArray.addAll(list);
        System.out.println(stringMyArray.getSize());
        System.out.println(stringMyArray.containsAll(list));

    }
}
