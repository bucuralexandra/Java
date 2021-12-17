package com.PT.project.Controller;

import com.PT.project.Model.Monomial;
import com.PT.project.Model.Operations;
import com.PT.project.Model.Pair;
import com.PT.project.Model.Polynomial;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController {
    private static Service service = new ProcessService();
    private Polynomial pol1;
    private Polynomial pol2;
    private Polynomial result;
    //regex for several pattern matching
    private static final String SPACE_CONSTANT_REGEX = "\\s+";
    private static final String MONOMIAL_REGEX = "([+-]?[\\d]*[x]?\\^?\\d*)";
    private static final String MONOMIAL_PART_REGEX = "([+-]?[\\d]*)(x?)\\^?(\\d*)";
    private static final String NOT_ACCEPTED_INPUT = "([a-wy-zA-WY-Z]|[!@#$%&*;:\"'`()\\\\_=|<>?{}\\[\\]~,./])";
    private static final String IS_STRING = "[+-]*\\d*?\\d+";
    /**
     * FXML related fields to intermediate to the UI
     */
    @FXML
    private Button addButton;
    @FXML
    private Button substractButton;
    @FXML
    private Button multiplyButton;
    @FXML
    private Button divideButton;
    @FXML
    private Button derivateButton;
    @FXML
    private Button integrateButton;
    @FXML
    private Button infoButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Button okButton1;
    @FXML
    private Button okButton2;
    @FXML
    private TextField firstPolynom;
    @FXML
    private TextField secondPolynom;
    @FXML
    private Label resultLabel;
    @FXML
    private Label errorLabel;

    /**
     *  init fct, sets all buttons to disabled and prepares the app at the begining
     */
    @FXML
    public void initialize() {

        addButton.setDisable(true);
        substractButton.setDisable(true);
        divideButton.setDisable(true);
        multiplyButton.setDisable(true);
        derivateButton.setDisable(true);
        integrateButton.setDisable(true);
        okButton1.setDisable(true);
        okButton2.setDisable(true);
        pol1 = new Polynomial();
        pol2 = new Polynomial();
        result = new Polynomial();
    }

    /**
     * this function "sees" if anything was included in the text box,
     * and according to that changes the behaviour for some buttons
     */
    @FXML
    public void handleKeyReleased() {
        String text1 = firstPolynom.getText(); //get what is written in the first textField
        String text2 = secondPolynom.getText();//get what is written in the second textField
        boolean firstPolynom = text1.isBlank(); //tests to see if the input is empty or not
        boolean secondPolynom = text2.isBlank();
        boolean bothPolynomials;
        okButton1.setDisable(firstPolynom); //if first textField contains text, okButton1 is set to enables
        okButton2.setDisable(secondPolynom);  //if second textField contains text, okButton2 is set to enables
        //buttons derivative and integrate will be available after pol1 is introduced,
        //buttons add,subtract, multiply,divide available after BOTH pol1 and pol2 are introduced
        derivateButton.setDisable(firstPolynom);
        integrateButton.setDisable(firstPolynom);
        if (!firstPolynom && !secondPolynom) {
            bothPolynomials = false;
        } else {
            bothPolynomials = true;
        }
        addButton.setDisable(bothPolynomials);
        substractButton.setDisable(bothPolynomials);
        divideButton.setDisable(bothPolynomials);
        multiplyButton.setDisable(bothPolynomials);
        resultLabel.setText(""); //reset resultLabel after erasing things from textField
        errorLabel.setText("");
    }

    /**
     * hadles the behaviour for the ok buttons, also there will be data validation
     */
    @FXML
    public void buttonOK_onClicked(ActionEvent e) {

        if (e.getSource() == okButton1) {
            if (!validateInput(firstPolynom.getText(), 1)) {
                errors("First polynomial is in incorrect form");
            }
        }
        if (e.getSource() == okButton2) {
            if (!validateInput(secondPolynom.getText(), 2)) {
                errors("Second polynomial is in incorrect form");
            }
        }
    }

    /**
     * deals with operation buttons and the info button
     */
    @FXML
    public void button_onClicked(ActionEvent e) {

        //performs addition
        if (e.getSource() == addButton) {
            if (validateInput(firstPolynom.getText(), 1) && validateInput(secondPolynom.getText(), 2)) {
                result = Operations.add(pol1, pol2);
                resultLabel.setText(result.toString());
            } else {
                errors("Could not perform addition.Try again");
            }
        }
        //performs subtraction
        if (e.getSource() == substractButton) {
            if (validateInput(firstPolynom.getText(), 1) && validateInput(secondPolynom.getText(), 2)) {
                result = Operations.substract(pol1, pol2);
                resultLabel.setText(result.toString());
            } else {
                errors("Could not perform subtraction.Try again");
            }
        }
        if (e.getSource() == multiplyButton) { //performs multiplication
            if (validateInput(firstPolynom.getText(), 1) && validateInput(secondPolynom.getText(), 2)) {
                result = Operations.multiply(pol1, pol2);
                resultLabel.setText(result.toString());
            } else {
                errors("Could not perform multiplication.Try again!");
            }
        }
        if (e.getSource() == divideButton) { //performs division
            if (validateInput(firstPolynom.getText(), 1) && validateInput(secondPolynom.getText(), 2)) {
                Polynomial rest = new Polynomial();
                Pair pair;
                pair = Operations.divide(pol1, pol2);
                if (pair.getPolynomial1() == null) {
                    errors("Division by zero");
                } else {
                    resultLabel.setText(pair.toString()); //returns a pair of quotient and remainder
                }
            } else {
                errors("Could not perform division.Try again!");
            }
        }
        if (e.getSource() == derivateButton) { //performs derivation of pol1
            if (validateInput(firstPolynom.getText(), 1)) {
                result = Operations.derivate(pol1);
                resultLabel.setText(result.toString());
            } else {
                errors("Could not perform derivation.Try again!");
            }
        }
        if (e.getSource() == integrateButton) { //performs integration of pol1
            if (validateInput(firstPolynom.getText(), 1)) {
                result = Operations.integrate(pol1);
                resultLabel.setText(result.toString());
            } else {
                errors("Could not perform integration.Try again!");
            }
        }
        if (e.getSource() == infoButton) { //all buttons that are under the label must dissapear
            infoLabel.setVisible(true);
            okButton1.setVisible(false);
            okButton2.setVisible(false);
            if (!service.isRunning()) //new service is started
                service.start();
            // If task completed successfully, go back to normal
            service.setOnSucceeded(ee -> { //after the amount of time-> show the buttons again
                infoLabel.setVisible(false);
                okButton1.setVisible(true);
                okButton2.setVisible(true);
                service.reset();
            });
        }
    }

    /**
     * Method to display message in a label
     * @param message what message to be displayed on the screen
     */
    private void errors(String message) {
        errorLabel.setVisible(true);
        errorLabel.setText(message);
        if (!service.isRunning())
            service.start();
        // If task completed successfully, go back to normal
        service.setOnSucceeded(ee -> {
            errorLabel.setVisible(false);
            service.reset();
        });
    }

    /**
     * this method takes data as string and transforms it into a polynomial
     * @param pol string pol to be tested
     * @param mode include result in pol1 or pol2
     * @return true/false
     */
    protected boolean validateInput(String pol, int mode) {

        initPol(mode);
        int number;
        int power;
        Pattern initialPattern = Pattern.compile(NOT_ACCEPTED_INPUT);
        Matcher initialMatcher = initialPattern.matcher(pol);
        while (initialMatcher.find()) {
            return false;
        }
        String newS = pol.replaceAll(SPACE_CONSTANT_REGEX, "");
        if (newS.contains("xx") || newS.contains("^^") || newS.contains("^x")) return false;
        Pattern pattern = Pattern.compile(MONOMIAL_REGEX);
        Matcher matcher = pattern.matcher(newS);
        while (!matcher.hitEnd()) { //parse string into monomials
            matcher.find();
            String currentMonomialGroup = matcher.group();
            Pattern patternMonomial = Pattern.compile(MONOMIAL_PART_REGEX);
            Matcher matcherMonomial = patternMonomial.matcher(currentMonomialGroup);
            if (currentMonomialGroup.length() == 1 &&
                    (currentMonomialGroup.charAt(0) == '+' || currentMonomialGroup.charAt(0) == '-')) { //if more + - signs are there
                return false;
            }
            if (matcherMonomial.find()) {
                try {
                    String coef = matcherMonomial.group(1); //coeff group
                    if (isANumber(coef)) {
                        number = Integer.valueOf(coef); //how to store
                    } else {
                        number = Integer.valueOf(coef + "1");
                    }
                } catch (IllegalStateException e) {
                    return false;
                }
                try {
                    String pwr = matcherMonomial.group(3);
                    if (isANumber(pwr)) {
                        power = Integer.valueOf(pwr);
                    } else {
                        if (matcherMonomial.group(2).equals(""))
                            power = 0;
                        else {
                            if (matcherMonomial.group(2).equals("x"))
                                power = 1;
                            else return false;
                        }
                    }
                } catch (IllegalStateException e) {
                    return false;
                }
                Monomial m = new Monomial(power, number);
                addInput(m, number, power, mode);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Initialize the polynomial
     */
    private void initPol(int mode) {
        if (mode == 1) {
            if (pol1 != null)
                pol1.empty();
        } else {
            if (pol2 != null)
                pol2.empty();
        }
        if (result != null)
            result.empty();
    }

    /**
     * Adds new monomial to polynomial
     * @param m monomial to be added
     * @param coefficient coeff and power to be added
     * @param mode in which pol
     */
    private void addInput(Monomial m, int coefficient, int exponent, int mode) {
        if (mode == 1) {
            if (pol1 != null && pol1.getPolynomial() != null) {
                int index = pol1.getPolynomial().indexOf(m);
                if (index != -1) {
                    pol1.getPolynomial().get(index).addMon(m.getCoeff().intValue());
                } else {
                    pol1.addMonomialToPolynomial(exponent, coefficient);
                }
            }
        } else {
            if (pol2 != null && pol2.getPolynomial() != null) {
                int index = pol2.getPolynomial().indexOf(m);
                if (index != -1) {
                    pol2.getPolynomial().get(index).addMon(m.getCoeff().intValue());
                } else {
                    pol2.addMonomialToPolynomial(exponent, coefficient);
                }
            }
        }
    }

    //tests to see if it is a number
    private static boolean isANumber(String str) {
        return str.matches(IS_STRING);
    }

    //class that deals with threads
    static class ProcessService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(9000);
                    return null;
                }
            };
        }
    }
}
