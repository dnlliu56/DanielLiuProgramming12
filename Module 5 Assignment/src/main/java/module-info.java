module com.liu.module53javafxcss {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.csv;


    opens com.liu.gui to javafx.fxml;
    exports com.liu.gui;
    exports com.liu.database;
    opens com.liu.database to javafx.fxml;
    exports com.liu;
    opens com.liu to javafx.fxml;
}