package com.iquestint.ju;

import com.nagarro.remotelearning.week5.p2.CustomException;
import com.nagarro.remotelearning.week5.p2.List;
import com.nagarro.remotelearning.week5.p2.StringList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MyListTest {

    private List<String> list;
    private StringList subList = new StringList();

    private Class<CustomException> exceptionType = CustomException.class;

    private String[] initData = {"12", "23", "34", "45"};

    private String[] anotherString = {"12", "34"};

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        this.list = new StringList();
    }

    @Test
    public void testAddValuesToTheList() {
        initData();
        assertEquals(initData.length, list.size());
        for (String data : initData) {
            assertTrue(list.contains(data));
        }
    }

    private void initData() {
        for (String numberAsString : initData) {
            list.add(numberAsString);
        }
    }

    @Test
    public void testAddNonIntegerValue() {
        exception.expect(exceptionType);
        exception.expectMessage("Invalid number.");
        list.add("Hey, I'm not an integer.");
    }

    @Test
    public void testAddNonNullValue() {
        exception.expect(exceptionType);
        exception.expectMessage("Null");
        list.add(null);
    }

    @Test
    public void testIndexOutOfBounds() {
        initData();
        exception.expect(exceptionType);
        exception.expectMessage("Index out of bounds.");
        list.get(initData.length);
    }

    @Test

    public void testSize() {
        initData();
        Assert.assertEquals(4, list.size());
    }

    @Test
    public void testContainsAll() {
        initData();
        for (String numberAsString : anotherString) {
            subList.add(numberAsString);
        }
        Assert.assertEquals(true, list.containsAll(subList));
    }
}