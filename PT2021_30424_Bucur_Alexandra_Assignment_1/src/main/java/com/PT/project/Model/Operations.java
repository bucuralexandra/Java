package com.PT.project.Model;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Operations {

    public static Polynomial add(Polynomial p1, Polynomial p2) {
        correctPolynomial(p1);  //removes 0 or reduddant data
        correctPolynomial(p2);
        Polynomial result = new Polynomial();
        ArrayList<Boolean> usedCoeff = new ArrayList(); //like a freq list
        for (int aux = 0; aux < p2.getPolynomial().size(); aux++) { //set all monomials to notUsed
            usedCoeff.add(true);
        }
        int index;
        for (Monomial m1 : p1.getPolynomial()) { //goes through all mon from p1
            index = p2.getPolynomial().indexOf(m1);
            if (index != -1) { //if same degree in p2 then add
                usedCoeff.set(index, false);
                Monomial res;
                res = addMonomial(m1, p2.getPolynomial().get(index), 0);
                result.addMonomialToPolynomial(res);
            } else {
                result.addMonomialToPolynomial(m1); //just add is as it is
            }
        }
        for (Monomial m2 : p2.getPolynomial()) {
            int indexInPolynomial;
            indexInPolynomial = p2.getPolynomial().indexOf(m2); //add the rest of monomials from p2
            if (usedCoeff.get(indexInPolynomial) == true) {
                result.addMonomialToPolynomial(m2);
            }
        }
        return correctPolynomial(result);
    }
    //same as addition
    public static Polynomial substract(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        ArrayList<Boolean> usedCoeff = new ArrayList();
        correctPolynomial(p1);
        correctPolynomial(p2);
        for (int aux = 0; aux <= p2.getPolynomial().size(); aux++) { //set all monomials to notUsed
            usedCoeff.add(true);
        }
        int index;
        for (Monomial m1 : p1.getPolynomial()) {
            index = p2.getPolynomial().indexOf(m1);
            if (index != -1) {
                usedCoeff.set(index, false);
                Monomial res;
                res = addMonomial(m1, p2.getPolynomial().get(index), 1);
                result.addMonomialToPolynomial(res);
            } else {
                result.addMonomialToPolynomial(m1);
            }
        }
        for (Monomial m2 : p2.getPolynomial()) {
            int indexInPolynomial;
            indexInPolynomial = p2.getPolynomial().indexOf(m2);
            if (usedCoeff.get(indexInPolynomial) == true) {
                m2.setCoeff(-m2.getCoeff().intValue());
                result.addMonomialToPolynomial(m2);
            }
        }
        return correctPolynomial(result);
    }

    //adds/adds with (-1) 2 monomials
    private static Monomial addMonomial(Monomial m1, Monomial m2, int mode) {
        Monomial res = new Monomial(0, 0);

        if (m1.getPower() == m2.getPower()) {
            if (mode == 0) {
                res.setCoeff(m1.getCoeff().intValue() + m2.getCoeff().intValue());
            } else {
                res.setCoeff(m1.getCoeff().intValue() - m2.getCoeff().intValue());
            }
            res.setPower(m1.getPower());
            return res;
        }
        return null;
    }

    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        correctPolynomial(p1);
        correctPolynomial(p2);
        Polynomial result;
        Polynomial incorrectFormPolynomial = new Polynomial();
        for (Monomial m1 : p1.getPolynomial()) {  //multiplies each mon from p1 with each mon from p2
            for (Monomial m2 : p2.getPolynomial()) {
                Monomial rez = new Monomial();
                rez.setPower(m1.getPower() + m2.getPower());
                rez.setCoeff(m1.getCoeff().intValue() * m2.getCoeff().intValue());
                int index = incorrectFormPolynomial.getPolynomial().indexOf(rez);
                if (index != -1) {
                    Monomial aux = incorrectFormPolynomial.getPolynomial().get(index);
                    aux.setCoeff(aux.getCoeff().intValue() + m1.getCoeff().intValue() * m2.getCoeff().intValue());
                } else {
                    incorrectFormPolynomial.addMonomialToPolynomial(rez);
                }
            }
        }
        result = correctPolynomial(incorrectFormPolynomial); //if some mon with same degree exists, it just adds them together
        return result;
    }

    private static Polynomial correctPolynomial(Polynomial polynomial) {
        polynomial.getCanonicalForm(); //gets canonical form
        Iterator<Monomial> iterator = polynomial.getPolynomial().iterator();
        while (iterator.hasNext()) {
            Monomial currentMon = iterator.next();
            if (currentMon.getCoeff().intValue() == 0 && currentMon.getCoeff().doubleValue() == 0) { //if coeff is 0->remove
                iterator.remove();
            }
        }
        return polynomial;
    }

    public static Polynomial derivate(Polynomial p) {
        correctPolynomial(p);
        Polynomial result = new Polynomial();

        for (Monomial m : p.getPolynomial()) {
            Monomial aux = new Monomial(0, 0);
            if (m.getPower() != 0) {
                aux.setCoeff(m.getCoeff().intValue() * m.getPower()); //performs derivation
                aux.setPower(m.getPower() - 1);
            } else {
                aux.setCoeff(0);
                aux.setPower(0);
            }
            result.addMonomialToPolynomial(aux); //get pretty output
        }
        return correctPolynomial(result);
    }

    public static Polynomial integrate(Polynomial p) {
        correctPolynomial(p);
        Polynomial result = new Polynomial();

        for (Monomial m : p.getPolynomial()) {
            Monomial aux = new Monomial(0, 0);
            aux.setPower(m.getPower() + 1);
            if (m.getCoeff().intValue() % aux.getPower() == 0) {  //perform integration
                aux.setCoeff((m.getCoeff().intValue() / aux.getPower()));
            } else {
                aux.setCoeff((((double) m.getCoeff().intValue()) / aux.getPower()));
            }
            result.addMonomialToPolynomial(aux); //get pretty output
        }
        result.getCanonicalForm();
        return result;
    }

    public static Pair divide(Polynomial p1, Polynomial p2) {
        correctPolynomial(p1);correctPolynomial(p2);
        Polynomial result = new Polynomial();Polynomial rest = new Polynomial();
        if (p2.getPolynomial().isEmpty()) { //some special cases
            return new Pair(null, null);
        } else {
            if (p1.degree() < p2.degree()) {
                result.empty();
                return new Pair(result, p1);
            } else {
                if (p2.getPolynomial().size() == 1 && p2.getPolynomial().get(0).getPower() == 0) {
                    rest.empty();
                    result = simpleDivide(p1, p2);
                    return new Pair(result, rest);
                } else {
                    for (Monomial aux : p1.getPolynomial()) { //copy pol1
                        Monomial addM = new Monomial();
                        addM.setPower(aux.getPower());
                        addM.setCoeff(aux.getCoeff().doubleValue());
                        rest.addMonomialToPolynomial(addM);
                    }
                    Monomial m2 = p2.getFirst(); //return biggest degree monomial
                    Polynomial between; Polynomial auxForMultiply = new Polynomial();
                    while (!(rest.getPolynomial().isEmpty()) && rest.degree() >= p2.degree()) {
                        Monomial m1 = rest.getFirst();
                        Monomial rez = new Monomial(0, 0);
                        rez.setPower(m1.getPower() - m2.getPower());
                        if (m1.getCoeff().intValue() % m2.getCoeff().intValue() == 0) {
                            rez.setCoeff((m1.getCoeff().intValue() / m2.getCoeff().intValue()));
                        } else {
                            rez.setCoeff(m1.getCoeff().doubleValue() / m2.getCoeff().doubleValue());
                        }
                        result.addMonomialToPolynomial(rez);
                        auxForMultiply.empty();
                        auxForMultiply.addMonomialToPolynomial(rez);
                        between = multiplyDouble(auxForMultiply, p2);
                        rest = subtractDouble(rest, between);
                    }
                }
            }
        }
        return new Pair(result, rest);
    }

    //special division for big division problem
    private static Polynomial simpleDivide(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial m : p1.getPolynomial()) {
            Monomial aux = new Monomial(0, 0);
            aux.setPower(m.getPower());
            if (m.getCoeff().intValue() % p2.getPolynomial().get(0).getCoeff().intValue() == 0) {
                aux.setCoeff((m.getCoeff().intValue() / p2.getPolynomial().get(0).getCoeff().intValue()));
            } else {
                aux.setCoeff((((double) m.getCoeff().intValue()) / p2.getPolynomial().get(0).getCoeff().intValue()));
            }
            result.addMonomialToPolynomial(aux);
        }
        return result;
    }
    //special subtract for big division problem
    private static Polynomial subtractDouble(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        ArrayList<Boolean> usedCoeff = new ArrayList();
        correctPolynomial(p1);
        correctPolynomial(p2);
        for (int aux = 0; aux <= p2.getPolynomial().size(); aux++) { //set all monomials to notUsed
            usedCoeff.add(true);
        }
        int index;
        for (Monomial m1 : p1.getPolynomial()) {
            index = p2.getPolynomial().indexOf(m1);
            if (index != -1) {
                usedCoeff.set(index, false);
                Monomial res = new Monomial(0, 0);
                res.setPower(m1.getPower());
                res.setCoeff(m1.getCoeff().doubleValue() - p2.getPolynomial().get(index).getCoeff().doubleValue());
                result.addMonomialToPolynomial(res);
            } else {
                result.addMonomialToPolynomial(m1);
            }
        }
        for (Monomial m2 : p2.getPolynomial()) {
            int indexInPolynomial;
            indexInPolynomial = p2.getPolynomial().indexOf(m2);
            if (usedCoeff.get(indexInPolynomial) == true) {
                m2.setCoeff(-m2.getCoeff().doubleValue());
                result.addMonomialToPolynomial(m2);
            }
        }
        return correctPolynomial(result);
    }
    //special multiply for big division problem
    private static Polynomial multiplyDouble(Polynomial p1, Polynomial p2) {
        correctPolynomial(p1);
        correctPolynomial(p2);
        Polynomial result;
        Polynomial incorrectFormPolynomial = new Polynomial();
        for (Monomial m1 : p1.getPolynomial()) {
            for (Monomial m2 : p2.getPolynomial()) {
                Monomial rez = new Monomial();
                rez.setPower(m1.getPower() + m2.getPower());
                rez.setCoeff(m1.getCoeff().doubleValue() * m2.getCoeff().doubleValue());
                int index = incorrectFormPolynomial.getPolynomial().indexOf(rez);
                if (index != -1) {
                    Monomial aux = incorrectFormPolynomial.getPolynomial().get(index);
                    aux.setCoeff(aux.getCoeff().doubleValue() + m1.getCoeff().intValue() * m2.getCoeff().doubleValue());
                } else {
                    incorrectFormPolynomial.addMonomialToPolynomial(rez);
                }
            }
        }
        result = correctPolynomial(incorrectFormPolynomial);
        return result;
    }
}
