package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {


    @FXML
    private void goToHome(MouseEvent event) {
        switchScene("/com/example/co2mpare/HomeScherm.fxml", event);
    }

    @FXML
    private void goToCompare(MouseEvent event) {
        switchScene("/com/example/co2mpare/CompareScherm.fxml", event);
    }

    @FXML
    private void goToSettings(MouseEvent event) {
        switchScene("/com/example/co2mpare/SettingsScherm.fxml", event);
    }

    @FXML
    private void goToFAQ(MouseEvent event) {
        switchScene("/com/example/co2mpare/FAQScherm.fxml", event);
    }

    @FXML
    private void goToOrders(MouseEvent event) {
        switchScene("/com/example/co2mpare/BestellingenScherm.fxml", event);
    }

    @FXML
    private void goToOverOns(MouseEvent event) {
        switchScene("/com/example/co2mpare/OverOnsScherm.fxml", event);
    }

    @FXML
    private void goToHelp(MouseEvent event) {
        switchScene("/com/example/co2mpare/HelpScherm.fxml", event);
    }

    @FXML
    private void signOut(MouseEvent event) {
        System.out.println("Gebruiker is uitgelogd.");
        switchScene("/com/example/co2mpare/InlogScherm.fxml", event);
    }

    private void switchScene(String fxmlPath, MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 375, 667));
            stage.show();
        } catch (IOException e) {
            System.err.println("Fout bij het laden van het scherm: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
