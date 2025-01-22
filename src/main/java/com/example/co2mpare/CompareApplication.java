package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class +CompareApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Laad het FXML-bestand
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/co2mpare/CompareScherm.fxml"));
        Parent root = loader.load();

        // Stel de scène in
        Scene scene = new Scene(root);

        // Configuratie van het venster
        primaryStage.setTitle("CO₂MPARE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
