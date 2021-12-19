package com.nagarro.remotelearning.week9.p2;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;


public class SortManagerTest {

    private LinkedList<Double> linkedList = new LinkedList<>();
    private ArrayList<Integer> array = new ArrayList<>();
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private static final double DELTA = 1e-15;

    @Before
    public void initData() {
        linkedList.add(2.5);
        linkedList.add(0.99);
        linkedList.add(100.7);
        linkedList.add(0.12);

        array.add(2);
        array.add(20);
        array.add(23);
        array.add(10);
        array.add(-2);

        stringArrayList.add("ab");
        stringArrayList.add("xx");
        stringArrayList.add("a");
        stringArrayList.add("cascaval");
    }

    @Test
    public void testBubbleSortLinkedList() {

        SortManager.bubbleSort(linkedList);
        Assert.assertEquals(0.12, (double) linkedList.get(0), DELTA);
        Assert.assertEquals(0.99, (double) linkedList.get(1), DELTA);
        Assert.assertEquals(2.5, (double) linkedList.get(2), DELTA);
        Assert.assertEquals(100.7, (double) linkedList.get(3), DELTA);
    }

    @Test
    public void testBubbleSortArrayList() {
        SortManager.bubbleSort(array);
        Assert.assertEquals("First operand does not match", -2, (int) array.get(0));
        Assert.assertEquals("Second operand does not match", 2, (int) array.get(1));
        Assert.assertEquals("Third operand does not match", 10, (int) array.get(2));
        Assert.assertEquals("Fourth operand does not match", 20, (int) array.get(3));
        Assert.assertEquals("Fifth operand does not match", 23, (int) array.get(4));

    }

    @Test
    public void testBubbleSortArrayListString() {
        SortManager.bubbleSort(stringArrayList);
        Assert.assertEquals("First operand does not match", "a", (String) stringArrayList.get(0));
        Assert.assertEquals("Second operand does not match", "ab", (String) stringArrayList.get(1));
        Assert.assertEquals("Third operand does not match", "cascaval", (String) stringArrayList.get(2));
        Assert.assertEquals("Fourth operand does not match", "xx", (String) stringArrayList.get(3));
    }
}