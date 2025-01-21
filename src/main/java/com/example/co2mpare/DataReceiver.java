package com.example.co2mpare;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataReceiver {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

    // Dynamisch account ID, standaardwaarde -1 (geen account ingelogd)
    private static int loggedInAccountId = 1;

    // Variabelen voor het bijhouden van hoogste waarden binnen 5 seconden
    private static double maxElectricityUsage = 0;
    private static double maxGasUsage = 0;

    public static void setLoggedInAccountId(int accountId) {
        loggedInAccountId = accountId;
    }

    public static int getLoggedInAccountId() {
        return loggedInAccountId;
    }

    public static void main(String[] args) {
        if (loggedInAccountId == -1) {
            System.err.println("Geen gebruiker is ingelogd. Kan geen data koppelen.");
            return;
        }

        System.out.println("Ingelogd account ID: " + loggedInAccountId);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server luistert op poort 5000...");

            // Tijd bijhouden om elke 5 seconden de data op te slaan
            long lastSaveTime = System.currentTimeMillis();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Verbonden met: " + clientSocket.getInetAddress());

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Lees data van de client
                    String rawData = in.readLine();
                    System.out.println("Ontvangen data: " + rawData);

                    // Verwerk de ontvangen data
                    updateMaxValues(rawData);

                    // Controleer of het tijd is om de hoogste waarden op te slaan
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastSaveTime >= 5000) {
                        saveToDatabase();
                        lastSaveTime = currentTime;
                    }

                    out.println("Data succesvol ontvangen en verwerkt.");

                } catch (Exception e) {
                    System.err.println("Fout bij het verwerken van clientgegevens: " + e.getMessage());
                } finally {
                    clientSocket.close();
                }
            }
        } catch (Exception e) {
            System.err.println("Fout bij het opzetten van de server:");
            e.printStackTrace();
        }
    }

    private static void updateMaxValues(String rawData) {
        try {
            String[] values = rawData.split("\\|");
            if (values.length != 2) {
                System.err.println("Ongeldig dataformaat: " + rawData);
                return;
            }

            double currentElectricityUsage = Double.parseDouble(values[0]); // kWh
            double currentGasUsage = Double.parseDouble(values[1]); // m³

            // Update de maximale waarden
            maxElectricityUsage = Math.max(maxElectricityUsage, currentElectricityUsage);
            maxGasUsage = Math.max(maxGasUsage, currentGasUsage);

            System.out.printf("Huidige hoogste elektriciteit: %.2f kWh, gas: %.2f m³%n", maxElectricityUsage, maxGasUsage);
        } catch (NumberFormatException e) {
            System.err.println("Fout bij het parsen van numerieke waarden: " + rawData);
        }
    }

    private static void saveToDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Opslaan van de hoogste waarden in de database...");

            // Zet gebruik om naar CO2
            double electricityCO2 = maxElectricityUsage * 0.475; // kg CO₂ per kWh
            double gasCO2 = maxGasUsage * 1.884; // kg CO₂ per m³

            String query = "INSERT INTO usage_data (account_id, date, gas_usage_kg, electricity_usage_kg) VALUES (?, NOW(), ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInAccountId);
            statement.setDouble(2, gasCO2); // Gasgebruik in CO2
            statement.setDouble(3, electricityCO2); // Elektriciteitgebruik in CO2

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data succesvol opgeslagen in de database!");
            }

            // Reset de maximale waarden na opslag
            maxElectricityUsage = 0;
            maxGasUsage = 0;
        } catch (Exception e) {
            System.err.println("Fout bij het opslaan van data:");
            e.printStackTrace();
        }
    }
}
