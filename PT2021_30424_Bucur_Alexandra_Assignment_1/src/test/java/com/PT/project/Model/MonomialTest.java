package com.PT.project.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MonomialTest {

    private Monomial monomial;
    private Monomial monomial2;
    private Monomial monomial3;
    private Monomial monomial4;
    private Monomial monomial5;
    private Monomial monomial6;

    @Before
    public void initData() {
        monomial = new Monomial(2, 2);
        monomial2 = new Monomial(0, 2);
        monomial3 = new Monomial(2, 1);
        monomial4 = new Monomial(2, 0);
        monomial4.setCoeff(1.23);
        monomial5 = new Monomial(1, 2);
        monomial5.setCoeff(1.0);
        monomial6 = new Monomial(0, -1);
    }

    @Test
    public void testToStringMonomial1() {
        String result = monomial.toString();
        Assert.assertEquals("Values differ", result, "+2x^2");
    }

    @Test
    public void testToStringMonomial2() {
        String result = monomial2.toString();
        Assert.assertEquals("Values differ", result, "+2");
    }

    @Test
    public void testToStringMonomial3() {
        String result = monomial3.toString();
        Assert.assertEquals("Values differ", result, "+x^2");
    }

    @Test
    public void testToStringMonomial4() {
        String result = monomial4.toString();
        Assert.assertEquals("Values differ", result, "+1.23x^2");
    }

    @Test
    public void testToStringMonomial5() {
        String result = monomial5.toString();
        Assert.assertEquals("Values differ", result, "+x");
    }

    @Test
    public void testToStringMonomial6() {
        String result = monomial6.toString();
        Assert.assertEquals("Values differ", result, "-1");
    }

}