package com.nagarro.remotelearning.week9.p1;

import java.util.*;
import java.util.function.Consumer;

public class MyArray<E> implements MyCollection<E> {

    private int size = 0;
    private static final int MAX_CAPACITY = 1000;
    private E[] elements;

    public MyArray() {
        this.elements = (E[]) new Object[MAX_CAPACITY];
    }

    @Override
    public boolean containsAll(Collection<? extends E> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        for (E obj : c) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(E elem) {
        boolean ok = false;
        for (E e : elements) {
            if (e != null && e.equals(elem)) {
                    ok = true;
                }
        }
        return ok;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean add(E elem) {
        if (size + 1 > MAX_CAPACITY){
            return false;
        }
        elements[size] = elem;
        size++;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && elements[currentIndex] != null;
            }

            @Override
            public E next() {
                return (E) elements[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer action) {
        Objects.requireNonNull(action);
        for (E t : this.elements) {
            action.accept(t);
        }
    }

    @Override
    public Spliterator spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    public int getSize() {
        return size;
    }
}
