package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InlogApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Zorg ervoor dat het pad naar het FXML-bestand klopt
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/co2mpare/InlogScherm.fxml"));
        Scene scene = new Scene(root, 400, 300); // Stel de grootte van het venster in
        primaryStage.setTitle("Inlogpagina");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
