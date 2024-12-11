package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;  // Zorg ervoor dat Button wordt geïmporteerd
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InlogController {

    @FXML
    private ImageView logo;  // Zorg ervoor dat dit overeenkomt met de fx:id in de FXML

    @FXML
    private TextField usernameField;  // Gebruikersnaam veld, moet overeenkomen met de fx:id in de FXML

    @FXML
    private PasswordField passwordField;  // Wachtwoord veld, moet overeenkomen met de fx:id in de FXML

    @FXML
    private Button loginButton;  // Voeg de Button toe met het fx:id "loginButton"

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

        // Logica om te controleren of de inloggegevens correct zijn
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Inloggen succesvol!");
            // Hier kun je bijvoorbeeld doorsteken naar een nieuw scherm of andere actie uitvoeren
        } else {
            System.out.println("Ongeldige inloggegevens.");
        }
    }
}
