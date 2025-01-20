module com.example.co2mpare {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jsch;


    // Exporteer de com.example.co2mpare package zodat andere modules toegang hebben
    exports com.example.co2mpare;
    opens com.example.co2mpare to javafx.fxml, java.sql;
}
