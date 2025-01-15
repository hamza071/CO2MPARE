package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    @FXML
    private ImageView logo;

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

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView statsIcon;

    @FXML
    private ImageView settingsIcon;

    @FXML
    public void initialize() {
        // Initialisatie van de controller
        System.out.println("SettingsController is geladen.");
    }

    @FXML
    private void handleAccountButton() {
        System.out.println("Account-knop geklikt!");
        // Voeg hier de functionaliteit toe voor Account
    }

    @FXML
    private void handleGeneralSettingsButton() {
        System.out.println("Algemene instellingen-knop geklikt!");
        // Voeg hier de functionaliteit toe voor Algemene Instellingen
    }

    @FXML
    private void handleMicrobitButton() {
        System.out.println("Micro:bit-knop geklikt!");
        // Voeg hier de functionaliteit toe voor Micro:bit
    }

    @FXML
    private void handleHelpButton() {
        System.out.println("Help-knop geklikt!");
        // Voeg hier de functionaliteit toe voor Hulp
    }

    @FXML
    private void handleSignOutButton() {
        System.out.println("Uitlog-knop geklikt!");
        // Voeg hier de functionaliteit toe voor Uitloggen
    }

    @FXML
    private void handleHomeIconClick(MouseEvent event) {
        System.out.println("Home-icoon geklikt!");
        // Voeg hier de functionaliteit toe voor navigatie naar Home
    }

    @FXML
    private void handleStatsIconClick(MouseEvent event) {
        System.out.println("Statistieken-icoon geklikt!");
        // Voeg hier de functionaliteit toe voor navigatie naar Statistieken
    }

    @FXML
    private void handleSettingsIconClick(MouseEvent event) {
        System.out.println("Instellingen-icoon geklikt!");
        // Dit is mogelijk overbodig, omdat de gebruiker al op het Instellingen-scherm is
    }
}
