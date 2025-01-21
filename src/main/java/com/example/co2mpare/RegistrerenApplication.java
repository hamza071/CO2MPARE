package com.example.co2mpare;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistrerenApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrerenApplication.class.getResource("RegistrerenScherm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 667); // Schermgrootte
        stage.setTitle("Registreren");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
