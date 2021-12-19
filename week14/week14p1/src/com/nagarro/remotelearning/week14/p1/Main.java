package com.nagarro.remotelearning.week14.p1;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;


public class Main {

    private static final String MESSAGE = "There are ";
    private static final String ODD_NUMBERS = " odd palindromes in the range";
    private static final String EVEN_NUMBERS = " even palindromes in the range";
    private int[] array;

    public static void main(String[] args) {

        IntPredicate isOdd = a -> a % 2 != 0;
        IntPredicate isEven = isOdd.negate();
        IntPredicate isPalindrome =
                a -> a == Integer.parseInt(new StringBuilder(String.valueOf(a)).reverse().toString());

        Long oddCount = IntStream.range(100, 10000).filter(isOdd.and(isPalindrome)).count();
        Long evenCount = IntStream.range(100, 10000).filter(isEven.and(isPalindrome)).count();

        System.out.println(MESSAGE + oddCount + ODD_NUMBERS);
        System.out.println(MESSAGE + evenCount + EVEN_NUMBERS);
    }
}
