module planner {
    requires javafx.controls;
    requires javafx.fxml;

    opens planner to javafx.fxml;
    exports planner;
}