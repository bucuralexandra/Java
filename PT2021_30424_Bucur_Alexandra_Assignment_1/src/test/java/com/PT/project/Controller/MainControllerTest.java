package com.PT.project.Controller;


import com.PT.project.Model.Polynomial;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MainControllerTest {

    @Mock
    private MainController mainController;

    @Mock
    private Polynomial p;

    @Before
    public void init() {
        mainController = new MainController();
        p = new Polynomial();
    }

    @Test
    public void testValidateData() {

        Assert.assertFalse("xx should not be a polynomial", mainController.validateInput("xx", 1));
    }

    @Test
    public void testValidateDataCorrectPolynomial() {

        Assert.assertTrue("should be a polynomial", mainController.validateInput("x^2+  5x", 1));
    }

    @Test
    public void testValidateDataAnotherCorrectPolynomial() {

        Assert.assertTrue("2 should be a polynomial", mainController.validateInput("2", 1));
    }

    @Test
    public void testValidateDataAnotherLetters() {

        Assert.assertFalse("x^2+a should not be a polynomial", mainController.validateInput("x^2+a", 1));
    }

    @Test
    public void testValidateDataMorePowerCharacters() {
        Assert.assertFalse("x^^2 should not be a polynomial", mainController.validateInput("x^^2", 1));
    }

    @Test
    public void testValidateDataMorePlusCharacters() {
        Assert.assertFalse("x^2++56 should not be a polynomial", mainController.validateInput("x^2++56", 1));
    }
}