package com.nagarro.remotelearning.week9.p3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;

public class MyPriorityQueueTest {

    private ArrayList<Integer> array = new ArrayList<>();
    private MyPriorityQueue<Integer> myPriorityQueue = new MyPriorityQueue<>(10);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void initData() {
        myPriorityQueue.insert(20);
        myPriorityQueue.insert(90);
        array.add(12);
        array.add(88);
        array.add(21);
        array.add(200);
        array.add(-11);

    }

    @Test
    public void testInsert() {
        myPriorityQueue.insert(100);
        Assert.assertEquals("inserted method not correct", "100 20 90 ", myPriorityQueue.toString());
    }

    @Test
    public void testInsertOverflow() {

        exception.expect(OverflowException.class);
        String msg = "Overflow";
        exception.expectMessage(msg);
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(1);
        queue.insert(669);
        queue.insert(22);

    }

    @Test
    public void testRemoveNonEmptyQueue() {
        int i = myPriorityQueue.remove();
        Assert.assertEquals("Output mismatch", 90, i);
    }

    @Test
    public void testRemoveEmptyQueue() {
        myPriorityQueue.clear();
        Integer i = myPriorityQueue.remove();
        Assert.assertEquals("Output mismatch", null, i);
    }

    @Test
    public void testHeadOnNonEmptyQueue() {

        int result = myPriorityQueue.head();
        Assert.assertEquals("Result and actual output do not correspond", 90, result);
    }

    @Test
    public void testHeadOnEmptyQueue() {

        myPriorityQueue.clear();
        Integer result = myPriorityQueue.head();
        Assert.assertEquals("Result and actual output do not correspond", null, result);
    }

    @Test
    public void testIsEmptyOnEmptyQueue() {
        myPriorityQueue.clear();
        Assertions.assertTrue(myPriorityQueue.isEmpty(), "Output mismatch");
    }

    @Test
    public void testIsEmptyOnNonEmptyQueue() {
        Assertions.assertFalse(myPriorityQueue.isEmpty(), "Output mismatch");
    }

    @Test
    public void testSort() {
        array = myPriorityQueue.sort(array);
        ArrayList<Integer> actual = new ArrayList<>();
        actual.add(-11);
        actual.add(12);
        actual.add(21);
        actual.add(88);
        actual.add(200);
        Assert.assertEquals(actual, array);
    }
}