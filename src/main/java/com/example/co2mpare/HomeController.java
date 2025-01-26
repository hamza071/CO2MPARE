package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeController {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Co2mpare!";

    // Labels om de data weer te geven
    @FXML
    private Label todayEnergyLabel;
    @FXML
    private Label todayGasLabel;

    @FXML
    private Label yesterdayEnergyLabel;
    @FXML
    private Label yesterdayGasLabel;

    @FXML
    private Label weeklyEnergyLabel;
    @FXML
    private Label weeklyGasLabel;

    @FXML
    private Label monthlyEnergyLabel;
    @FXML
    private Label monthlyGasLabel;

    @FXML
    public void initialize() {
        // Haal de data op uit de database en toon deze in de labels
        updateUsageData();
    }

    private void updateUsageData() {
        int accountId = DataReceiver.getLoggedInAccountId(); // Gebruik de methode om het account ID op te halen
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Vandaag
            String todayQuery = "SELECT SUM(electricity_usage_kg) AS energy, SUM(gas_usage_kg) AS gas " +
                    "FROM usage_data WHERE account_id = ? AND DATE(date) = CURDATE()";
            setUsageData(todayQuery, conn, accountId, todayEnergyLabel, todayGasLabel);

            // Gisteren
            String yesterdayQuery = "SELECT SUM(electricity_usage_kg) AS energy, SUM(gas_usage_kg) AS gas " +
                    "FROM usage_data WHERE account_id = ? AND DATE(date) = CURDATE() - INTERVAL 1 DAY";
            setUsageData(yesterdayQuery, conn, accountId, yesterdayEnergyLabel, yesterdayGasLabel);

            // Afgelopen week
            String weeklyQuery = "SELECT SUM(electricity_usage_kg) AS energy, SUM(gas_usage_kg) AS gas " +
                    "FROM usage_data WHERE account_id = ? AND DATE(date) >= CURDATE() - INTERVAL 7 DAY";
            setUsageData(weeklyQuery, conn, accountId, weeklyEnergyLabel, weeklyGasLabel);

            // Afgelopen maand
            String monthlyQuery = "SELECT SUM(electricity_usage_kg) AS energy, SUM(gas_usage_kg) AS gas " +
                    "FROM usage_data WHERE account_id = ? AND DATE(date) >= CURDATE() - INTERVAL 30 DAY";
            setUsageData(monthlyQuery, conn, accountId, monthlyEnergyLabel, monthlyGasLabel);

        } catch (Exception e) {
            System.err.println("Fout bij het ophalen van gebruiksdata: " + e.getMessage());
        }
    }

    private void setUsageData(String query, Connection conn, int accountId, Label energyLabel, Label gasLabel) {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double energy = rs.getDouble("energy");
                double gas = rs.getDouble("gas");

                // Toon de data in de labels, afgerond op 2 decimalen
                energyLabel.setText(String.format("%.2f kg", energy));
                gasLabel.setText(String.format("%.2f kg", gas));
            } else {
                // Als er geen data is
                energyLabel.setText("0.00 kg");
                gasLabel.setText("0.00 kg");
            }
        } catch (Exception e) {
            System.err.println("Fout bij het instellen van gebruiksdata: " + e.getMessage());
        }
    }

    @FXML
    public void goToHome(MouseEvent event) {
        loadFXML("/com/example/co2mpare/HomeScherm.fxml", "Home", event);
    }

    @FXML
    public void goToSettings(MouseEvent event) {
        loadFXML("/com/example/co2mpare/SettingsScherm.fxml", "Settings", event);
    }

    @FXML
    public void goToStats(MouseEvent event) {
        loadFXML("/com/example/co2mpare/CompareScherm.fxml", "Compare", event);
    }

    private void loadFXML(String fxmlPath, String title, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van het scherm: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
