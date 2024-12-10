package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InlogApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Laad de FXML
        FXMLLoader fxmlLoader = new FXMLLoader(InlogApplication.class.getResource("/com/example/co2mpare/InlogScherm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Zet de titel van het venster
        stage.setTitle("Inloggen - Co2mpare");

        // Zet de scene en toon het venster
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Start de applicatie
        launch();
    }
}
