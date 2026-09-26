package co.edu.uniquindio.parcial.parcial.controller;

import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehiculoController {

    @FXML private TextField txtPlaca;
    @FXML private TextField txtMarca;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAnio;
    @FXML private ComboBox<String> cbTipo;
    @FXML private TextField txtTarifa;
    @FXML private Label lblMensaje;

    @FXML private TableView<Vehiculo> tablaVehiculos;
    @FXML private TableColumn<Vehiculo, String> colPlaca;
    @FXML private TableColumn<Vehiculo, String> colMarca;
    @FXML private TableColumn<Vehiculo, String> colModelo;
    @FXML private TableColumn<Vehiculo, Integer> colAnio;
    @FXML private TableColumn<Vehiculo, String> colTipo;
    @FXML private TableColumn<Vehiculo, Double> colTarifa;

    @FXML
    public void initialize() {
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAnio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaDiaria"));


        tablaVehiculos.setItems(SistemaController.getInstance().getVehiculos());

        cbTipo.setItems(FXCollections.observableArrayList("Turismo", "SUV", "Camioneta", "Van"));
    }

    @FXML
    public void registrarVehiculo() {
        try {
            String placa = txtPlaca.getText();
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            String tipo = cbTipo.getValue();
            double tarifa = Double.parseDouble(txtTarifa.getText());

            if (placa.isEmpty() || marca.isEmpty() || tipo == null) {
                lblMensaje.setText("Completa todos los campos obligatorios.");
                lblMensaje.setStyle("-fx-text-fill: red;");
                return;
            }

            Vehiculo vehiculo = new Vehiculo(placa, marca, modelo, anio, tipo, tarifa);

            // Confirmación en consola
            System.out.println("Vehículo registrado: " + vehiculo.getPlaca() + " | " + vehiculo.getMarca());

            // Registrar en el sistema (la tabla se actualiza automáticamente)
            SistemaController.getInstance().registrarVehiculo(vehiculo);

            lblMensaje.setText("Vehículo registrado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
            limpiarCampos();

        } catch (NumberFormatException e) {
            lblMensaje.setText("Año y tarifa deben ser números válidos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    private void limpiarCampos() {
        txtPlaca.clear();
        txtMarca.clear();
        txtModelo.clear();
        txtAnio.clear();
        cbTipo.setValue(null);
        txtTarifa.clear();
    }
}