package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InlogApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Laad de FXML-bestand en koppel de controller
        FXMLLoader fxmlLoader = new FXMLLoader(InlogApplication.class.getResource("/com/example/co2mpare/login.fxml"));

        // Maak een nieuwe scene van de FXML
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Pas de grootte van het venster aan als dat nodig is

        // Stel de titel van de stage in
        stage.setTitle("Co2mpare - Inloggen");

        // Zet de scene in de stage
        stage.setScene(scene);

        // Toon het venster
        stage.show();
    }

    public static void main(String[] args) {
        launch(); // Start de JavaFX applicatie
    }
}
