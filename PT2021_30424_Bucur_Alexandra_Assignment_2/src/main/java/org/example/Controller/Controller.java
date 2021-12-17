package org.example.Controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Model.SelectionPolicy;
import org.example.Model.TimeBounds;

public class Controller {

    private static Service service = new ProcessService();
    @FXML
    private Button startSimulationButton;
    @FXML
    private TextField nrClientsTextField;
    @FXML
    private TextField nrQueuesTextField;
    @FXML
    private TextField simulationTimeTextField;
    @FXML
    private TextField minArrivalTimeTextField;
    @FXML
    private TextField maxArrivalTimeTextField;
    @FXML
    private TextField minServiceTimeTextField;
    @FXML
    private TextField maxServiceTimeTextField;

    @FXML
    private Label attentionLabel;

    @FXML
    TextArea resultTextArea;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Label finalLabel;

    private int nrClients;
    private int nrQueues;
    private int simulationTime;
    private TimeBounds arrivalBounds = new TimeBounds();
    private TimeBounds serviceBounds = new TimeBounds();
    private SimulationManager simulationManager;

    @FXML
    public void initialize() {
        choiceBox.setVisible(false);
        choiceBox.getItems().add("Shortest queue");
        choiceBox.getItems().add("Shortest time");
    }

    @FXML
    public void handleKeyReleased_nrClients() {
        boolean ok = !nrClientsTextField.getText().isBlank() && checkInput(nrClientsTextField);
        nrQueuesTextField.setDisable(!ok);
        if (nrClientsTextField.getText().isBlank()) {
            attentionLabel.setText("");
            nrQueuesTextField.setDisable(true);
            nrQueuesTextField.setText("");
            simulationTimeTextField.setDisable(true);
            simulationTimeTextField.setText("");
            minArrivalTimeTextField.setDisable(true);
            minArrivalTimeTextField.setText("");
            maxArrivalTimeTextField.setDisable(true);
            maxArrivalTimeTextField.setText("");
            minServiceTimeTextField.setDisable(true);
            minServiceTimeTextField.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_nrQueues() {
        boolean ok = !nrQueuesTextField.getText().isBlank() && checkInput(nrQueuesTextField);
        simulationTimeTextField.setDisable(!ok);
        if (nrQueuesTextField.getText().isBlank()) {
            attentionLabel.setText("");
            simulationTimeTextField.setDisable(true);
            simulationTimeTextField.setText("");
            minArrivalTimeTextField.setDisable(true);
            minArrivalTimeTextField.setText("");
            maxArrivalTimeTextField.setDisable(true);
            maxArrivalTimeTextField.setText("");
            minServiceTimeTextField.setDisable(true);
            minServiceTimeTextField.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_simulation() {
        boolean ok = !simulationTimeTextField.getText().isBlank() && checkInput(simulationTimeTextField);
        minArrivalTimeTextField.setDisable(!ok);
        if (simulationTimeTextField.getText().isBlank()) {
            attentionLabel.setText("");
            minArrivalTimeTextField.setDisable(true);
            minArrivalTimeTextField.setText("");
            maxArrivalTimeTextField.setDisable(true);
            maxArrivalTimeTextField.setText("");
            minServiceTimeTextField.setDisable(true);
            minServiceTimeTextField.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_minArrival() {
        boolean ok = !minArrivalTimeTextField.getText().isBlank() && checkInput(minArrivalTimeTextField);
        maxArrivalTimeTextField.setDisable(!ok);
        if (minArrivalTimeTextField.getText().isBlank()) {
            attentionLabel.setText("");
            maxArrivalTimeTextField.setDisable(true);
            maxArrivalTimeTextField.setText("");
            minServiceTimeTextField.setDisable(true);
            minServiceTimeTextField.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_maxArrival() {
        boolean ok = !maxArrivalTimeTextField.getText().isBlank() && checkInput(maxArrivalTimeTextField);
        minServiceTimeTextField.setDisable(!ok);
        if (maxArrivalTimeTextField.getText().isBlank()) {
            attentionLabel.setText("");
            minServiceTimeTextField.setDisable(true);
            minServiceTimeTextField.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_minService() {
        boolean ok = !minServiceTimeTextField.getText().isBlank() && checkInput(minServiceTimeTextField);
        maxServiceTimeTextField.setDisable(!ok);
        if (minServiceTimeTextField.getText().isBlank()) {
            attentionLabel.setText("");
            maxServiceTimeTextField.setDisable(true);
            maxServiceTimeTextField.setText("");
            startSimulationButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased_maxService() {
        boolean ok = !maxServiceTimeTextField.getText().isBlank() && checkInput(maxServiceTimeTextField);
        startSimulationButton.setDisable(!ok);
        choiceBox.setVisible(ok);
    }

    @FXML
    public void button_OnClicked(ActionEvent e) {
        String value = choiceBox.getValue();
        if (value.equals("Shortest time")) {
            simulationManager = new SimulationManager(simulationTime, serviceBounds, arrivalBounds, nrQueues, nrClients, SelectionPolicy.SHORTEST_TIME, resultTextArea);
        } else {
            simulationManager = new SimulationManager(simulationTime, serviceBounds, arrivalBounds, nrQueues, nrClients, SelectionPolicy.SHORTEST_QUEUE, resultTextArea);
        }
        Thread thread = new Thread(simulationManager);
        thread.start();
        choiceBox.setDisable(true);
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

    private boolean checkInput(TextField textField) {
        boolean returnValue = false;
        try {
            if (textField.getId().equals(nrClientsTextField.getId())) {
                nrClients = Integer.parseInt(textField.getText());
                if (nrClients < 0) {
                    errors("Input a positive number of clients");
                    return false;
                }
            } else if (textField.getId().equals(nrQueuesTextField.getId())) {
                nrQueues = Integer.parseInt(textField.getText());
                if (nrQueues < 0) {
                    errors("Input a positive number of queues");
                    return false;
                }
            } else if (textField.getId().equals(simulationTimeTextField.getId())) {
                simulationTime = Integer.parseInt(textField.getText());
                if (simulationTime < 0) {
                    errors("Input a positive number for the simulation time");
                    return false;
                }
            } else if (textField.getId().equals(minArrivalTimeTextField.getId())) {
                if (checkBounds(Integer.parseInt(textField.getText()), 1, 0)) {
                    arrivalBounds.setMinTime(Integer.parseInt(textField.getText()));
                } else {
                    errors("Input a valid number, positive and smaller \n that the simulation time");
                    return false;
                }
            } else if (textField.getId().equals(maxArrivalTimeTextField.getId())) {
                if (checkBounds(Integer.parseInt(textField.getText()), 2, arrivalBounds.getMinTime())) {
                    arrivalBounds.setMaxTime(Integer.parseInt(textField.getText()));
                } else {
                    errors("Input a valid number, positive, smaller that the \nsimulation time and min arrival time");
                    return false;
                }
            } else if (textField.getId().equals(minServiceTimeTextField.getId())) {
                if (checkBounds(Integer.parseInt(textField.getText()), 1, 0)) {
                    serviceBounds.setMinTime(Integer.parseInt(textField.getText()));
                } else {
                    errors("Input a valid number, positive and smaller \n that the simulation time");
                    return false;
                }
            } else if (textField.getId().equals(maxServiceTimeTextField.getId())) {
                if (checkBounds(Integer.parseInt(textField.getText()), 2, serviceBounds.getMinTime())) {
                    serviceBounds.setMaxTime(Integer.parseInt(textField.getText()));
                } else {
                    errors("Input a valid number, positive, smaller that the \nsimulation time and min service time");
                    return false;
                }
            }
            returnValue = true;
        } catch (NumberFormatException e) {
            errors("Please insert a number");
            returnValue = false;
        } finally {
            return returnValue;
        }
    }

    private boolean checkBounds(int time, int mode, int min) {
        if (mode == 1) {
            if (time < 0 || time > simulationTime)
                return false;
            return true;
        } else {
            if (time < 0 || time >= simulationTime || time <= min)
                return false;
            return true;
        }
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
