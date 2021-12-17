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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControllerLogin {

    @FXML
    private Button loginButton;
    @FXML
    private Button backButton;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label attentionLabel;

    private static final Service service = new ProcessService();
    public static User client;
    private static DeliveryService deliveryService = new DeliveryService();
    private ArrayList<User>allUsers = new ArrayList<>();

    @FXML
    public void initialize(){
        allUsers.clear();
        loginButton.setVisible(false);
        ArrayList<String> usersType = new ArrayList<>();
        usersType.add("Client");
        usersType.add("Administrator");
        usersType.add("Employee");
        choiceBox.setItems(FXCollections.observableArrayList(usersType));
        deliveryService = Serializator.deserialize();
        allUsers = deliveryService.getUsers();
    }


    /**
     * This method deals with opening another page, corresponding to the pressed button
     * it will open another ui and then erase the current page, in order to not have at the end multiple
     * pages of the same type
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {

        if(actionEvent.getSource() == backButton) {
            try {
                FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_4\\src\\main\\java\\org\\example\\presentationLayer\\firstPage.fxml").toURI().toURL());
                Parent root1 = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageCurrent = (Stage) backButton.getScene().getWindow();
                stageCurrent.close();
            } catch (Exception e) {
                System.out.println("Cannot open startPage");
            }
        }
        else
        {
            deliveryService = Serializator.deserialize();
            allUsers = deliveryService.getUsers();
            if(actionEvent.getSource() == loginButton) {
                if (login()) {
                    if(choiceBox.getValue().equals("Client")){
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("client.fxml"));
                        Parent root1 = loader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));
                        stage.show();
                    } catch (Exception e) {
                        System.out.println("Cannot open clientPage");
                    }
                    }else{
                        if(choiceBox.getValue().equals("Employee")){
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("employee.fxml"));
                                Parent root1 = loader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                stage.show();
                            } catch (Exception e) {
                                System.out.println("Cannot open employeePage");
                            }
                        }else{
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("admin.fxml"));
                                Parent root1 = loader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                stage.show();
                                Stage stageCurrent = (Stage) loginButton.getScene().getWindow();
                                stageCurrent.close();
                            } catch (Exception e) {
                                System.out.println("Cannot open adminPage");
                            }
                        }
                    }
                }
            }
            else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("register.fxml"));
                    Parent root1 = loader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    Stage stageCurrent = (Stage) loginButton.getScene().getWindow();
                    stageCurrent.close();
                } catch (Exception e) {
                    System.out.println("Cannot open register");
                }
            }
        }
    }

    /**
     * method to see which input was introduced
     */
    @FXML
    public void handleKeyReleased() {
        boolean ok = (!usernameTextField.getText().isEmpty() || !usernameTextField.getText().trim().isEmpty()) &&
                (!passwordTextField.getText().isEmpty() || !passwordTextField.getText().trim().isEmpty());
        loginButton.setVisible(ok);
    }

    /**
     * orivate method that will check if the input is correct => if the username and password exists in
     * the "database"
     * @return true/false based on the input data from the user
     */
    private boolean login(){

        String password = passwordTextField.getText();
        String username = usernameTextField.getText();
        boolean registration = false;
        String type = choiceBox.getValue();
        User userLogin;
        if( type.equals("Client")){
             userLogin = new User(username,password,UserType.CLIENT);
             client = new User(username,password,UserType.CLIENT);
        }else {
            if( type.equals("Employee")){
                 userLogin = new User(username,password,UserType.EMPLOYEE);
            }else {
                 userLogin = new User(username,password,UserType.ADMINISTRATOR);
            }
        }

        for(User user: allUsers){
            System.out.println(user.toString());
        }
        System.out.println(allUsers.size());
        if(allUsers != null && !allUsers.isEmpty()){
            for(User user: allUsers){
                if(user.equals(userLogin))
                    return true;
            }
            registration = false;
            errors("Wrong username or password.Try again");
        }else {
            registration = false;
            errors("Wrong username or password.Try again");
        }
        return registration;
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
                    Thread.sleep(2000);
                    return null;
                }
            };
        }
    }
}
