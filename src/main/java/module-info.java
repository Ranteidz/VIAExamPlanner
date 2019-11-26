module planner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens planner to javafx.fxml;
    exports planner;
}