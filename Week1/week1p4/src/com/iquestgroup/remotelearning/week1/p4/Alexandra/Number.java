package com.iquestgroup.remotelearning.week1.p4.Alexandra;

public class Number {

    private long n;

    public Number(long n) {
        this.n = n;
    }

    public boolean isPalindrome(long number) {
        long copy = number;
        long newN = 0;

        while (copy > 0) {

            newN = newN * 10 + (copy % 10);
            copy /= 10;
        }
        if (number == newN)
            return true;
        return false;
    }

    public int countPalindromes(long limit) {
        long index;
        int count = 0;
        for (index = this.n; index < limit; index += this.n) {
            if (isPalindrome(index)) {
                count++;
            }
        }
        return count;
    }
}
