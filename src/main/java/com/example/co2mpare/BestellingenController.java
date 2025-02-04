package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BestellingenController {

    @FXML
    private void goToSettings(MouseEvent event) {
        switchScene("/com/example/co2mpare/SettingsScherm.fxml", event);
    }

    private void switchScene(String fxmlPath, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Haal de huidige stage op via het event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 375, 667)); // Standaard venstergrootte behouden
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van het scherm: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
