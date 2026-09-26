package co.edu.uniquindio.parcial.parcial.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Button btnModalidad;

    public void OnActionModalidad(javafx.event.ActionEvent actionEvent) {
        cargarVista("/co/edu/uniquindio/parcial/parcial/view/modalidad-view.fxml");
    }


    @FXML
    public void mostrarClientes() {
        cargarVista("/co/edu/uniquindio/parcial/parcial/view/cliente-view.fxml");
    }

    @FXML
    public void mostrarVehiculos() {
        cargarVista("/co/edu/uniquindio/parcial/parcial/view/vehiculo-view.fxml");
    }

    private void cargarVista(String ruta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent vista = loader.load();
            contentArea.getChildren().setAll(vista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}