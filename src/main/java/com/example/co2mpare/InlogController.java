package com.example.co2mpare; // Dit moet overeenkomen met je FXML-declaratie

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class InlogController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Text errorMessage;

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "admin123";

    @FXML
    public void initialize() {
        loginButton.setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (CORRECT_USERNAME.equals(username) && CORRECT_PASSWORD.equals(password)) {
            errorMessage.setText("");
            System.out.println("Inloggen succesvol!");
        } else {
            errorMessage.setText("Ongeldige gebruikersnaam of wachtwoord.");
        }
    }
}
