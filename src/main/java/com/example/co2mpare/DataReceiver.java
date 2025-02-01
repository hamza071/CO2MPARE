package com.example.co2mpare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataReceiver {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Aidee123!";

    private static int loggedInAccountId = -1; // Geen gebruiker ingelogd bij start

    // ✅ Methode om het ingelogde account ID in te stellen
    public static void setLoggedInAccountId(int accountId) {
        loggedInAccountId = accountId;
        System.out.println("🔹 Ingelogd account ID: " + loggedInAccountId);
    }

    // ✅ Methode om het ingelogde account ID op te halen
    public static int getLoggedInAccountId() {
        return loggedInAccountId;
    }

    public static void main(String[] args) {
        if (loggedInAccountId == -1) {
            System.out.println("❌ Geen gebruiker ingelogd. Stoppen...");
            return;
        }

        while (true) {
            generateAndSaveFakeData();
            try {
                Thread.sleep(60000); // Wacht 1 minuut voor nieuwe data
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateAndSaveFakeData() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (loggedInAccountId == -1) {
                System.out.println("❌ Geen ingelogde gebruiker, data wordt niet opgeslagen.");
                return;
            }

            Random random = new Random();

            // Realistische fake data genereren
            double electricityDelta = 0.1 + (random.nextDouble() * 0.4); // 0.1 - 0.5 kWh
            double gasDelta = random.nextDouble() < 0.2 ? (0.01 + (random.nextDouble() * 0.05)) : 0; // Soms 0 m³

            double electricityCO2 = electricityDelta * 0.475;
            double gasCO2 = gasDelta * 1.884;

            // Huidige tijd verkrijgen
            LocalDateTime now = LocalDateTime.now();
            String formattedTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Data in database opslaan
            String query = "INSERT INTO usage_data (account_id, date, gas_usage_kg, electricity_usage_kg) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInAccountId);
            statement.setString(2, formattedTime);
            statement.setDouble(3, gasCO2);
            statement.setDouble(4, electricityCO2);

            statement.executeUpdate();
            System.out.printf("🔹 Fake data opgeslagen: %.3f kWh, %.3f m³ (%s)%n", electricityDelta, gasDelta, formattedTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
