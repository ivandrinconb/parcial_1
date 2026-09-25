module co.edu.uniquindio.parcial.parcial {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parcial.parcial to javafx.fxml;
    exports co.edu.uniquindio.parcial.parcial;
}