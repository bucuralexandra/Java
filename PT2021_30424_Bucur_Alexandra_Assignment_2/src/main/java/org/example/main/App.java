package org.example.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application {
    /**
     * @param primaryStage will have the ui
     * @throws IOException if no file for the .fxml is found
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_2\\src\\main\\java\\org\\example\\View\\screen.fxml").toURI().toURL());
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("view/screen.fxml"));
            Parent root = loader.load();
            var scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Queue simulator"); //set name to the GUI
            primaryStage.show();
        } catch (FileNotFoundException ex) {
            System.out.println("Exception on FXMLLoader.load()");
            System.out.println("  * " + ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

