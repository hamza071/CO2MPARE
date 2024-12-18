package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SettingsController {

    @FXML
    private ImageView logo, accountIcon, generalSettingsIcon, microbitIcon, helpIcon, signOutIcon, homeIcon, statsIcon, settingsIcon;

    @FXML
    private Button accountButton, generalSettingsButton, microbitButton, helpButton, signOutButton;

    @FXML
    private void handleAccountButtonAction(MouseEvent event) {
        System.out.println("Account button clicked");
        // Handle the action for Account button
    }

    @FXML
    private void handleGeneralSettingsButtonAction(MouseEvent event) {
        System.out.println("General settings button clicked");
        // Handle the action for General settings button
    }

    @FXML
    private void handleMicrobitButtonAction(MouseEvent event) {
        System.out.println("Micro:bit button clicked");
        // Handle the action for Micro:bit button
    }

    @FXML
    private void handleHelpButtonAction(MouseEvent event) {
        System.out.println("Help and support button clicked");
        // Handle the action for Help button
    }

    @FXML
    private void handleSignOutButtonAction(MouseEvent event) {
        System.out.println("Sign out button clicked");
        // Handle the action for Sign out button
    }

    @FXML
    private void handleHomeIconAction(MouseEvent event) {
        System.out.println("Home icon clicked");
        // Handle the action for Home icon
    }

    @FXML
    private void handleStatsIconAction(MouseEvent event) {
        System.out.println("Stats icon clicked");
        // Handle the action for Stats icon
    }

    @FXML
    private void handleSettingsIconAction(MouseEvent event) {
        System.out.println("Settings icon clicked");
        // Handle the action for Settings icon
    }
}
