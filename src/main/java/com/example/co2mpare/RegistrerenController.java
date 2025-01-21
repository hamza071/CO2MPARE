package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
    private Label successMessageLabel;

    // Databasegegevens
    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

    @FXML
    private void handleRegisterButtonClick() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // Reset fout- en succesmeldingen
        errorMessageLabel.setText("");
        successMessageLabel.setText("");

        // Validatie
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessageLabel.setText("Vul alle velden in.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorMessageLabel.setText("Wachtwoorden komen niet overeen.");
            return;
        }

        if (!isPasswordValid(password)) {
            errorMessageLabel.setText("Wachtwoord moet minimaal 8 tekens, een hoofdletter, een cijfer en een speciaal teken bevatten.");
            return;
        }

        // Gegevens opslaan in de database
        if (saveUserToDatabase(email, password)) {
            successMessageLabel.setText("Registratie succesvol! U kunt nu inloggen.");
            clearFields();
        } else {
            errorMessageLabel.setText("Registratie mislukt. Probeer het opnieuw.");
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    private void clearFields() {
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private boolean saveUserToDatabase(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO accounts (email, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password); // Gebruik een hashfunctie voor productieomgevingen
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
