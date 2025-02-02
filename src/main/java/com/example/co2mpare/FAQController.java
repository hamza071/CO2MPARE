package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class FAQController {

    private static final double WINDOW_WIDTH = 375;
    private static final double WINDOW_HEIGHT = 667;

    @FXML
    public void goToSettings(MouseEvent event) {
        loadFXML("/com/example/co2mpare/SettingsScherm.fxml", "Settings", event);
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
