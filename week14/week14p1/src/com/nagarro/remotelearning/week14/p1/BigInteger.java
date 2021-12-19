package com.nagarro.remotelearning.week14.p1;

public class BigInteger {
    boolean sign;
    char[] digits;

    public BigInteger(boolean sign, char[] digits) {
        this.sign = sign;
        this.digits = digits;
    }
    public BigInteger(){
        this.digits = new char[100];
    }
}
