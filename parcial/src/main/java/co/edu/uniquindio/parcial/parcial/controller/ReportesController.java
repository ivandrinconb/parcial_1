package co.edu.uniquindio.parcial.parcial.controller;

import co.edu.uniquindio.parcial.parcial.model.Reporte;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class ReportesController {

    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;
    @FXML private Label lblIngresos;
    @FXML private Label lblCantidadReservas;
    @FXML private Label lblCantidadClientes;
    @FXML private Label lblCantidadVehiculos;

    // 🔹 Nuevos elementos
    @FXML private TextField txtTelefono;
    @FXML private Label lblResultado;

    @FXML
    private void generarReporte() {
        LocalDate inicio = dpInicio.getValue();
        LocalDate fin = dpFin.getValue();

        if (inicio == null || fin == null) {
            lblIngresos.setText("Selecciona ambas fechas.");
            lblIngresos.setStyle("-fx-text-fill: red;");
            return;
        }

        Reporte reporte = SistemaController.getInstance().generarReporte(inicio, fin);

        lblIngresos.setText("Ingresos: $" + reporte.getIngresosTotales());
        lblCantidadReservas.setText("Reservas: " + reporte.getCantidadReservas());
        lblCantidadClientes.setText("Clientes únicos: " + reporte.getCantidadClientes());
        lblCantidadVehiculos.setText("Vehículos usados: " + reporte.getCantidadVehiculos());
    }

    @FXML
    private void verificarTelefono() {
        String telefono = txtTelefono.getText();
        if (telefono == null || telefono.isEmpty()) {
            lblResultado.setText("Ingresa un teléfono.");
            lblResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean esPerfecto = SistemaController.getInstance().telefonoEsNumeroPerfecto(telefono);
        if (esPerfecto) {
            lblResultado.setText("El teléfono corresponde a un número perfecto.");
            lblResultado.setStyle("-fx-text-fill: green;");
        } else {
            lblResultado.setText("El teléfono NO corresponde a un número perfecto.");
            lblResultado.setStyle("-fx-text-fill: blue;");
        }
    }
}