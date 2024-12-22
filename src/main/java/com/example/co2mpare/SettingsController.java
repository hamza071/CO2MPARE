// Controller: SettingsController.java
package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

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

    @FXML
    private Label accountLabel;

    @FXML
    private Label generalSettingsLabel;

    @FXML
    private Label microbitLabel;

    @FXML
    private Label helpLabel;

    @FXML
    private Label signOutLabel;

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
    public void initialize() {
        // Initialization code (if needed)
    }

    // Example method to handle account button click
    @FXML
    private void onAccountButtonClick() {
        System.out.println("Account button clicked");
    }

    // Example method to handle sign out button click
    @FXML
    private void onSignOutButtonClick() {
        System.out.println("Sign out button clicked");
    }
}
