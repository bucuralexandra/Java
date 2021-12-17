package com.PT.project.Model;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author Alexandra Bucur
 */
public class Monomial implements Comparable {
    private int power;
    private Number coeff;

    public Monomial() {
    }

    public Monomial(int power, int coeff) {
        this();
        this.power = power;
        this.coeff = coeff;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Number getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    /**
     * @param m sees if m is equal to the actual monomial
     * @return 0/1
     */

    @Override
    public int compareTo(Object m) {
        int comparePower = ((Monomial) m).getPower();
        return comparePower - this.power;
    }

    /**
     *
     * @return a string of 1 monomial that displays the data nicely
     */
    @Override
    public String toString() {

        if (this.coeff.intValue() != this.coeff.doubleValue())
            this.coeff = Double.parseDouble(new DecimalFormat("##.##").format(this.coeff));
        if (this.power > 0 && this.coeff.intValue() == 1 && this.coeff.doubleValue() == 1) {
            if (power == 1) return "+x";
            else return "+x^" + this.power;
        }
        if (this.power > 0 && this.coeff.intValue() == -1 && this.coeff.doubleValue() == -1) {
            if (power == 1) return "-x";
            else return "-x^" + this.power;
        }
        if (this.power == 0) {
            if (this.coeff.doubleValue() < 0) {
                return this.coeff.toString();
            } else
                return "+" + this.coeff.toString();
        } else {
            if (this.power == 1) {
                if (this.coeff.intValue() < 0) {
                    return this.coeff.toString() + "x";
                } else
                    return "+" + this.coeff.toString() + "x";
            } else {
                if (this.coeff.intValue() < 0) {
                    return this.coeff.toString() + "x^" + this.power;
                } else
                    return "+" + this.coeff.toString() + "x^" + this.power;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monomial monomial = (Monomial) o;
        return power == monomial.power;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, coeff);
    }

    public void addMon(int value) {
        this.coeff = this.coeff.intValue() + value;
    }
}
