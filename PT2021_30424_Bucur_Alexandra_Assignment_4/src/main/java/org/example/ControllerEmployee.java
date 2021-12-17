package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.businessLayer.DeliveryService;
import org.example.dataLayer.Serializator;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;


public class ControllerEmployee implements Observer, Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TextArea orderText;

    /**
     * This method deals with opening another page, corresponding to the pressed button
     * it will open another ui and then erase the current page, in order to not have at the end multiple
     * pages of the same type
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            try {
                FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_4\\src\\main\\java\\org\\example\\presentationLayer\\firstPage.fxml").toURI().toURL());
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageCurrent = (Stage) backButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open mainPage");
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        orderText.appendText(arg.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Helper.controllerEmployee = this;
    }
}
