module com.liu.module53javafxcss {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.csv;

    // Open for FXML reflection
    opens com.liu.gui to javafx.fxml;
    opens com.liu.database to javafx.fxml;

    // Open com.liu to javafx.graphics for JavaFX access (required for the Application class)
    opens com.liu to javafx.graphics; // Open the com.liu package to javafx.graphics
}
