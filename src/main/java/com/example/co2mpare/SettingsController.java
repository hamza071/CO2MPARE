package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SettingsController {

    @FXML
    private ImageView logo;  // Zorg ervoor dat dit overeenkomt met de fx:id in de FXML

    @FXML
    private Button accountButton;  // De knoppen voor de instellingen

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

    // Zorg ervoor dat de initialize() methode wordt aangeroepen bij het laden van de FXML
    @FXML
    public void initialize() {
        // Laad de afbeelding via de controller
        if (logo != null) {
            logo.setImage(new Image(getClass().getResourceAsStream("/Images/logo.png")));
        }

        // Event handlers voor knoppen zonder gebruik van events
        if (accountButton != null) accountButton.setText("Account");
        if (generalSettingsButton != null) generalSettingsButton.setText("General Settings");
        if (microbitButton != null) microbitButton.setText("Microbit");
        if (helpButton != null) helpButton.setText("Help");
        if (signOutButton != null) signOutButton.setText("Sign Out");

        // Event handlers voor de iconen
        if (homeIcon != null) homeIcon.setOpacity(0.5); // Verandert de zichtbaarheid van het icoon als voorbeeld
        if (statsIcon != null) statsIcon.setOpacity(0.5);
        if (settingsIcon != null) settingsIcon.setOpacity(0.5);
    }
}
