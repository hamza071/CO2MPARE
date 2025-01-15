package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class CompareController {

    @FXML
    private ImageView homeIcon; // Zorg dat dit overeenkomt met de FXML

    @FXML
    private ImageView settingsIcon; // Zorg dat dit overeenkomt met de FXML

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

            // Haal de huidige stage op via een bestaand FXML-element
            Stage stage = (Stage) homeIcon.getScene().getWindow();

            // Stel de nieuwe scène in
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van FXML-bestand: " + fxmlPath);
        }
    }
}
