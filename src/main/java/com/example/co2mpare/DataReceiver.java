package com.example.co2mpare;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class DataReceiver {

    // Database-instellingen
    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

    // Raspberry Pi-verbinding instellingen
    private static final String PI_HOST = "192.168.1.151";
    private static final String PI_USER = "hamza";
    private static final String PI_SCRIPT = "source /home/hamza/myenv/bin/activate && python3 /home/hamza/slimme_meter.py";

    // Variabele om ingelogde account ID op te slaan
    private static int loggedInAccountId = 1;

    // Timestamps voor bijhouden van updates
    private static LocalDateTime lastGasUpdate = LocalDateTime.MIN;
    private static LocalDateTime lastElectricityUpdate = LocalDateTime.MIN;

    // Methode om het ingelogde account ID in te stellen
    public static void setLoggedInAccountId(int accountId) {
        loggedInAccountId = accountId;
    }

    // Methode om het ingelogde account ID op te halen
    public static int getLoggedInAccountId() {
        return loggedInAccountId;
    }

    // Hoofdprogramma
    public static void main(String[] args) {
        // Controleer of een gebruiker is ingelogd
        if (loggedInAccountId == -1) {
            return; // Stop als er geen gebruiker is ingelogd
        }

        // Start zowel SSH-verbinding als serversocket in aparte threads
        new Thread(DataReceiver::startSSHConnection).start();
        new Thread(DataReceiver::startServerSocket).start();
    }

    // SSH-verbinding met Raspberry Pi maken en script uitvoeren
    private static void startSSHConnection() {
        try {
            // Maak een SSH-sessie aan
            JSch jsch = new JSch();
            Session session = jsch.getSession(PI_USER, PI_HOST, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("hamza");
            session.connect();

            // Voer het script op de Raspberry Pi uit
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(PI_SCRIPT);
            channel.setInputStream(null);
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            // Lezen van eventuele uitvoer van het script
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (reader.readLine() != null) {
                // Verwerken van de uitvoer (indien nodig)
            }

            // Sluit de SSH-sessie
            channel.disconnect();
            session.disconnect();
        } catch (Exception ignored) {
            // Fouten worden genegeerd
        }
    }

    // Serversocket opzetten om data te ontvangen
    private static void startServerSocket() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                // Wacht op een inkomende verbinding
                Socket clientSocket = serverSocket.accept();

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    // Lees de ontvangen data van de client
                    StringBuilder rawData = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        rawData.append(line).append("\n");
                    }

                    // Verwerk en sla de ontvangen data op
                    processAndSaveData(rawData.toString());
                    out.println("Data succesvol ontvangen en verwerkt.");
                } catch (Exception ignored) {
                    // Fouten bij het verwerken van clientgegevens worden genegeerd
                }
            }
        } catch (Exception ignored) {
            // Fouten bij het opzetten van de server worden genegeerd
        }
    }

    // Verwerkt de ontvangen data en slaat deze op in de database
    private static void processAndSaveData(String rawData) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Parse de ontvangen data
            String[] values = rawData.trim().split("\\|");
            if (values.length != 2) {
                return; // Ongeldige data-indeling
            }

            // Haal elektriciteits- en gasgebruik op uit de data
            double electricityUsage = Double.parseDouble(values[0]);
            double gasUsage = Double.parseDouble(values[1]);

            // Bereken de CO2-uitstoot
            double electricityCO2 = electricityUsage * 0.475; // CO2 per kWh
            double gasCO2 = gasUsage * 1.884; // CO2 per m³

            // Controleer of voldoende tijd verstreken is sinds laatste update
            LocalDateTime now = LocalDateTime.now();
            boolean saveElectricity = now.isAfter(lastElectricityUpdate.plusMinutes(15));
            boolean saveGas = now.isAfter(lastGasUpdate.plusHours(1));

            if (!saveElectricity && !saveGas) {
                return; // Sla geen data op als het tijdsinterval niet is verstreken
            }

            // Sla de data op in de database
            String query = "INSERT INTO usage_data (account_id, date, gas_usage_kg, electricity_usage_kg) VALUES (?, NOW(), ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInAccountId);
            statement.setDouble(2, saveGas ? gasCO2 : 0);
            statement.setDouble(3, saveElectricity ? electricityCO2 : 0);

            // Voer de database-insert uit
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                // Update de tijdstempels als de data succesvol is opgeslagen
                if (saveElectricity) lastElectricityUpdate = now;
                if (saveGas) lastGasUpdate = now;
            }
        } catch (Exception ignored) {
            // Fouten bij het verwerken en opslaan van data worden genegeerd
        }
    }
}
