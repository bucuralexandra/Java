package com.nagarro.remotelearning.week9.p3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyPriorityQueue<E extends Comparable> implements Comparable<MyPriorityQueue<E>> {

    private static final String EMPTY_QUEUE = "Queue is empty";
    private int maxCapacity;
    private int actualSize;
    private ArrayList<E> priorityQueue;

    public MyPriorityQueue() {
        this.maxCapacity = 10000;
        this.actualSize = 0;
        this.priorityQueue = new ArrayList<>();
    }

    public MyPriorityQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.actualSize = 0;
        this.priorityQueue = new ArrayList<>();
    }

    public int getActualSize() {
        return actualSize;
    }

    void insert(E e) {
        if (e != null) {
            if (this.actualSize + 1 > this.maxCapacity)
                throw new OverflowException("Overflow");
            else {
                this.actualSize++;
                this.priorityQueue.add(e);
                heapIncreaseKey(this.priorityQueue, this.actualSize - 1, e);
            }
        }
    }

    E remove() {
        if (this.priorityQueue != null && this.actualSize > 0) {
            E aux = this.priorityQueue.get(0);
            this.priorityQueue.set(0, this.priorityQueue.get(actualSize - 1));
            this.actualSize--;
            maxHeapify(this.priorityQueue, 0);
            return aux;
        }
        return null;
    }

    private void maxHeapify(ArrayList<E> queue, int index) {
        int max = index; // Initialize largest as root
        int size = queue.size();
        int left = 2 * index + 1; // left = 2*i + 1, because array starts from 0
        int right = 2 * index + 2; // right = 2*i + 2, because array starts from 0

        if (left < size && queue.get(left).compareTo(queue.get(max)) > 0)
            // If left index still in array andthe left child is larger than root
            max = left;
        // If right child is larger than maximum so far ( and index in array)
        if (right < size && queue.get(right).compareTo(queue.get(max)) > 0)
            max = right;
        if (max != index) {
            Collections.swap(queue, index, max);
            maxHeapify(queue, max); //heapify the rest
        }
    }


    private int parent(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    private void heapIncreaseKey(ArrayList<E> array, int i, E key) {
        int ok = 0;
        if (key.compareTo(array.get(i)) < 0) {
            System.out.println("Cannot be inserted");
        }
        array.set(i, key);
        while (i > 0 && ok == 0) {
            int parent = parent(i);
            if (array.get(parent).compareTo(array.get(i)) < 0) {
                Collections.swap(array, i, parent);
                i = parent;
            } else ok = 1;
        }
    }

    void clear() {
        this.priorityQueue.clear();
        this.actualSize = 0;
    }

    E head() {
        if (this.actualSize > 0 && this.priorityQueue != null) {
            return this.priorityQueue.get(0);
        }
        return null;
    }

    boolean isEmpty() {
        return this.actualSize > 0 ? false : true;
    }

    @Override
    public int compareTo(MyPriorityQueue<E> o) {
        if (this.actualSize > o.getActualSize()) return 1;
        else if (this.actualSize == o.getActualSize()) return 0;
        return -1;
    }

    public ArrayList<E> sort(List<E> list) {
        if (list != null) {
            this.priorityQueue = null;
            this.actualSize = 0;
            ArrayList<E> helper = (ArrayList<E>) list;
            for (E i : helper) {
                insert(i);
            }
            int size = this.actualSize;
            helper = new ArrayList<>(size + 6);
            for (int i = 0; i < size; i++) {
                helper.add(null);
            }
            for (int i = 0; i < size; i++) {
                E aux = remove();
                helper.set(size - 1 - i, aux);
            }
            return helper;
        }
        return null;
    }

    @Override
    public String toString() {
        if (this.priorityQueue != null) {
            if (this.actualSize > 0) {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < this.actualSize; i++) {
                    E e = this.priorityQueue.get(i);
                    s.append(e.toString()).append(" ");
                }
                return s.toString();
            } else return EMPTY_QUEUE;
        } else return EMPTY_QUEUE;
    }
}

