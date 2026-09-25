package co.edu.uniquindio.parcial.parcial.controller;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ClienteController {

    @FXML private TextField txtDocumento;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;
    @FXML private Label lblMensaje;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colDocumento;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;

    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        tablaClientes.setItems(listaClientes);
    }

    @FXML
    public void registrarCliente() {
        try {
            String documento = txtDocumento.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            LocalDate fechaRegistro = dpFechaRegistro.getValue();

            if (documento.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || fechaRegistro == null) {
                lblMensaje.setText("Completa todos los campos obligatorios.");
                return;
            }

            Cliente cliente = new Cliente(nombre, documento, telefono, correo, edad, fechaRegistro);
            SistemaController.getInstance().registrarCliente(cliente);

            listaClientes.add(cliente);
            lblMensaje.setText("Cliente registrado correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            lblMensaje.setText("La edad debe ser un número.");
        }
    }

    private void limpiarCampos() {
        txtDocumento.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(null);
    }
}