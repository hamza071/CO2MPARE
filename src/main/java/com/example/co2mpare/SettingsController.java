package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SettingsController {

    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView statsIcon;
    @FXML
    private ImageView settingsIcon;

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

    // De initialize methode kan gebruikt worden voor extra initiële setup als nodig
    @FXML
    private void initialize() {
        // Voeg hier eventuele initialisatie of actie toe die je wilt uitvoeren bij het laden
        // Bijvoorbeeld, stel een actie in voor knoppen als je deze later wilt gebruiken
    }
}
