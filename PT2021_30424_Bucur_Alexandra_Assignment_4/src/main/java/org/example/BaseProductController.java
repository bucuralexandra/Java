package org.example;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.businessLayer.BaseProduct;
import org.example.businessLayer.DeliveryService;
import org.example.businessLayer.MenuItem;
import org.example.dataLayer.Serializator;

import java.io.IOException;

public class BaseProductController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField caloriesText;
    @FXML
    private TextField ratingText;
    @FXML
    private TextField sodiumText;
    @FXML
    private TextField fatText;
    @FXML
    private TextField proteinText;
    @FXML
    private TextField priceText;
    @FXML
    private Label attentionLabel;
    @FXML
    private Button backButton;

    private static MenuItem newMenuItem;
    private static Service service = new ProcessService();

    @FXML
    public void button_onClicked(){
        if(checkInput()){
            newMenuItem = new BaseProduct(nameText.getText(), Double.parseDouble(ratingText.getText()),Integer.parseInt(caloriesText.getText()),
                    Integer.parseInt(proteinText.getText()), Integer.parseInt(fatText.getText()), Integer.parseInt(sodiumText.getText()), Integer.parseInt(priceText.getText()));
            DeliveryService deliveryService = new DeliveryService();
            deliveryService = Serializator.deserialize();
            deliveryService.addProduct(newMenuItem);
            Serializator.serialize(deliveryService);
            errors("Items has been added");
        }else{
            errors("Not a good input");
        }
    }

    private boolean checkInput() {
        boolean ok = true;
        try {
            Integer.parseInt(priceText.getText());
            Double.parseDouble(ratingText.getText());
            Integer.parseInt(caloriesText.getText());
            Integer.parseInt(fatText.getText());
            Integer.parseInt(sodiumText.getText());
            Integer.parseInt(proteinText.getText());
        } catch (NumberFormatException e) {
            ok = false;
        }
        return ok;
    }

    @FXML
    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("admin.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageCurrent = (Stage) backButton.getScene().getWindow();
            stageCurrent.close();
        }catch (IOException exception){
            System.out.println("cannot open");
        }
    }
    private void errors(String message) {
        attentionLabel.setVisible(true);
        attentionLabel.setText(message);
        if (!service.isRunning())
            service.start();
        // If task completed successfully, go back to normal
        service.setOnSucceeded(ee -> {
            attentionLabel.setVisible(false);
            service.reset();
        });
    }

    /**
     * service class that helps with displaying a message for a period of time
     */
    static class ProcessService extends Service<Void> {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000);
                    return null;
                }
            };
        }
    }

}
