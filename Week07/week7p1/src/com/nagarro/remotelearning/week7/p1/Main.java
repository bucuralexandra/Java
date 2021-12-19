package com.nagarro.remotelearning.week7.p1;

public class Main {

    public static void main(String[] args) {

        Validation validation = new Validation();

        System.out.println("---EMAILS---");
        System.out.println("Email Ana@yahoo.com is " + (validation.validateEmail("Ana@yahoo.com") ?
                "correct" : "incorrect"));
        System.out.println("Email Ana@yahoo.ro is " + (validation.validateEmail("Ana@yahoo.ro") ?
                "correct" : "incorrect"));
        System.out.println("Email An@a@yahoo.com is " + (validation.validateEmail("An@a@yahoo.com") ?
                "correct" : "incorrect"));
        System.out.println("Email Ana@yahoo.coom is " + (validation.validateEmail("Ana@yahoo.coom") ?
                "correct" : "incorrect"));

        System.out.println("---PHONE NUMBERS---");
        System.out.println("Number +40 728110538 is " + (validation.validatePhoneNumber("+40 728110538") ?
                "correct" : "incorrect"));
        System.out.println("Number 0040728110538 is " + (validation.validatePhoneNumber("0040728110538") ?
                "correct" : "incorrect"));
        System.out.println("Number 0755242480 is " + (validation.validatePhoneNumber("+40755242480") ?
                "correct" : "incorrect"));
        System.out.println("Number 0098767 is " + (validation.validatePhoneNumber("0098767") ?
                "correct" : "incorrect"));

        System.out.println("---PASSWORDS---");
        System.out.println("Password Mamaliga!!1 is " + (validation.validatePassword("Mamaliga!!1") ?
                "correct" : "incorrect"));
        System.out.println("Password M!!1 is " + (validation.validatePassword("M!!1") ?
                "correct" : "incorrect"));
        System.out.println("Password mamaliga!!1 is " + (validation.validatePassword("mamaliga!!1") ?
                "correct" : "incorrect"));
        System.out.println("Password Mamaliga is " + (validation.validatePassword("Mamaliga") ?
                "correct" : "incorrect"));
    }
}
