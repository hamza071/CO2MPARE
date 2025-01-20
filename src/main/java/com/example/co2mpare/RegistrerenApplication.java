package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrerenApplication extends Application {

    private static final double WINDOW_WIDTH = 375; // Breedte in pixels
    private static final double WINDOW_HEIGHT = 667; // Hoogte in pixels

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/co2mpare/RegistrerenScherm.fxml"));
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle("Registreren - Co2mpare");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Zorg dat het venster niet kan worden aangepast
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
