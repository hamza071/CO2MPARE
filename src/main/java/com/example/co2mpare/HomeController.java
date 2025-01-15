package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    public void goToHome() {
        System.out.println("Navigeren naar het Home-scherm.");
        loadFXML("/com/example/co2mpare/HomeScherm.fxml");
    }

    @FXML
    public void goToStats() {
        System.out.println("Navigeren naar het Statistieken-scherm.");
        loadFXML("/com/example/co2mpare/CompareScherm.fxml");
    }

    @FXML
    public void goToSettings() {
        System.out.println("Navigeren naar het Instellingen-scherm.");
        loadFXML("/com/example/co2mpare/SettingsScherm.fxml");
    }

    private void loadFXML(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Huidige stage ophalen via een willekeurig component
            Stage stage = (Stage) ((Node) loader.getRoot()).getScene().getWindow();

            // Scene wijzigen
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van FXML-bestand: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
