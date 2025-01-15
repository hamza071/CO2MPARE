package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class SettingsController {

    @FXML
    private void handleAccountButton() {
        System.out.println("Account settings geopend.");
        // Voeg hier logica toe voor accountinstellingen
    }

    @FXML
    private void handleGeneralSettingsButton() {
        System.out.println("Algemene instellingen geopend.");
        // Voeg hier logica toe voor algemene instellingen
    }

    @FXML
    private void handleMicrobitButton() {
        System.out.println("Micro:bit verbinding geopend.");
        // Voeg hier logica toe voor Micro:bit
    }

    @FXML
    private void handleHelpButton() {
        System.out.println("Help geopend.");
        // Voeg hier logica toe voor help
    }

    @FXML
    private void handleSignOutButton(ActionEvent event) {
        try {
            // Laad het InlogScherm.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/InlogScherm.fxml"));
            Parent root = loader.load();

            // Krijg de huidige stage via het event-object
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Stel de nieuwe scene in
            stage.setScene(new Scene(root));
            stage.setTitle("Inloggen");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Fout bij het laden van InlogScherm.fxml: " + e.getMessage());
        }
    }

    @FXML
    private void handleHomeIconClick() {
        System.out.println("Navigeren naar Home.");
        // Voeg logica toe voor navigatie naar Home
    }

    @FXML
    private void handleStatsIconClick() {
        System.out.println("Navigeren naar Statistieken.");
        // Voeg logica toe voor navigatie naar Statistieken
    }

    @FXML
    private void handleSettingsIconClick() {
        System.out.println("Instellingen worden opnieuw geopend.");
        // Voeg logica toe voor navigatie naar Instellingen
    }
}
