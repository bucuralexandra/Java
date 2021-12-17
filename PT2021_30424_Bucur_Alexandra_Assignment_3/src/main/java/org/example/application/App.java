package org.example.application;

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
 * @since 12/04/2021
 */
public class App extends Application {

    /**
     * @param primaryStage will have the ui
     * @throws IOException if no file for the .fxml is found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_3\\src\\main\\java\\org\\example\\presentation\\startGUI.fxml").toURI().toURL());
            Parent root = loader.load();
            var scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Order management"); //set name to the GUI
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
    }
}

