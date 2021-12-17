package com.PT.project.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OperationsTest {

    private Polynomial p1;
    private Polynomial p2;
    private Polynomial result;
    private Polynomial rest;

    @Before
    public void init() {
        p1 = new Polynomial();
        p2 = new Polynomial();
        rest = new Polynomial();
        rest = new Polynomial();

        p1.addMonomialToPolynomial(1, 2);
        p1.addMonomialToPolynomial(2, 4);
        p1.addMonomialToPolynomial(0, 3);

        p2.addMonomialToPolynomial(5, 5);
        p2.addMonomialToPolynomial(1, -7);
        p2.addMonomialToPolynomial(0, 10);
    }

    @Test
    public void testAdd() {

        result = Operations.add(p1, p2);
        Assert.assertEquals("5x^5+4x^2-5x+13", result.toString());
    }

    @Test
    public void testSubstract() {
        result = Operations.substract(p1, p2);
        Assert.assertEquals("-5x^5+4x^2+9x-7", result.toString());
    }

    @Test
    public void testSubstractResultIsZero() {
        result = Operations.substract(p1, p1);
        Assert.assertEquals("0", result.toString());
    }


    @Test
    public void testMultiply() {
        result = Operations.multiply(p1, p2);
        Assert.assertEquals("20x^7+10x^6+15x^5-28x^3+26x^2-x+30", result.toString());
    }

    @Test
    public void testMultiplyAgain() {

        Polynomial p3 = new Polynomial();
        Polynomial p4 = new Polynomial();
        p3.addMonomialToPolynomial(1, 1);
        p3.addMonomialToPolynomial(0, 1);
        p4.addMonomialToPolynomial(7, 1);
        result = Operations.multiply(p3, p4);
        Assert.assertEquals("x^8+x^7", result.toString());
    }

    @Test
    public void testMultiplyOnlyCoeff() {
        Polynomial p3 = new Polynomial();
        p3.addMonomialToPolynomial(0, 7);
        result = Operations.multiply(p3, p1);
        Assert.assertEquals("28x^2+14x+21", result.toString());
    }

    @Test
    public void testDerivatePolynomial1() {
        result = Operations.derivate(p1);
        Assert.assertEquals("8x+2", result.toString());

    }

    @Test
    public void testDerivatePolynomial2() {
        result = Operations.derivate(p2);
        Assert.assertEquals("25x^4-7", result.toString());
    }

    @Test
    public void estDerivateConstant() {
        p2.empty();
        p2.addMonomialToPolynomial(0, 10);
        result = Operations.derivate(p2);
        Assert.assertEquals("0", result.toString());
    }

    @Test
    public void testIntegrateConstant() {
        p2.empty();
        p2.addMonomialToPolynomial(0, 10);
        result = Operations.integrate(p2);
        Assert.assertEquals("10x", result.toString());
    }

    @Test
    public void testIntegrateOnlyIntegersAsResult() {
        p2.empty();
        p2.addMonomialToPolynomial(0, 1);
        p2.addMonomialToPolynomial(2, 3);
        p2.addMonomialToPolynomial(3, 4);
        result = Operations.integrate(p2);
        Assert.assertEquals("x^4+x^3+x", result.toString());
    }

    @Test
    public void testIntegrateRandomPolynomial() {
        result = Operations.integrate(p2);
        Assert.assertEquals("0.83x^6-3.5x^2+10x", result.toString());
    }

    @Test
    public void testDivideDivisionByZero() {
        p2.empty();
        Pair pair = new Pair();
        pair = Operations.divide(p1, p2);
        Assert.assertEquals("Quotient: impossible Remainder: impossible", pair.toString());
    }

    @Test
    public void testDivideDivisionOnlyReaminder() {
        Pair pair = new Pair();
        pair = Operations.divide(p1, p2);
        Assert.assertEquals("Quotient: 0  Remainder: 4x^2+2x+3", pair.toString());
    }

    @Test
    public void testDivideDivisionByConstant() {
        p2.empty();
        Pair pair = new Pair();
        p2.addMonomialToPolynomial(0, 2);
        pair = Operations.divide(p1, p2);
        Assert.assertEquals("Quotient: 2x^2+x+1.5  Remainder: 0", pair.toString());
    }

    @Test
    public void testDivideDivision() {
        Pair pair = new Pair();
        p1.empty();
        p2.empty();
        p1.addMonomialToPolynomial(2, 3);
        p1.addMonomialToPolynomial(1, 5);
        p1.addMonomialToPolynomial(0, 2);
        p2.addMonomialToPolynomial(1, 2);
        p2.addMonomialToPolynomial(0, 1);
        pair = Operations.divide(p1, p2);
        Assert.assertEquals("Quotient: 1.5x+1.75  Remainder: 0.25", pair.toString());
    }
}