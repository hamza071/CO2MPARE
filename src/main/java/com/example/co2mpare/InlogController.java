package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InlogController {

    @FXML
    private ImageView logo;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    public void initialize() {
        try {
            Image logoImage = new Image(getClass().getResourceAsStream("/Images/logo.png"));
            if (logoImage != null) {
                logo.setImage(logoImage);
            } else {
                System.err.println("Logo-afbeelding niet gevonden: /Images/logo.png");
            }
        } catch (Exception e) {
            System.err.println("Fout bij het laden van de logo-afbeelding: " + e.getMessage());
        }
    }

    @FXML
    public void handleLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Reset foutmeldingen en veldstijlen
        usernameField.setStyle("-fx-border-color: #ddd;");
        passwordField.setStyle("-fx-border-color: #ddd;");
        errorMessageLabel.setText("");

        // Validatie
        if (username.isEmpty()) {
            usernameField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een gebruikersnaam in.");
        } else if (password.isEmpty()) {
            passwordField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een wachtwoord in.");
        } else if (username.equals("Skibidi") && password.equals("Toilets")) {
            System.out.println("Inloggen succesvol!");
            navigateToHome();
        } else {
            System.err.println("Ongeldige inloggegevens.");
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Ongeldige inloggegevens.");
        }
    }

    private void navigateToHome() {
        try {
            // Laad HomeScherm.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/HomeScherm.fxml"));
            Parent root = loader.load();

            // Haal de huidige stage op
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Stel de nieuwe scène in
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Home");
        } catch (Exception e) {
            System.err.println("Fout bij navigatie naar HomeScherm: " + e.getMessage());
        }
    }
}
