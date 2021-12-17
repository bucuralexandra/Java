package com.PT.project.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    /**
     * @param primaryStage will have the ui
     * @throws IOException if no file for the .fxml is found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(new File("D:\\Facultate\\Year 2\\sem2\\PT\\Assignments\\PT2021_30424_Bucur_Alexandra_Assignment_1\\src\\main\\java\\com\\PT\\project\\View\\UI.fxml").toURI().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Polynomial Calculator"); //set name to the GUI
            primaryStage.setMinWidth(500); //set dimensions
            primaryStage.setMaxWidth(500);
            primaryStage.setMinHeight(400);
            primaryStage.setMaxHeight(400);
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