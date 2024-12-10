package com.example.co2mpare;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InlogController {

    @FXML
    private ImageView logo;  // Zorg ervoor dat dit overeenkomt met de fx:id in de FXML

    @FXML
    public void initialize() {
        // Laad de afbeelding via de controller
        logo.setImage(new Image(getClass().getResourceAsStream("/com/example/co2mpare/logo.png")));
    }
}
