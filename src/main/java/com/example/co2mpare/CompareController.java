package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CompareController {

    @FXML
    private ImageView logo;

    @FXML
    private Label leaderboardLabel1;

    @FXML
    private Label leaderboardLabel2;

    @FXML
    private Label leaderboardLabel3;

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView statsIcon;

    @FXML
    private ImageView settingsIcon;

    @FXML
    public void initialize() {
        // Initialisatie logica
        System.out.println("CompareController is geladen.");

        // Voorbeeldwaarden instellen
        leaderboardLabel1.setText("10 kg CO₂");
        leaderboardLabel2.setText("7 kg CO₂");
        leaderboardLabel3.setText("5 kg CO₂");
    }


}
