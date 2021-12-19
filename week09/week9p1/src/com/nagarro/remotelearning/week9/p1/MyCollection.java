package com.nagarro.remotelearning.week9.p1;

import java.util.Collection;

public interface MyCollection<E> extends Iterable<E> {

    boolean containsAll(Collection<? extends E> c);

    boolean addAll(Collection<? extends E> c);

}
