module assignments.assignment4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;

    opens assignments.assignment4 to javafx.fxml;
    opens assignments.assignment4.page to javafx.fxml;
    opens assignments.assignment4.components.form to javafx.fxml;
    opens assignments.assignment4.components to javafx.fxml;
    exports assignments.assignment4;
    exports assignments.assignment4.page;
    exports assignments.assignment4.components.form;
    exports assignments.assignment4.components;
}
