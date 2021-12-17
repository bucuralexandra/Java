package org.example;

import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.businessLayer.DeliveryService;
import org.example.dataLayer.Serializator;

import java.util.ArrayList;

public class RegisterController {

    @FXML
    private Button backButton;
    @FXML
    private Button createButton;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label attentionLabel;

    private boolean succesfulAccount;
    private static final Service service = new  ProcessService();
    DeliveryService deliveryService = new DeliveryService();

    public void initialize(){
        createButton.setVisible(false);
        ArrayList<String> usersType = new ArrayList<>();
        usersType.add("Client");
        usersType.add("Administrator");
        usersType.add("Employee");
        choiceBox.setItems(FXCollections.observableArrayList(usersType));
    }
    /**
     * This method deals with opening another page, corresponding to the pressed button
     * it will open another ui and then erase the current page, in order to not have at the end multiple
     * pages of the same type
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource() == backButton) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginPage.fxml"));
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageCurrent = (Stage) backButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open loginPage");
            }
        }
        else {
            if(checkInput()){
                addNewUser();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("loginPage.fxml"));
                    Parent root1 = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    Stage stageCurrent = (Stage) createButton.getScene().getWindow();
                    stageCurrent.close();
                } catch (Exception e) {
                    System.out.println("Cannot open loginPage");
                }
            }else {
                errors("Not a good username");
            }
        }
    }

    /**
     * method that checks if an input is correct or not
     * => sees if the email is valid
     * @return
     */
    private boolean checkInput() {
        String password = passwordTextField.getText();
        String username = usernameTextField.getText();
        String type = choiceBox.getValue();
        boolean valid = EmailValidator.validate(username);
        System.out.println(valid);
        return valid;
    }

    /**
     * method that deals with all the fields that are visible or not based on the input
     */
    @FXML
    public void handleKeyReleased() {
         succesfulAccount = (!usernameTextField.getText().isEmpty() || !usernameTextField.getText().trim().isEmpty()) &&
                (!passwordTextField.getText().isEmpty() || !passwordTextField.getText().trim().isEmpty());
        createButton.setVisible(succesfulAccount);
    }

    /**
     * method that creates a new user and will add into the "memory"
     */
    private void addNewUser(){

        String password = passwordTextField.getText();
        String username = usernameTextField.getText();
        String type = choiceBox.getValue();
        User newUser;
        if( type.equals("Client")){
            newUser = new User(username,password,UserType.CLIENT);
        }else {
            if( type.equals("Employee")){
                newUser = new User(username,password,UserType.EMPLOYEE);
            }else {
                newUser = new User(username,password,UserType.ADMINISTRATOR);
            }
        }
        deliveryService = Serializator.deserialize();
        ArrayList<User> users =deliveryService.getUsers();
        if(!users.contains(newUser)){
            deliveryService.getUsers().add(newUser);
            Serializator.serialize(deliveryService);
        }
        else {
            errors("User already exists");
        }
    }
    /**
     * Method to display message in a label
     *
     * @param message what message to be displayed on the screen
     */
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
                    Thread.sleep(3000);
                    return null;
                }
            };
        }
    }
}
