package com.nagarro.remotelearning.week5.p2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class StringList implements List {

    private final int MAX_SIZE = 200;
    private String[] stringList;
    private int actualSize;

    public StringList() {
        this.stringList = new String[MAX_SIZE];
        this.actualSize = 0;
    }

    @Override
    public void add(Object element) {

        int aux;
        if (element == null || element.equals("")) {
            throw new CustomException("Null");
        }
        try {
            aux = Integer.parseInt((String) element);
        } catch (NumberFormatException e) {
            throw new CustomException("Invalid number.");

        }
        stringList[actualSize] = (String) element;
        this.actualSize++;
    }

    @Override
    public Object get(int positon) {
        if (positon >= this.actualSize) {
            throw new CustomException("Index out of bounds.");
        } else {
            return stringList[positon];
        }
    }

    @Override
    public boolean contains(Object element) {

        for (int i = 0; i < this.actualSize; i++) {
            if (element.equals(stringList[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(List foreignList) {
        boolean containsAllElements = false;
        for (int i = 0; i < foreignList.size(); i++) {
            containsAllElements = false;
            for (int j = 0; j < actualSize; j++) {
                if (stringList[j].equals(foreignList.get(i))) {
                    containsAllElements = true;
                }
            }
            if (!containsAllElements) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.actualSize;
    }
}
