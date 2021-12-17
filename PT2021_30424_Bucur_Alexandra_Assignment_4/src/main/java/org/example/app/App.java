package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Alexandra
 * @since 12/05/2021
 */
public class App extends Application {

    /**
     * @param primaryStage will have the ui
     * @throws IOException if no file for the .fxml is found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Alexandra\\Desktop\\PT2021_30424_Bucur_Alexandra_Assignment_4\\src\\main\\java\\org\\example\\presentationLayer\\firstPage.fxml").toURI().toURL());
            Parent root = loader.load();
            var scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("FirstPage"); //set name to the GUI
            primaryStage.show();
        } catch (FileNotFoundException ex) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * " + ex);
        }
    }

    /**
     * this method starts the whole application
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
       launch(args);
  /*  FileReaderCSV fileReader = new FileReaderCSV();
        HashSet<MenuItem> items = fileReader.processInputFile();
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setItems(items);
        System.out.println(deliveryService.getItems().size());
        Serializator.serialize(deliveryService);*/

    }
}

