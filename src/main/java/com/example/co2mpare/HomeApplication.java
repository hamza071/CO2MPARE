package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Laad de FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/HomeScherm.fxml"));
            Parent root = loader.load();

            // Maak een nieuwe scen
            Scene scene = new Scene(root);

            // Stel de primary stage in
            primaryStage.setScene(scene);
            primaryStage.setTitle("Home");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Fout bij het laden van HomeScherm.fxml: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
