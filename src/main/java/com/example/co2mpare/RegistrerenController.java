package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class RegistrerenController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    public void handleRegisterButtonClick() {
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        errorMessageLabel.setText(""); // Reset foutmelding

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessageLabel.setText("Vul alle velden in.");
        } else if (!password.equals(confirmPassword)) {
            errorMessageLabel.setText("Wachtwoorden komen niet overeen.");
        } else if (!isValidPassword(password)) {
            errorMessageLabel.setText("Wachtwoord moet minimaal 8 tekens, een hoofdletter, een cijfer en een speciaal teken bevatten.");
        } else {
            // Registratieproces uitvoeren
            System.out.println("Registreren succesvol voor email: " + email);
            errorMessageLabel.setText("Registratie gelukt!"); // Optioneel
        }
    }

    private boolean isValidPassword(String password) {
        // Regex voor wachtwoordvalidatie
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }
}
