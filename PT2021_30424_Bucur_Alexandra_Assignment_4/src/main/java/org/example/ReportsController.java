package org.example;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.businessLayer.DeliveryService;
import org.example.dataLayer.Serializator;

import java.time.LocalDate;
import java.util.Calendar;

public class ReportsController {

    @FXML
    private Button timeButton;
    @FXML
    private Button productNumberButton;
    @FXML
    private Button clientButton;
    @FXML
    private Button productsInADayButton;
    @FXML
    private TextArea infoArea;
    @FXML
    private TextField minHText;
    @FXML
    private TextField maxHText;
    @FXML
    private TextField nrOfTimesText;
    @FXML
    private TextField totalCostText;
    @FXML
    private Button generateButton;
    @FXML
    private DatePicker datePick;

    private void setTextFields(){
        minHText.setText("min");
        maxHText.setText("max");
        nrOfTimesText.setText("nr times");
        totalCostText.setText("total cost");
    }

    @FXML
    public void timeInterval_onAction(){
        setTextFields();
        infoArea.setText("");
        minHText.setVisible(true);
        maxHText.setVisible(true);
    }

    @FXML
    public void nrTimes_onAction(){
        setTextFields();
        infoArea.setText("");
        nrOfTimesText.setVisible(true);
        generateButton.setVisible(true);
    }

    @FXML
    public void timeTimesAndTotalCost_onAction(){
        setTextFields();
        infoArea.setText("");
        nrOfTimesText.setVisible(true);
        totalCostText.setVisible(true);
    }

    @FXML
    public void date_onAction(){
        setTextFields();
        infoArea.setText("");
        datePick.setVisible(true);
        generateButton.setVisible(true);
    }

    @FXML
    public void generateOnAction(){
        infoArea.setText("");
        if(minHText.isVisible() && maxHText.isVisible() && Integer.parseInt(minHText.getText()) < Integer.parseInt(maxHText.getText())){
            if(checkInput(minHText) && checkInput(maxHText)) {
                DeliveryService deliveryService = new DeliveryService();
                deliveryService = Serializator.deserialize();
                System.out.println(deliveryService.generateReportsTimeInterval(Integer.parseInt(minHText.getText()),
                        Integer.parseInt(maxHText.getText())));
                infoArea.setText(deliveryService.generateReportsTimeInterval(Integer.parseInt(minHText.getText()),
                        Integer.parseInt(maxHText.getText())));
                minHText.setVisible(false); maxHText.setVisible(false);
            }
        }
        else {
            if(nrOfTimesText.isVisible() && !totalCostText.isVisible()){ //products ordered more than a specific nr of times
               if(checkInput(nrOfTimesText)) {
                   DeliveryService deliveryService = new DeliveryService();
                   deliveryService = Serializator.deserialize();
                   System.out.println(deliveryService.generateProductNrTimes(Integer.parseInt(nrOfTimesText.getText())));
                   infoArea.setText(deliveryService.generateProductNrTimes(Integer.parseInt(nrOfTimesText.getText())));
                   nrOfTimesText.setVisible(false);
               }
            }
            else{
                if(nrOfTimesText.isVisible() && totalCostText.isVisible()){
                    if(checkInput(nrOfTimesText) && checkInput(totalCostText)) {
                        DeliveryService deliveryService = new DeliveryService();
                        deliveryService = Serializator.deserialize();
                        System.out.println(deliveryService.generateClientsNrTimes(Integer.parseInt(nrOfTimesText.getText()), Integer.parseInt(totalCostText.getText())));
                        infoArea.setText(deliveryService.generateClientsNrTimes(Integer.parseInt(nrOfTimesText.getText()), Integer.parseInt(totalCostText.getText())));
                        nrOfTimesText.setVisible(false);
                        totalCostText.setVisible(false);
                    }
                }
                else {
                    LocalDate date = datePick.getValue();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
                    DeliveryService deliveryService = new DeliveryService();
                    deliveryService = Serializator.deserialize();
                    System.out.println(deliveryService.generateReports(calendar));
                    infoArea.setText(deliveryService.generateReports(calendar));
                    datePick.setVisible(false);
                }
            }
        }
    }
    private boolean checkInput(TextField  textField){
        boolean ok = false;
        try {
            Integer.parseInt(textField.getText());
            ok = true;
        }catch (NumberFormatException e){
            ok = false;
        }
        return ok;
    }

}
