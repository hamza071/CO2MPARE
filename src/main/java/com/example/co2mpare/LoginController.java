package com.example.co2mpare;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Voeg hier je logica toe om in te loggen
        System.out.println("Inloggen met: " + email + " en wachtwoord: " + password);
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        // Voeg hier je logica toe om de registratie te verwerken
        System.out.println("Registreren...");
    }
}
