package com.nagarro.remotelearning.week13.p1;

public class Main {

    public static void main(String[] args) {

        OperationTransformator operationTransformator = new OperationTransformator();

        Transformer summer = (num1, num2, num3, num4) ->
                (int) num1 + (int) num2 + (int) num3 + (int) num4;

        Transformer multiplier = (num1, num2, num3, num4) ->
                (int) num1 * (int) num2 * (int) num3 * (int) num4;

        Transformer stringConcatenated = (term1, term2, term3, term4) ->
                (String) term1 + (String) term2 + term3 + (String) term4;
        Transformer stringConcatenatedWithSpaces = (num1, num2, num3, num4) ->
                num1 + " " + num2 + " " + num3 + " " + num4;

        operationTransformator.applyTransformations(new Integer[]{1, 2, 3, 4}, summer, System.out::println);
        operationTransformator.applyTransformations(new Integer[]{1, 2, 3, 4}, multiplier, System.out::println);
        operationTransformator.applyTransformations(new String[]{"Afara", "e", "foarte", "frumos"}, stringConcatenated, System.out::println);
        operationTransformator.applyTransformations(new String[]{"Mihai", "pleaca", "de", "acasa"}, stringConcatenatedWithSpaces, System.out::println);
        System.out.println("Incorrect length for the next array => exception");
        operationTransformator.applyTransformations(new String[]{"Mihai", "pleaca", "de", "acasa", "azi"}, stringConcatenatedWithSpaces, System.out::println);
    }
}
