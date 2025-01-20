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

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

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
            // Probeer de gebruiker op te slaan in de database
            if (saveToDatabase(email, password)) {
                errorMessageLabel.setText("Registratie gelukt!");
            } else {
                errorMessageLabel.setText("Registratie mislukt. Probeer het opnieuw.");
            }
        }
    }

    private boolean isValidPassword(String password) {
        // Regex voor wachtwoordvalidatie
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean saveToDatabase(String email, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO accounts (email, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password); // Let op: sla wachtwoorden versleuteld op in een echte applicatie!

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
