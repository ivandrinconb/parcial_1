package co.edu.uniquindio.parcial.parcial.controller;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.builder.Reserva;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.Modalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<Vehiculo> cbVehiculo;
    @FXML private ComboBox<Modalidad> cbModalidad;
    @FXML private DatePicker dpInicio;
    @FXML private DatePicker dpFin;

    @FXML private CheckBox chkGPS;
    @FXML private CheckBox chkSilla;
    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkConductor;

    @FXML private TextField txtCostoTotal;
    @FXML private Label lblMensaje;

    @FXML private TableView<Reserva> tablaReservas;
    @FXML private TableColumn<Reserva, String> colCliente;
    @FXML private TableColumn<Reserva, String> colVehiculo;
    @FXML private TableColumn<Reserva, String> colModalidad;
    @FXML private TableColumn<Reserva, String> colInicio;
    @FXML private TableColumn<Reserva, String> colFin;
    @FXML private TableColumn<Reserva, String> colServicios;
    @FXML private TableColumn<Reserva, String> colCosto;

    @FXML
    public void initialize() {
        colCliente.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCliente().getNombreCompleto()));
        colVehiculo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getVehiculo().getMarca()));
        colModalidad.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getModalidad().getNombre()));
        colInicio.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFechaInicio().toString()));
        colFin.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFechaFin().toString()));
        colCosto.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().calcularCostoTotal())));

        // Mostrar servicios adicionales en la tabla
        colServicios.setCellValueFactory(cellData -> {
            List<ServicioAdicional> servicios = cellData.getValue().getServicios();
            String nombres = servicios.stream()
                    .map(ServicioAdicional::getNombre)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            return new ReadOnlyStringWrapper(nombres);
        });

        tablaReservas.setItems(SistemaController.getInstance().getReservas());

        cbCliente.setItems(SistemaController.getInstance().getClientes());
        cbVehiculo.setItems(SistemaController.getInstance().getVehiculos());
        cbModalidad.setItems(SistemaController.getInstance().getModalidades());
    }

    @FXML
    private void registrarReserva() {
        try {
            Cliente cliente = cbCliente.getValue();
            Vehiculo vehiculo = cbVehiculo.getValue();
            Modalidad modalidad = cbModalidad.getValue();

            if (cliente == null || vehiculo == null || modalidad == null ||
                    dpInicio.getValue() == null || dpFin.getValue() == null) {
                lblMensaje.setText("Completa todos los campos obligatorios.");
                lblMensaje.setStyle("-fx-text-fill: red;");
                return;
            }

            long dias = ChronoUnit.DAYS.between(dpInicio.getValue(), dpFin.getValue());
            if (dias <= 0) {
                lblMensaje.setText("La fecha de fin debe ser posterior a la de inicio.");
                lblMensaje.setStyle("-fx-text-fill: red;");
                return;
            }

            double costoBase = dias * modalidad.getValorDiario();
            double costoExtra = 0;
            List<ServicioAdicional> servicios = new ArrayList<>();

            // Prototype: clonar servicios seleccionados
            if (chkGPS.isSelected()) {
                servicios.add(new ServicioAdicional("GPS", 20).clone());
                costoExtra += 20;
            }
            if (chkSilla.isSelected()) {
                servicios.add(new ServicioAdicional("Silla para niños", 15).clone());
                costoExtra += 15;
            }
            if (chkSeguro.isSelected()) {
                servicios.add(new ServicioAdicional("Seguro adicional", 50).clone());
                costoExtra += 50;
            }
            if (chkConductor.isSelected()) { // 🔹 nuevo servicio
                servicios.add(new ServicioAdicional("Conductor adicional", 30).clone());
                costoExtra += 30;
            }

            double costoTotal = costoBase + costoExtra;
            txtCostoTotal.setText(String.format("%.2f", costoTotal));

            Reserva reserva = new Reserva.Builder()
                    .cliente(cliente)
                    .vehiculo(vehiculo)
                    .modalidad(modalidad)
                    .servicios(servicios)
                    .fechaInicio(dpInicio.getValue())
                    .fechaFin(dpFin.getValue())
                    .dias((int) dias)
                    .build();

            SistemaController.getInstance().registrarReserva(reserva);

            lblMensaje.setText("Reserva registrada correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
            limpiarCampos();

        } catch (Exception e) {
            lblMensaje.setText("Error al registrar la reserva.");
            lblMensaje.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        cbCliente.setValue(null);
        cbVehiculo.setValue(null);
        cbModalidad.setValue(null);
        dpInicio.setValue(null);
        dpFin.setValue(null);
        chkGPS.setSelected(false);
        chkSilla.setSelected(false);
        chkSeguro.setSelected(false);
        chkConductor.setSelected(false);
        txtCostoTotal.clear();
    }
}