package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class InlogController {

    @FXML
    private ImageView logo;  // Zorg ervoor dat dit overeenkomt met de fx:id in de FXML

    @FXML
    private TextField usernameField;  // Gebruikersnaam veld, moet overeenkomen met de fx:id in de FXML

    @FXML
    private PasswordField passwordField;  // Wachtwoord veld, moet overeenkomen met de fx:id in de FXML

    @FXML
    private Label errorMessageLabel;  // Foutmelding Label

    @FXML
    public void initialize() {
        // Laad de afbeelding via de controller
        logo.setImage(new Image(getClass().getResourceAsStream("/com/example/co2mpare/logo.png")));
    }

    // Event handler voor de inlogknop
    @FXML
    public void handleLoginButtonClick() {
        String username = usernameField.getText();  // Verkrijg de gebruikersnaam
        String password = passwordField.getText();  // Verkrijg het wachtwoord

        // Reset de stijl en foutmelding
        usernameField.setStyle("-fx-border-color: #ddd;");
        passwordField.setStyle("-fx-border-color: #ddd;");
        errorMessageLabel.setText("");

        // Controleer of de velden leeg zijn
        if (username.isEmpty()) {
            usernameField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een gebruikersnaam in.");
        } else if (password.isEmpty()) {
            passwordField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een wachtwoord in.");
        } else if (username.equals("Skibidi") && password.equals("Toilets")) {
            System.out.println("Inloggen succesvol!");
            // Hier kun je bijvoorbeeld doorsteken naar een nieuw scherm of andere actie uitvoeren
        } else {
            System.out.println("Ongeldige inloggegevens.");
            // Visuele feedback voor foutieve inloggegevens
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Ongeldige inloggegevens.");
        }
    }
}
