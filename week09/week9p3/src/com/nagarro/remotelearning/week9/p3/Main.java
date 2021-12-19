package com.nagarro.remotelearning.week9.p3;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        MyPriorityQueue<Integer> myQueue = new MyPriorityQueue<>(10);

        myQueue.insert(4);
        myQueue.insert(30);
        myQueue.insert(1);
        myQueue.insert(20);
        System.out.println(myQueue.toString());
        System.out.println(myQueue.head());
        myQueue.remove();
        System.out.println(myQueue.toString());
        myQueue.clear();
        myQueue.insert(2);
        System.out.println(myQueue.toString());
        myQueue.remove();
        System.out.println(myQueue.toString());


        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(50);
        arrayList.add(90);
        arrayList.add(1);
        System.out.println(myQueue.sort(arrayList));

        ArrayList<Integer> array = new ArrayList<>();
        array.add(12);
        array.add(88);
        array.add(21);
        array.add(200);
        array.add(-11);
        System.out.println(myQueue.sort(array));

        MyPriorityQueue<String> myStringQueue = new MyPriorityQueue<>(10);

        myStringQueue.insert("z");
        myStringQueue.insert("c");
        myStringQueue.insert("m");
        myStringQueue.insert("f");
        System.out.println(myStringQueue.toString());
        System.out.println(myStringQueue.head());
        myStringQueue.remove();
        System.out.println(myStringQueue.toString());
        myStringQueue.clear();
        myStringQueue.insert("apa");
        System.out.println(myStringQueue.toString());
        myStringQueue.remove();
        System.out.println(myStringQueue.toString());

    }
}
