package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControllerStart {

    @FXML
    private Button loginButton;


    /**
     * This method deals with opening another page, corresponding to the pressed button
     * it will open another ui and then erase the current page, in order to not have at the end multiple
     * pages of the same type
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginPage.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageCurrent = (Stage) loginButton.getScene().getWindow();
            stageCurrent.close();
        } catch (Exception e) {
            System.out.println("Cannot open logIn");
        }
    }
}
