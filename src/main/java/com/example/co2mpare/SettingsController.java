package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    private void goToHome(MouseEvent event) {
        switchScene("/com/example/co2mpare/HomeScherm.fxml", event);
    }

    @FXML
    private void goToCompare(MouseEvent event) {
        switchScene("/com/example/co2mpare/CompareScherm.fxml", event);
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        switchScene("/com/example/co2mpare/SettingsScherm.fxml", event);
    }

    @FXML
    private void goToFAQ(MouseEvent event) {
        // Dit zorgt ervoor dat de gebruiker naar het FAQ-scherm wordt gestuurd.
        switchScene("/com/example/co2mpare/FAQScherm.fxml", event);
    }

    @FXML
    private void signOut(MouseEvent event) {
        System.out.println("Gebruiker is uitgelogd.");

        // Logica voor uitloggen (bijv. sessie beëindigen, gebruikersgegevens wissen, etc.)
        // Voeg hier je eigen uitloglogica toe.

        // Navigeer naar het inlogscherm (LoginScherm.fxml)
        switchScene("/com/example/co2mpare/InlogScherm.fxml", event);
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
