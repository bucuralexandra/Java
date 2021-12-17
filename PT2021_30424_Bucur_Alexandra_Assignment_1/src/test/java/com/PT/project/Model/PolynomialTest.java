package com.PT.project.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PolynomialTest {

    Polynomial p;

    @Before
    public void init() {
        p = new Polynomial();
        p.addMonomialToPolynomial(2, 2);
        p.addMonomialToPolynomial(0, 1);
        p.addMonomialToPolynomial(4, 100);
    }

    @Test
    public void testAddMonomialToPolynomial() {

        Assert.assertEquals("2x^2+1+100x^4", p.toString());
    }

    @Test
    public void testGetCanonicalForm() {
        p.empty();
        p.addMonomialToPolynomial(0, 1);
        p.addMonomialToPolynomial(1, 9);
        p.addMonomialToPolynomial(9, -8);
        p.addMonomialToPolynomial(3, 8);
        p.getCanonicalForm();
        Assert.assertEquals("-8x^9+8x^3+9x+1", p.toString());
    }

    @Test
    public void testEmpty() {
        p.empty();
        Assert.assertEquals("0", p.toString());
    }

    @Test
    public void testDegree() {
        int result = p.degree();
        Assert.assertEquals("Results differ in testDegree test", 4, result);
    }

    @Test
    public void testDegreeEmptyPolynomial() {
        p.empty();
        int result = p.degree();
        Assert.assertEquals("Results differ in testDegree test", 0, result);
    }


    @Test
    public void testGetFirst() {
        Monomial m = p.getFirst();
        Assert.assertEquals("+100x^4", m.toString());
    }

    @Test
    public void testGetFirstEmptyPol() {
        p.empty();
        Monomial m = p.getFirst();
        Assert.assertEquals("+0", m.toString());
    }
}