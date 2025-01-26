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

public class DataReceiver {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/co2mpare";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Co2mpare!";

    private static final String PI_HOST = "192.168.1.151";
    private static final String PI_USER = "hamza";
    private static final String PI_SCRIPT = "source /home/hamza/myenv/bin/activate && python3 /home/hamza/slimme_meter.py";

    private static int loggedInAccountId = -1;

    public static void setLoggedInAccountId(int accountId) {
        loggedInAccountId = accountId;
    }

    public static int getLoggedInAccountId() {
        return loggedInAccountId;
    }

    public static void main(String[] args) {
        if (loggedInAccountId == -1) {
            return;
        }

        new Thread(DataReceiver::startSSHConnection).start();
        new Thread(DataReceiver::startServerSocket).start();
    }

    private static void startSSHConnection() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(PI_USER, PI_HOST, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("hamza");
            session.connect();

            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(PI_SCRIPT);
            channel.setInputStream(null);
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (reader.readLine() != null) {
                // Output verwerken indien nodig
            }

            channel.disconnect();
            session.disconnect();
        } catch (Exception e) {
            // Fout afhandelen
        }
    }

    private static void startServerSocket() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    StringBuilder rawData = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        rawData.append(line).append("\n");
                    }

                    processAndSaveData(rawData.toString());
                    out.println("Data succesvol ontvangen en verwerkt.");
                } catch (Exception e) {
                    // Fout bij verwerken van gegevens
                }
            }
        } catch (Exception e) {
            // Fout bij opzetten van de server
        }
    }

    private static void processAndSaveData(String rawData) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String[] values = rawData.trim().split("\\|");
            if (values.length != 2) {
                return;
            }

            double electricityDelta = Double.parseDouble(values[0]);
            double gasDelta = Double.parseDouble(values[1]);

            double electricityCO2 = electricityDelta * 0.475;
            double gasCO2 = gasDelta * 1.884;

            String query = "INSERT INTO usage_data (account_id, date, gas_usage_kg, electricity_usage_kg) VALUES (?, NOW(), ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, loggedInAccountId);
            statement.setDouble(2, gasCO2);
            statement.setDouble(3, electricityCO2);

            statement.executeUpdate();
        } catch (Exception e) {
            // Fout bij opslaan van data
        }
    }
}
