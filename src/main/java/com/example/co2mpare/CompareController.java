package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompareController {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aidee123!";

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView settingsIcon;

    @FXML
    private TableView<LeaderboardEntry> leaderboardTable;

    @FXML
    private TableColumn<LeaderboardEntry, String> emailColumn;

    @FXML
    private TableColumn<LeaderboardEntry, Double> totalCO2Column;

    @FXML
    public void initialize() {
        // Koppel kolommen aan LeaderboardEntry-class
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        totalCO2Column.setCellValueFactory(new PropertyValueFactory<>("totalCO2"));

        // Pas de breedte van de kolommen aan
        emailColumn.prefWidthProperty().bind(leaderboardTable.widthProperty().multiply(0.6));
        totalCO2Column.prefWidthProperty().bind(leaderboardTable.widthProperty().multiply(0.4));

        // Stel de tabel in om proportioneel te schalen
        leaderboardTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Laad data in de TableView
        leaderboardTable.getItems().setAll(loadLeaderboardData());
    }


    private List<LeaderboardEntry> loadLeaderboardData() {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        String query = """
            SELECT email, ROUND(SUM(gas_usage_kg + electricity_usage_kg), 2) AS total_co2
            FROM accounts
            JOIN usage_data ON accounts.id = usage_data.account_id
            WHERE DATE(date) >= CURDATE() - INTERVAL 7 DAY
            GROUP BY accounts.id, accounts.email
            ORDER BY total_co2 ASC;
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String email = rs.getString("email");
                double totalCO2 = rs.getDouble("total_co2");
                leaderboard.add(new LeaderboardEntry(email, totalCO2));
            }
        } catch (Exception e) {
            System.err.println("Fout bij het laden van het leaderboard: " + e.getMessage());
        }

        return leaderboard;
    }




    @FXML
    public void goToHome() {
        navigateTo("/com/example/co2mpare/HomeScherm.fxml", "Home");
    }

    @FXML
    public void goToSettings() {
        navigateTo("/com/example/co2mpare/SettingsScherm.fxml", "Settings");
    }

    private void navigateTo(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) homeIcon.getScene().getWindow();
            stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van FXML-bestand: " + fxmlPath);
            e.printStackTrace();
        }
    }

    // Klasse om leaderboardgegevens te modelleren
    public static class LeaderboardEntry {
        private final String email;
        private final double totalCO2;

        public LeaderboardEntry(String email, double totalCO2) {
            this.email = email;
            this.totalCO2 = totalCO2;
        }

        public String getEmail() {
            return email;
        }

        public double getTotalCO2() {
            return totalCO2;
        }
    }
}
