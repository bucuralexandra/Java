package com.iquestgroup.bank;

public class BankSimulation {

    public static void main(String[] args) {
        Account account = new Account("George", 1L, 100);

        account.withdraw(10, 0.2f);

        System.out.println("Your balance: " + account.getBalance());

        account.withdraw(10, 0.2f);

        System.out.println("Your balance: " + account.getBalance());
    }
}
