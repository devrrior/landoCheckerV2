module com.example.landocheckerv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.landocheckerv2 to javafx.fxml;
    exports com.example.landocheckerv2;

    opens com.example.landocheckerv2.controllers to javafx.fxml;
    exports com.example.landocheckerv2.controllers;
}