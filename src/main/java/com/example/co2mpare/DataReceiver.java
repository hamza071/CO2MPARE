package com.example.co2mpare;

import com.jcraft.jsch.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataReceiver {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/verbruikapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hamza";

    private static final String PI_HOST = "192.168.1.151";
    private static final String PI_USER = "hamza";
    private static final String PI_SCRIPT = "source /home/hamza/myenv/bin/activate && python3 /home/hamza/slimme_meter.py";

    public static void main(String[] args) {
        try {
            // Maak SSH-verbinding met de Raspberry Pi
            JSch jsch = new JSch();
            Session session = jsch.getSession(PI_USER, PI_HOST, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("hamza"); // Wachtwoord van Raspberry Pi gebruiker
            session.connect();

            System.out.println("Verbonden met Raspberry Pi!");

            // Voer het script uit op de Raspberry Pi
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(PI_SCRIPT);
            channel.setInputStream(null);
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();

            System.out.println("Script gestart op de Raspberry Pi...");

            // Lees data van de Raspberry Pi
            StringBuilder data = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                String output = new String(buffer, 0, bytesRead);
                System.out.print(output); // Print ontvangen data in de console
                data.append(output);
            }

            // Sluit SSH-verbinding
            channel.disconnect();
            session.disconnect();

            // Sla de ontvangen data op in de database
            saveToDatabase(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToDatabase(String data) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Verbonden met de database!");

            // Query om data op te slaan
            String query = "INSERT INTO verbruik (account_id, datum, data) VALUES (?, NOW(), ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            // Gebruik een dummy account ID voor nu
            statement.setInt(1, 1); // account_id = 1
            statement.setString(2, data);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data succesvol opgeslagen in de database!");
            }
        } catch (Exception e) {
            System.err.println("Fout bij opslaan in de database:");
            e.printStackTrace();
        }
    }
}
