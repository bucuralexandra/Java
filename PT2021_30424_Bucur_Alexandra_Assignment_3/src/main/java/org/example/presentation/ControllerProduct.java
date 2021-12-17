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
import org.example.bll.ProductBLL;
import org.example.model.Product;

import java.io.File;
import java.util.List;

/**
 * @author Alexandra
 * @since 18/04/2021
 * This is the class that deals with all the operations regarding the e buttons and actions happening
 * in the interface
 */
public class ControllerProduct {


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
    private TextField priceField;
    @FXML
    private TextField stockField;
    @FXML
    private Button updateButton;
    @FXML
    private Label productLabel;
    @FXML
    private TableView<Product> productTableView;
    private int nrId;
    private final ProductBLL productBLL = new ProductBLL();
    private static Service service = new ProcessService();

    /**
     * @method that is called each time this page is created
     * here I get the columns that are required for the table
     */
    public void initialize() {
        ObservableList<TableColumn<Product, ?>> columns = productBLL.getCols();
        productTableView.getColumns().addAll(columns);
        productTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * @method that goes back to the main page when the "back" button is clicked
     */
    @FXML
    public void button_onClicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_3\\src\\main\\java\\org\\example\\presentation\\startGUI.fxml").toURI().toURL());
            Parent root1 = loader.load();
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
     * @method that will set visible some buttons and controls on the ui in order to make the editing
     * of a product possible
     */
    @FXML
    public void button_onClicked_editProduct(ActionEvent actionEvent) {
        idTextArea.setVisible(true);
        productTableView.setVisible(false);
        productLabel.setVisible(false);
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
            Product product = productBLL.findProductById(nrId);
            if (product != null) {
                insertButton.setVisible(false);
                updateButton.setVisible(true);
                editPane.setVisible(ok);
                nameField.setText(product.getName());
                priceField.setText(String.valueOf(product.getPrice()));
                stockField.setText(String.valueOf(product.getNrProductsInStock()));
            } else {
                errors("No such product");
                insertButton.setVisible(false);
                editPane.setVisible(false);
            }
        } else editPane.setVisible(ok);
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
     * @method that will create a new product based on the input from the ui and update the product
     * info in the table
     */
    public void button_onClickedUpdate(ActionEvent actionEvent) {
        try {
            Product newProduct = new Product(nrId, nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(stockField.getText()));
            if (!productBLL.update(newProduct))
                errors("Data is not correct");
            else {
                editPane.setVisible(false);
                idTextArea.setVisible(false);
            }
        } catch (NumberFormatException e) {
            errors("Wrong input");
        }
    }

    /**
     * @param actionEvent
     * @method that will create a new client with the info from the ui, check the input
     * and if the fields are correct the product is inserted into the table
     * also some fields are set to enabled/disabled or visible or not
     */
    public void button_onClickedAdd(ActionEvent actionEvent) {

        productTableView.setVisible(false);
        productLabel.setVisible(false);
        idTextArea.setVisible(false);
        editPane.setVisible(true);
        nameField.setText("");
        priceField.setText("");
        stockField.setText("");
        updateButton.setVisible(false);
        insertButton.setVisible(true);
        idDeleteField.setVisible(false);
    }

    /**
     * @param actionEvent
     * @method that creates and inserts a new prod
     */
    public void button_onClickedAddProduct(ActionEvent actionEvent) {
        try {
            Product newProduct = new Product(nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(stockField.getText()));
            if (!productBLL.insert(newProduct))
                errors("Data is not correct");
            else {
                editPane.setVisible(false);
            }
        } catch (NumberFormatException e) {
            errors("Incorrect data");
        }
    }

    /**
     * method that shows all the products in the table and decides what will be shown on the view
     *
     * @param actionEvent
     */
    public void button_onClickedView(ActionEvent actionEvent) {
        idTextArea.setVisible(false);
        editPane.setVisible(false);
        idDeleteField.setVisible(false);
        List<Product> products = productBLL.showAll();
        for (Product product : products) {
            System.out.println(product.toString());
        }
        if (products != null) {
            productLabel.setVisible(true);
            final ObservableList<Product> data = FXCollections.observableArrayList(products);
            productTableView.setItems(data);
            productTableView.setVisible(true);
        } else errors("No products in the table");
    }


    @FXML
    public void button_onClickedDelete() {
        idTextArea.setVisible(false);
        editPane.setVisible(false);
        productLabel.setVisible(false);
        productTableView.setVisible(false);
        idDeleteField.setVisible(true);
    }

    /**
     * method that deletes a product
     */
    @FXML
    public void handleKeyReleasedDelete() {
        boolean ok = !idDeleteField.getText().isBlank() && checkInput(idDeleteField);
        if (ok) {
            Product product = productBLL.findProductById(nrId);
            if (product != null) {
                productBLL.delete(nrId);
                errors("Product deleted");
                idDeleteField.setVisible(false);
            } else {
                errors("No such product");
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
