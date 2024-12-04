package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InlogController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Voeg hier je logica voor de inlogverificatie toe
        System.out.println("Ingelogd met gebruikersnaam: " + username + " en wachtwoord: " + password);
    }
}
