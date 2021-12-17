package org.example.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.bll.ClientBLL;
import org.example.model.Client;

import java.io.File;
import java.util.List;

/**
 * @author Alexandra
 * @since 18/04/2021
 * This is the class that deals with all the operations regarding the e buttons and actions happening
 * in the interface
 */
public class ControllerClient {


    @FXML
    private Label attentionLabel;
    @FXML
    private Button backButton;
    @FXML
    private TextField idTextArea;
    @FXML
    private TextField idDeleteField;
    @FXML
    private Button insertButton;
    @FXML
    private Pane editPane;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button updateButton;
    @FXML
    private Label clientLabel;
    @FXML
    private TableView<Client> tableClients;
    private static Service service = new ProcessService();
    private int nrId;
    private final ClientBLL clientBLL = new ClientBLL();

    /**
     * @method that is called each time this page is created
     * here I get the columns that are required for the table
     */
    public void initialize() {
        ObservableList<TableColumn<Client, ?>> columns = clientBLL.getCols();
        columns.get(0).prefWidthProperty().bind(tableClients.widthProperty().multiply(0.1));
        tableClients.getColumns().addAll(columns);
        tableClients.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * @method that goes back to the main page when the "back" button is clicked
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_3\\src\\main\\java\\org\\example\\presentation\\startGUI.fxml").toURI().toURL());
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageCurrent = (Stage) backButton.getScene().getWindow();
            stageCurrent.close();
        } catch (Exception e) {
            System.out.println("Cannot open startGUI");
        }
    }

    /**
     * @param actionEvent
     * @method that will set visible some buttons and controls on the ui in order to make the editing
     * of a client possible
     */
    @FXML
    public void button_onClicked_editClient(ActionEvent actionEvent) {
        idTextArea.setVisible(true);
        tableClients.setVisible(false);
        clientLabel.setVisible(false);
        idDeleteField.setVisible(false);
        editPane.setVisible(false);
    }

    /**
     * @method that will enable/disable some buttons in the app based on the input
     */
    @FXML
    public void handleKeyReleased() {

        boolean ok = !idTextArea.getText().isBlank() && checkInput(idTextArea);
        if (ok) {
            Client client = clientBLL.findClientById(nrId);
            if (client != null) {
                insertButton.setVisible(false);
                updateButton.setVisible(true);
                editPane.setVisible(ok);
                nameField.setText(client.getName());
                emailField.setText(client.getEmail());
                phoneNumberField.setText(client.getPhoneNumber());
            } else {
                errors("No such client");
                insertButton.setVisible(false);
                editPane.setVisible(false);
            }
        } else editPane.setVisible(false);
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
     * @param textField the text field that will take the input from the user
     * @return true/false depending on the input
     * @method that checks if the input is correct(positive numbers)
     */
    private boolean checkInput(TextField textField) {
        boolean returnValue = true;
        try {
            nrId = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            errors("Please insert a number");
            returnValue = false;
        } finally {
            return returnValue;
        }
    }

    /**
     * @param actionEvent
     * @method that will create a new client based on the input from the ui and update the client
     * info in the table
     */
    public void button_onClickedUpdate(ActionEvent actionEvent) {
        Client newClient = new Client(nrId, nameField.getText(), emailField.getText(), phoneNumberField.getText());
        if (!clientBLL.update(newClient))
            errors("Data is not correct");
        else {
            editPane.setVisible(false);
            idTextArea.setVisible(false);
        }
    }

    /**
     * @param actionEvent
     * @method that will create a new client with the info from the ui, check the input
     * and if the fields are correct the client is inserted into the table
     * also some fields are set to enabled/disabled or visible or not
     */
    public void button_onClickedAdd(ActionEvent actionEvent) {
        tableClients.setVisible(false);
        clientLabel.setVisible(false);
        idTextArea.setVisible(false);
        editPane.setVisible(true);
        nameField.setText("");
        emailField.setText("");
        phoneNumberField.setText("");
        updateButton.setVisible(false);
        insertButton.setVisible(true);
        idDeleteField.setVisible(false);
    }

    /**
     * @param actionEvent
     * @method that creates and inserts a new client
     */
    public void button_onClickedAddClient(ActionEvent actionEvent) {
        Client newClient = new Client(nameField.getText(), emailField.getText(), phoneNumberField.getText());
        if (!clientBLL.insert(newClient))
            errors("Data is not correct");
        else {
            editPane.setVisible(false);
        }
    }

    /**
     * method that shows all the clients in the table and decides what will be shown on the view
     *
     * @param actionEvent
     */
    public void button_onClickedView(ActionEvent actionEvent) {
        idTextArea.setVisible(false);
        editPane.setVisible(false);
        idDeleteField.setVisible(false);
        List clients = clientBLL.showAll();
        if (clients != null) {
            clientLabel.setVisible(true);
            final ObservableList<Client> data = FXCollections.observableArrayList(clients);
            tableClients.setItems(data);
            tableClients.setVisible(true);
        } else errors("No clients in the table");
    }

    @FXML
    public void button_onClikedDelete() {
        idTextArea.setVisible(false);
        editPane.setVisible(false);
        clientLabel.setVisible(false);
        tableClients.setVisible(false);
        idDeleteField.setVisible(true);
    }

    /**
     * method that deletes a client
     */
    @FXML
    public void handleKeyReleasedDelete() {
        boolean ok = !idDeleteField.getText().isBlank() && checkInput(idDeleteField);
        if (ok) {
            Client client = clientBLL.findClientById(nrId);
            if (client != null) {
                clientBLL.delete(nrId);
                errors("Client deleted");
                idDeleteField.setVisible(false);
            } else {
                errors("No such client");
            }
        } else errors("Something wrong with delete");
    }

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
