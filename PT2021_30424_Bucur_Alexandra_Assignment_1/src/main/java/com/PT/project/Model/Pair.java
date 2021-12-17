package com.PT.project.Model;

/**
 * @author Alexandra Bucur
 *
 * this class is used only for the division method, in order to return both the quotinent and remainder
 * at the same time
 */
public class Pair {
    Polynomial polynomial1;
    Polynomial polynomial2;

    public Pair() {
    }

    public Pair(Polynomial polynomial1, Polynomial polynomial2) {
        this();
        this.polynomial1 = polynomial1;
        this.polynomial2 = polynomial2;
    }

    public Polynomial getPolynomial1() {
        return polynomial1;
    }

    public void setPolynomial1(Polynomial polynomial1) {
        this.polynomial1 = polynomial1;
    }

    public Polynomial getPolynomial2() {
        return polynomial2;
    }

    public void setPolynomial2(Polynomial polynomial2) {
        this.polynomial2 = polynomial2;
    }

    /**
     * @return a string containing both the Quotient and Remainder
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder("");
        s.append("Quotient: ");
        if (polynomial1 != null)
            s.append(polynomial1.toString()).append("  ");
        else s.append("impossible ");
        s.append("Remainder: ");
        if (polynomial2 != null)
            s.append(polynomial2.toString());
        else s.append("impossible");
        return s.toString();
    }
}
