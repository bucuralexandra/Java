package com.iquestgroup.bank;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;

import java.text.NumberFormat;

public class AccountTest {

    private Account account;
    private Account accountBad;
    private final NumberFormat fmt = NumberFormat.getCurrencyInstance();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initData() {
        account = new Account("Alexandra", 2398765, 200);
    }

    @Test
    public void testConstructorAccountwhenBalanceNegative_throwsException() {
        expectedException.expect(NumberFormatException.class);
        expectedException.expectMessage("Invalid balance input");
        accountBad = new Account("Tom", 78665, -90);
    }


    @Test
    public void testDepositwhenDepositValueIsPositive_thenBalanceIsChanged() {
        Assertions.assertTrue(account.deposit(100), "The balance value doesn't match");
    }

    @Test
    public void testDepositwhenDepositValueIsPositive_afterBalanceIsChanged() {
        Assertions.assertTrue(account.deposit(100), "The balance value doesn't match");
    }


    @Test
    public void testDepositwhenDepositValueIsNotPositive() {
        Assertions.assertFalse(account.deposit(-12), "Negative deposit is not checked");
    }

    @Test
    public void testDepositwhenDepositValueIsNotPositive_BalanceIsNotChanged() {
        Assertions.assertEquals(200, account.getBalance());
    }

    @Test
    public void testDepositNoAmount() {
        Assertions.assertFalse(account.deposit(0));
    }

    @Test
    public void testDepositNoAmount_thenBalanceIsNotChanged() {
        Assertions.assertEquals(200, account.getBalance(), "Cannot deposit 0 money");
    }

    @Test
    public void testWithdrawSimple() {
        Assertions.assertTrue(account.withdraw(20, 5));
    }

    @Test
    public void testWithdrawSimple_thenBalanceIsChanged() {
        account.withdraw(20, 5);
        Assertions.assertEquals(175, account.getBalance(), "The balance value doesn't match");
    }

    @Test
    public void testWithdrawBiggerBalance() {
        Assertions.assertFalse(account.withdraw(300, 5), "Amount bigger than the balance not checked");
    }

    @Test
    public void testWithdrawBiggerFee() {

        Assertions.assertFalse(account.withdraw(40, 1001));
    }

    @Test
    public void testWithdrawBiggerFee_thenBalanceNotChanged() {
        account.withdraw(40, 1001);
        Assertions.assertEquals(200, account.getBalance(), "Fee bigger than the balance not checked");
    }

    @Test
    public void testWithdrawBiggerAmount() {
        Assertions.assertFalse(account.withdraw(199, 2), "Amount + fee bigger than the balance not checked");
    }

    @Test
    public void testWithdrawNegativeAmount() {
        account.withdraw(199, 2);
        Assertions.assertFalse(account.withdraw(-10, 3), "Amount negative not checked");

    }

    @Test
    public void testWithdrawNegativeAmount_thenBalanceNotChanged() {
        Assertions.assertEquals(200, account.getBalance());
    }

    @Test
    public void testWithdrawNegativeFee() {

        Assertions.assertFalse(account.withdraw(10, -3), "Negative fee not checked");
    }

    @Test
    public void TestAddInterest() {
        account.addInterest();
        float result = account.getBalance();
        Assertions.assertEquals(200 + 200 * 0.045f, result, "the float value does not match");
    }

    @Test
    public void testGetBalance() {
        float result = account.getBalance();
        Assertions.assertEquals(200, result, "getBalance method is incorrect");
    }

    @Test
    public void testGetAccountNumber() {

        float result = account.getAccountNumber();
        Assertions.assertEquals(2398765, result, "The account number doesn't match");
    }

    @Test
    public void testToString() {

        String result = account.toString();
        Assertions.assertEquals("2398765" + "\t" + "Alexandra" + "\t" +
                        fmt.format(account.getBalance()), result,
                "The string value doesn't match");
    }
}