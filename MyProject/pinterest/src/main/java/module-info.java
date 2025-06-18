module app.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    opens app.main to javafx.fxml;
    exports app.main;
}
