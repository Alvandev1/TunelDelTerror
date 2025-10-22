module com.tunel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.tunel to javafx.fxml;
    exports com.tunel;
}
