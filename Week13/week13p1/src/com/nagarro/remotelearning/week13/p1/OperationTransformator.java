package com.nagarro.remotelearning.week13.p1;

import java.util.function.Consumer;

public class OperationTransformator<T> {

    private static final String ERROR_MESSAGE = "Incorrect length for the array";

    public void applyTransformations(T[] array, Transformer<T> transformer, Consumer<T> printer) {
        T result;
        if (array.length == 4) {
            result = transformer.transform(array[0], array[1], array[2], array[3]);
            printer.accept(result);
        } else {
            throw new IncorrectLengthException(ERROR_MESSAGE);
        }
    }
}
