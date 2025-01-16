package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CompareController {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView settingsIcon;

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
}
