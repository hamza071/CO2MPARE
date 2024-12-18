package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SettingsController {

    @FXML
    private ImageView logo;

    @FXML
    private ImageView accountIcon;

    @FXML
    private ImageView generalSettingsIcon;

    @FXML
    private ImageView microbitIcon;

    @FXML
    private ImageView helpIcon;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView statsIcon;

    @FXML
    private ImageView settingsIcon;

    // Actie voor de knoppen
    @FXML
    private Button accountButton;
    @FXML
    private Button generalSettingsButton;
    @FXML
    private Button microbitButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button signOutButton;

    // Deze methoden worden aangeroepen wanneer de knoppen worden aangeklikt
    @FXML
    private void handleAccountAction() {
        System.out.println("Account clicked");
        // Voeg hier actie toe voor account
    }

    @FXML
    private void handleGeneralSettingsAction() {
        System.out.println("General settings clicked");
        // Voeg hier actie toe voor general settings
    }

    @FXML
    private void handleMicrobitAction() {
        System.out.println("Micro:bit clicked");
        // Voeg hier actie toe voor Micro:bit connectie
    }

    @FXML
    private void handleHelpAction() {
        System.out.println("Help clicked");
        // Voeg hier actie toe voor help
    }

    @FXML
    private void handleSignOutAction() {
        System.out.println("Sign out clicked");
        // Voeg hier actie toe voor uitloggen
    }

    @FXML
    private void handleHomeIconAction() {
        System.out.println("Home icon clicked");
        // Voeg hier actie toe voor home navigatie
    }

    @FXML
    private void handleStatsIconAction() {
        System.out.println("Stats icon clicked");
        // Voeg hier actie toe voor statistieken
    }

    @FXML
    private void handleSettingsIconAction() {
        System.out.println("Settings icon clicked");
        // Voeg hier actie toe voor instellingen
    }
}
