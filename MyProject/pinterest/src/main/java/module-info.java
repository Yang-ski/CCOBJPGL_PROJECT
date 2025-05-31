module app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens app.main to javafx.fxml;
    exports app.main;
}
