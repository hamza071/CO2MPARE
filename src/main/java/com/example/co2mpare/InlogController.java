package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class InlogController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text errorMessage;

    @FXML
    public void handleLoginButtonClick() {
        // Logica voor inloggen
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Gebruikersnaam of wachtwoord mag niet leeg zijn!");
        } else if (username.equals("admin") && password.equals("password")) {
            errorMessage.setText("Succesvol ingelogd!");
            errorMessage.setStyle("-fx-fill: green;");
        } else {
            errorMessage.setText("Onjuiste gebruikersnaam of wachtwoord.");
            errorMessage.setStyle("-fx-fill: red;");
        }
    }
}
