module co.edu.uniquindio.parcial.parcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.parcial.parcial to javafx.fxml;
    opens co.edu.uniquindio.parcial.parcial.controller to javafx.fxml;
    exports co.edu.uniquindio.parcial.parcial;
}