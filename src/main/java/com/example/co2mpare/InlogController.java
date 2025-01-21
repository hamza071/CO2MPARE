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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InlogController {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

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
        String email = usernameField.getText();
        String password = passwordField.getText();

        usernameField.setStyle("-fx-border-color: #ddd;");
        passwordField.setStyle("-fx-border-color: #ddd;");
        errorMessageLabel.setText("");

        if (email.isEmpty()) {
            usernameField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een e-mail in.");
        } else if (password.isEmpty()) {
            passwordField.setStyle("-fx-border-color: red;");
            errorMessageLabel.setText("Vul een wachtwoord in.");
        } else {
            int accountId = authenticateUser(email, password);
            if (accountId > 0) {
                System.out.println("Inloggen succesvol!");

                // Stel het ingelogde account ID in voor sessiebeheer
                DataReceiver.setLoggedInAccountId(accountId);

                // Start DataReceiver in een nieuwe achtergrondthread
                Thread dataReceiverThread = new Thread(() -> DataReceiver.main(null));
                dataReceiverThread.setDaemon(true); // Zorgt ervoor dat de thread stopt als de applicatie sluit
                dataReceiverThread.start();

                navigateToHome();
            } else {
                System.err.println("Ongeldige inloggegevens.");
                usernameField.setStyle("-fx-border-color: red;");
                passwordField.setStyle("-fx-border-color: red;");
                errorMessageLabel.setText("Ongeldige inloggegevens.");
            }
        }
    }

    private int authenticateUser(String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT id FROM accounts WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Retourneer de ID van de ingelogde gebruiker
            }
        } catch (Exception e) {
            System.err.println("Fout bij authenticatie: " + e.getMessage());
        }
        return -1; // Retourneer -1 als authenticatie mislukt
    }

    private void navigateToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/HomeScherm.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            stage.setTitle("Home");
        } catch (Exception e) {
            System.err.println("Fout bij navigatie naar HomeScherm: " + e.getMessage());
        }
    }

    @FXML
    public void handleRegisterButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/RegistrerenScherm.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            stage.setTitle("Registreren");
        } catch (Exception e) {
            System.err.println("Fout bij navigatie naar RegistrerenScherm: " + e.getMessage());
        }
    }
}
