package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Alexandra
 * @since 18/04/2021
 * <p>
 * Main controller that deals with the functioning of the application at the beginning,
 * on the first page
 */
public class ControllerStart {

    @FXML
    private Button clientButton;
    @FXML
    private Button orderButton;
    @FXML
    private Button productButton;

    /**
     * @param actionEvent "sees" which button has been pressed
     * This method deals with opening another page, corresponding to the pressed button
     * it will open another ui and then erase the current page, in order to not have at the end multiple
     * pages of the same type
     */
    @FXML
    public void button_OnClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == clientButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("clientGUI.fxml"));
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageCurrent = (Stage) clientButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open clientGUI");
            }
        }
        if (actionEvent.getSource() == orderButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("orderGUI.fxml"));
                Parent root2 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root2));
                stage.show();
                Stage stageCurrent = (Stage) orderButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open orderGUI");
            }
        }
        if (actionEvent.getSource() == productButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("productGUI.fxml"));
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageCurrent = (Stage) productButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open productGUI");
            }
        }
    }
}