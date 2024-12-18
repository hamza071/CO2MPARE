package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SettingsApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(SettingsApplication.class.getResource("SettingsScherm.fxml"));
        StackPane root = fxmlLoader.load();

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
