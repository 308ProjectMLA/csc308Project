module com.example.csc308project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires json.simple;
    requires java.desktop;
    requires java.logging;

    opens com.example.csc308project to javafx.fxml;
    exports com.example.csc308project;
}