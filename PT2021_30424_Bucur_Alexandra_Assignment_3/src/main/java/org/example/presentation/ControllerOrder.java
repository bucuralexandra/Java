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
import org.example.bll.OrderBLL;
import org.example.bll.ProductBLL;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Alexandra
 * @since 18/04/2021
 * This is the class that deals with all the operations regarding the e buttons and actions happening
 * in the interface
 */
public class ControllerOrder {

    @FXML
    private Label attentionLabel;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> clientChoice;
    @FXML
    private ChoiceBox<String> productChoice;
    @FXML
    private TextField nrProduct;
    @FXML
    private Button createButton;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private Label clientLabel;
    @FXML
    private Label prodLabel;
    @FXML
    private Label nrProdLabel;
    @FXML
    private Pane orderDetailsPane;

    private int nrProd;
    private List<Client> clients;
    private final HashMap<Integer, String> clientNames = new HashMap<>();
    private List<Product> products;
    private final HashMap<Integer, String> productNames = new HashMap<>();
    private final ProductBLL productBLL = new ProductBLL();
    private static final Service service = new ProcessService();
    private static final OrderBLL orderBLL = new OrderBLL();
    private int orderNumber = 0;

    /**
     * @method that is called each time this page is created
     * here I get the columns that are required for the table and create the choice boxes
     * with the existing clients and products
     */
    public void initialize() {

        ObservableList<TableColumn<Order, ?>> columns = orderBLL.getCols();
        orderTableView.getColumns().addAll(columns);
        orderTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        createButton.setDisable(true);
        ClientBLL cb = new ClientBLL();
        clients = cb.showAll();
        for (Client c : clients) {
            clientNames.put(c.getId(), c.getName());
        }
        clientChoice.setItems(FXCollections.observableArrayList(clientNames.values()));
        ProductBLL pb = new ProductBLL();
        products = pb.showAll();
        for (Product p : products) {
            productNames.put(p.getId(), p.getName());
        }
        productChoice.setItems(FXCollections.observableArrayList(productNames.values()));
    }

    /**
     * @method that goes back to the main page when the "back" button is clicked
     */
    @FXML
    public void button_onClicked() {
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
            nrProd = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            errors("Please insert a number");
            returnValue = false;
        } finally {
            return returnValue;
        }
    }

    /**
     * @method that will enable/disable some buttons in the app based on the input
     */
    public void handleKeyReleased() {
        boolean ok = !nrProduct.getText().isBlank() && checkInput(nrProduct);
        if (ok) {
            if (clientChoice.getValue() != null && productChoice.getValue() != null)
                createButton.setDisable(false);
        } else errors("Wrong input");
    }

    /**
     * @method that will create a new order and a new bill, based on the input given by the user
     */
    public void button_onClickedCreate() {

        int index = clientChoice.getSelectionModel().getSelectedIndex(); //sees which choice was selected
        Client client = clients.get(index); //gets the client from the list of all clients
        index = productChoice.getSelectionModel().getSelectedIndex();
        Product product = products.get(index);
        orderTableView.setVisible(false);
        nrProduct.setText("");
        if (product.getNrProductsInStock() - nrProd < 0) {
            errors("Not enough in stock");
        } else {
            try {
                orderNumber++; //bill number
                StringBuilder fileName = new StringBuilder();
                fileName.append("bill");
                fileName.append(orderNumber);
                fileName.append(".txt");
                PrintWriter writer = new PrintWriter(fileName.toString(), "UTF-8");
                writer.println(client.toStringAllDetails());
                writer.println(product.toStringAllDetails());
                writer.println("Number products:" + nrProd);
                writer.println("Total cost : " + product.getPrice() * nrProd);
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                System.out.println("File not found for the bill");
            }
            Order order = new Order(client.getId(), product.getId(), nrProd);
            orderBLL.insert(order);
            orderDetailsPane.setVisible(true);
            clientLabel.setText(client.toString());
            prodLabel.setText(product.toString());
            nrProdLabel.setText(String.valueOf(nrProd));
            if (product.getNrProductsInStock() - nrProd == 0) {
                productBLL.delete(product.getId());
            } else {
                product.decreaseProductsInStock(nrProd);
                productBLL.update(product);
            }
        }
    }

    /**
     * method that shows all the orders in the table and decides what will be shown on the view
     *
     * @param actionEvent
     */
    public void button_onClickedShow(ActionEvent actionEvent) {

        List orders = orderBLL.showAll();
        orderDetailsPane.setVisible(false);
        if (orders != null) {
            final ObservableList<Order> data = FXCollections.observableArrayList(orders);
            orderTableView.setItems(data);
            orderTableView.setVisible(true);
        } else errors("No products in the table");
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
