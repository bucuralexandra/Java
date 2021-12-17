package org.example.presentationLayer;


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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.businessLayer.DeliveryService;
import org.example.businessLayer.MenuItem;
import org.example.businessLayer.Order;
import org.example.dataLayer.Helper;
import org.example.dataLayer.Serializator;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerClient {

    @FXML
    private Button backButton;
    @FXML
    private Button addButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewItemsButton;
    @FXML
    private Button createOrderButton;
    @FXML
    private Button findButton;
    @FXML
    private TableView<MenuItem> itemTable;
    @FXML
    private TableColumn col1;
    @FXML
    private TableColumn col2;
    @FXML
    private TableColumn col3;
    @FXML
    private TableColumn col4;
    @FXML
    private TableColumn col5;
    @FXML
    private TableColumn col6;
    @FXML
    private TableColumn col7;
    @FXML
    private Label attentionLabel;
    @FXML
    private Button createOrderFinalButton;
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
    private Label usernameLabel;


    private static final Service service = new ProcessService();
    private ArrayList<MenuItem> items = new ArrayList<>();
    private Order order = new Order();
    public static ArrayList<MenuItem> itemsOrdered = new ArrayList<>();

    /**
     * Initial method that is called each time the page is opened
     */
    @FXML
    public void initialize() {
        usernameLabel.setText("Username  " + ControllerLogin.client.getUsername());
        col1.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("Title"));
        col2.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("Rating"));
        col3.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Calories"));
        col4.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Protein"));
        col5.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Fat"));
        col6.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Sodium"));
        col7.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Price"));
        DeliveryService deliveryService = new DeliveryService();
        deliveryService = Serializator.deserialize();
        items = deliveryService.getItems();
    }

    /**
     * method that will find an intem or a set of item that will be shown on the UI in order to
     * be seen by the user
     */
    @FXML
    public void find_onClicked() {
        ArrayList<MenuItem> findItems;
        DeliveryService deliveryService = new DeliveryService();
        deliveryService = Serializator.deserialize();
        findItems = deliveryService.findItems(nameText.getText(), ratingText.getText(), caloriesText.getText(),
                proteinText.getText(), fatText.getText(), sodiumText.getText(), priceText.getText());
        addButton.setVisible(false);
        createOrderFinalButton.setVisible(false);
        System.out.println("Finding button");
        if (findItems != null && !findItems.isEmpty()) {
            caloriesText.setVisible(false);
            nameText.setVisible(false);
            ratingText.setVisible(false);
            priceText.setVisible(false);
            proteinText.setVisible(false);
            fatText.setVisible(false);
            sodiumText.setVisible(false);
            ObservableList<MenuItem> dataFound = FXCollections.observableArrayList(findItems);
            itemTable.setItems(dataFound);
            itemTable.setVisible(true);
            findButton.setVisible(false);
        } else {
            errors("No such product");
        }
    }

    /**
     * method that will allow the user to observe the table with all the menu items
     */
    @FXML
    public void viewItems() {
        hideTextBoxes();
        final ObservableList<MenuItem> data = FXCollections.observableArrayList(items);
        itemTable.setItems(data);
        itemTable.setVisible(true);
        addButton.setVisible(false);
        createOrderFinalButton.setVisible(false);
    }

    /**
     * method that will hide from the user the unnecesarry textBoxes from the search part
     */
    private void hideTextBoxes() {
        caloriesText.setVisible(false);
        nameText.setVisible(false);
        ratingText.setVisible(false);
        priceText.setVisible(false);
        proteinText.setVisible(false);
        fatText.setVisible(false);
        sodiumText.setVisible(false);
        findButton.setVisible(false);
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
    }

    /**
     * method that only deals with the specific objects that can be seen or not in the UI
     */
    @FXML
    public void searchItem() {
        itemTable.setVisible(false);
        addButton.setVisible(false);
        createOrderFinalButton.setVisible(false);
        caloriesText.setVisible(true);
        caloriesText.setText("Calories");
        nameText.setVisible(true);
        nameText.setText("Name");
        ratingText.setVisible(true);
        ratingText.setText("Rating");
        priceText.setVisible(true);
        priceText.setText("Price");
        proteinText.setVisible(true);
        proteinText.setText("Protein");
        fatText.setVisible(true);
        fatText.setText("Fat");
        sodiumText.setVisible(true);
        sodiumText.setText("Sodium");
        findButton.setVisible(true);
    }

    /**
     * Method that will add all desired products to a list and then create a new order for the client
     *
     * @param e "sees" what button has been pressed
     * @throws IOException
     */
    @FXML
    public void createOrder(ActionEvent e) throws IOException {
        hideTextBoxes();
        if (e.getSource() == createOrderButton) {
            itemsOrdered.clear();
            final ObservableList<MenuItem> data = FXCollections.observableArrayList(items);
            itemTable.setItems(data);
            itemTable.setVisible(true);
            addButton.setVisible(true);
            createOrderFinalButton.setVisible(true);
            order = new Order();
        }
        if (e.getSource() == addButton) {
            MenuItem itemChosen = itemTable.getSelectionModel().getSelectedItem();
            if (itemChosen == null) {
                errors("Please chose an item");
            } else {
                itemsOrdered.add(itemChosen);
            }
        }
        if (e.getSource() == createOrderFinalButton) {
            DeliveryService deliveryService2 = Serializator.deserialize();
            deliveryService2.addObserver(Helper.controllerEmployee);
            System.out.println(deliveryService2.countObservers());
            deliveryService2.createOrder(order, itemsOrdered, ControllerLogin.client);
            int totalPrice = deliveryService2.computeOrderPrice(order);
            deliveryService2.generateBill(order, totalPrice, itemsOrdered);
            Serializator.serialize(deliveryService2);
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
