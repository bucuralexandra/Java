package com.iquestint.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TitlelizerImplTest {

    private Titlelizer titlelizer;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testTitlelizeForNullShouldThrowException() {

        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Null String");
        stringTitlelizer.titlelize(null);

    }

    @Test
    public void testTitlelizeForEmptyStringShouldReturnEmptyString() {

        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        String actualResult = stringTitlelizer.titlelize("");
        assertThat(actualResult, is(""));
    }

    @Test
    public void testTitlelizeForTitlelizedString() {
        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        String actualResult = stringTitlelizer.titlelize("Already Titlelized String");
        assertThat(actualResult, is("Already Titlelized String"));
    }

    @Test
    public void testTitlelizeShouldIgnorePrepositionsString() {
        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        String actualResult = stringTitlelizer.titlelize("This String is Titlelized");
        assertThat(actualResult, is("This String is Titlelized"));
    }

    @Test
    public void testTitlelizeWhenFirsWordIsPreposition() {

        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        String actualResult = stringTitlelizer.titlelize("this String is Titlelized");
        assertThat(actualResult, is("This String is Titlelized"));
    }

    @Test
    public void testTitlelize() {
        StringTitlelizer stringTitlelizer = new StringTitlelizer();
        String actualResult = stringTitlelizer.titlelize("please titlelize me");
        assertThat(actualResult, is("Please Titlelize Me"));
    }

}
