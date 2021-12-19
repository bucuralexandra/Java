package com.nagarro.remotelearning.week5.p2;


public interface List<T> {

    void add(T element) throws CustomException;

    T get(int position) throws CustomException;

    boolean contains(T element);

    boolean containsAll(List<T> foreignList) throws CustomException;

    int size();
}