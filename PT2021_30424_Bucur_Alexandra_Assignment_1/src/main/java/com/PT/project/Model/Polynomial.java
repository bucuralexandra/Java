package com.PT.project.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: Bucur Alexandra
 */

public class Polynomial {

    private ArrayList<Monomial> polynomial;

    public Polynomial() {
        this.polynomial = new ArrayList<>();
    }

    /**
     * @return a string representing the polynomial
     */
    @Override
    public String toString() {
        if (this.polynomial == null || this.polynomial.size() == 0) {
            return "0";
        }
        StringBuilder polinomyalString = new StringBuilder("");
        for (Monomial mon : polynomial) {
            polinomyalString.append(mon.toString());
        }
        if (polinomyalString.charAt(0) == '+')
            return polinomyalString.substring(1);
        else return polinomyalString.toString();
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }

    //public void setPolynomial(ArrayList<Monomial> polynomial) {
    //    this.polynomial = polynomial;
   // }

    /**
     * This method adds a new instance monomial to the polynomial =>size++ for the polynomial
     * @param m monomial to be added
     */
    public void addMonomialToPolynomial(Monomial m) {
        this.polynomial.add(m);
    }
    /**
     * This method adds a new  monomial to the polynomial, without creating a new monomial
     * =>size++ for the polynomial
     * @param power, coeff monomial to be added
     */
    public void addMonomialToPolynomial(int power, int coeff) {
        this.polynomial.add(new Monomial(power, coeff));
    }

    /**
     * sorts the polynomial in decreasing order
     */
    public void getCanonicalForm() {
        Collections.sort(this.polynomial);
    }
    /**
     * removes all monomials from a polynomial, after this the polynom is 0
     */
    public void empty() {
        this.polynomial.removeAll(this.polynomial);
    }

    /**
     * sorts the polynomial in decreasing order, afetr this it returns the first power of the first
     * monomial
     * @return an int; the largest number from all power.
     * */
    public int degree() {
        this.getCanonicalForm();
        if (!this.polynomial.isEmpty())
            return this.polynomial.get(0).getPower();
        else return 0;
    }
    /**
     * sorts the polynomial in decreasing order, afetr this it returns the first monomial
     * @return a monomial; the largest number from all power.
     * */
    public Monomial getFirst() {
        this.getCanonicalForm();
        if (!this.polynomial.isEmpty())
            return this.polynomial.get(0);
        else return new Monomial(0, 0);
    }
}
