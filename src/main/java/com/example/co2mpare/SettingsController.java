package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    @FXML
    public void goToHome(MouseEvent event) {
        loadFXML("/com/example/co2mpare/HomeScherm.fxml", "Home", event);
    }

    @FXML
    public void goToCompare(MouseEvent event) {
        loadFXML("/com/example/co2mpare/CompareScherm.fxml", "Compare", event);
    }

    @FXML
    public void signOut(MouseEvent event) {
        loadFXML("/com/example/co2mpare/InlogScherm.fxml", "Inloggen", event);
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
