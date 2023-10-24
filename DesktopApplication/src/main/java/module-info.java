module example.desktopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens example.desktopapplication to javafx.fxml;
    exports example.desktopapplication;
}