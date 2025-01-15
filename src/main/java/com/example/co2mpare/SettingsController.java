package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    public void goToHome() {
        navigateTo("/com/example/co2mpare/HomeScherm.fxml", "Home");
    }

    @FXML
    public void goToCompare() {
        navigateTo("/com/example/co2mpare/CompareScherm.fxml", "Compare");
    }

    private void navigateTo(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Huidige stage ophalen
            Stage stage = (Stage) root.getScene().getWindow();

            // Nieuwe scène instellen
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van " + fxmlPath);
            e.printStackTrace();
        }
    }
}
