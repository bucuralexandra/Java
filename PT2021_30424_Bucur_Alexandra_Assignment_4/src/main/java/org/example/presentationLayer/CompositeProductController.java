package org.example.presentationLayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.businessLayer.CompositeProduct;
import org.example.businessLayer.DeliveryService;
import org.example.businessLayer.MenuItem;
import org.example.dataLayer.Serializator;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alexandra
 * @since 20/05/2021
 */
public class CompositeProductController {

    @FXML
    private TextField nameText;
    @FXML
    private TableView<MenuItem> itemsTable;
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
    private Button addButton;
    @FXML
    private Button moreItemButton;
    @FXML
    private TextArea composition;

    private ArrayList<MenuItem> items = new ArrayList<>();
    private static MenuItem newMenuItemComposed;
    @FXML
    private Button backButton;


    /**
     * method that is called every time this kind of page is opened
     */
    @FXML
    public void initialize() {
        composition.setText("Items:");
        col1.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("Title"));
        col2.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Double>("Rating"));
        col3.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Integer>("Calories"));
        col4.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Integer>("Protein"));
        col5.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Integer>("Fat"));
        col6.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Integer>("Sodium"));
        col7.setCellValueFactory(new PropertyValueFactory<org.example.businessLayer.MenuItem, Integer>("Price"));
        DeliveryService deliveryService = new DeliveryService();
        deliveryService = Serializator.deserialize();
        final ObservableList<MenuItem> data = FXCollections.observableArrayList(deliveryService.getItems());
        itemsTable.setItems(data);
        itemsTable.setVisible(true);
    }

    /**
     * method that adds the new product into the list
     * it also adds new base products into a "menu"
     *
     * @param event
     */
    @FXML
    public void button_onClicked(ActionEvent event) {
        if (event.getSource() == addButton) {
            newMenuItemComposed = new CompositeProduct(nameText.getText(), items);
            DeliveryService deliveryService = new DeliveryService();
            deliveryService = Serializator.deserialize();
            deliveryService.addProduct(newMenuItemComposed);
            Serializator.serialize(deliveryService);
            composition.setText("Item has been created");
        } else {
            MenuItem item = itemsTable.getSelectionModel().getSelectedItem();
            if (item != null) {
                items.add(item);
                composition.appendText("\n" + item.toString());
            }
        }
    }

    /**
     * method that will go back to the main page
     */
    @FXML
    public void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("admin.fxml"));
            Parent root1 = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageCurrent = (Stage) backButton.getScene().getWindow();
            stageCurrent.close();
        } catch (IOException exception) {
            System.out.println("cannot open");
        }
    }

}
