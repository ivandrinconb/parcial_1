package co.edu.uniquindio.parcial.parcial.controller;

import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.*;
import co.edu.uniquindio.parcial.parcial.patrones.singleton.SistemaController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ModalidadController {

    @FXML private TextField txtCodigo;
    @FXML private ComboBox<String> cbNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDuracionMinima;
    @FXML private TextField txtValorDiario;
    @FXML private ComboBox<EstadoModalidad> cbEstado;
    @FXML private TextField txtCobertura;
    @FXML private TextField txtConductores;
    @FXML private TextField txtCaracteristicas;
    @FXML private Label lblMensaje;

    @FXML private TableView<Modalidad> tablaModalidades;
    @FXML private TableColumn<Modalidad, String> colCodigo;
    @FXML private TableColumn<Modalidad, String> colNombre;
    @FXML private TableColumn<Modalidad, String> colDescripcion;
    @FXML private TableColumn<Modalidad, String> colDuracion;
    @FXML private TableColumn<Modalidad, String> colValor;
    @FXML private TableColumn<Modalidad, String> colEstado;

    @FXML
    public void initialize() {
        // Usamos cellValueFactory manuales para asegurar que siempre se muestre texto
        colCodigo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCodigo()));
        colNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDescripcion()));
        colDuracion.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getDuracionMinimaDias())));
        colValor.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getValorDiario())));
        colEstado.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEstado().toString()));

        tablaModalidades.setItems(SistemaController.getInstance().getModalidades());

        cbNombre.setItems(FXCollections.observableArrayList("Economica", "Ejecutiva", "Premium"));
        cbEstado.setItems(FXCollections.observableArrayList(EstadoModalidad.values()));

        cbNombre.setOnAction(event -> cargarValoresPorDefecto(cbNombre.getValue()));
    }

    private void cargarValoresPorDefecto(String nombre) {
        switch (nombre) {
            case "Economica":
                txtCodigo.setText("ECO");
                txtDescripcion.setText("Modalidad económica con kilometraje incluido");
                txtDuracionMinima.setText("1");
                txtValorDiario.setText("50.0");
                cbEstado.setValue(EstadoModalidad.DISPONIBLE);
                txtCobertura.clear();
                txtConductores.clear();
                txtCaracteristicas.clear();
                break;
            case "Ejecutiva":
                txtCodigo.setText("EJE");
                txtDescripcion.setText("Modalidad ejecutiva con más beneficios");
                txtDuracionMinima.setText("2");
                txtValorDiario.setText("100.0");
                cbEstado.setValue(EstadoModalidad.DISPONIBLE);
                txtCobertura.clear();
                txtConductores.clear();
                txtCaracteristicas.clear();
                break;
            case "Premium":
                txtCodigo.setText("PRE");
                txtDescripcion.setText("Modalidad premium con cobertura total");
                txtDuracionMinima.setText("3");
                txtValorDiario.setText("200.0");
                cbEstado.setValue(EstadoModalidad.DISPONIBLE);
                txtCobertura.setText("Cobertura completa");
                txtConductores.setText("2");
                txtCaracteristicas.setText("Asistencia 24/7, GPS incluido");
                break;
        }
    }

    @FXML
    private void registrarModalidad() {
        try {
            String codigo = txtCodigo.getText();
            String nombre = cbNombre.getValue();
            String descripcion = txtDescripcion.getText();
            int duracionMinima = Integer.parseInt(txtDuracionMinima.getText());
            double valorDiario = Double.parseDouble(txtValorDiario.getText());
            EstadoModalidad estado = cbEstado.getValue();

            if (codigo.isEmpty() || nombre == null || descripcion.isEmpty() || estado == null) {
                lblMensaje.setText("Completa todos los campos obligatorios.");
                lblMensaje.setStyle("-fx-text-fill: red;");
                return;
            }

            ModalidadFactory factory;
            switch (nombre) {
                case "Economica": factory = new FactoryEconomica(); break;
                case "Ejecutiva": factory = new FactoryEjecutiva(); break;
                case "Premium": factory = new FactoryPremium(); break;
                default:
                    lblMensaje.setText("Tipo de modalidad no válido.");
                    return;
            }

            Modalidad modalidad = (Modalidad) factory.crearModalidad();
            modalidad.setCodigo(codigo);
            modalidad.setDescripcion(descripcion);
            modalidad.setDuracionMinimaDias(duracionMinima);
            modalidad.setValorDiario(valorDiario);
            modalidad.setEstado(estado);


            System.out.println("Modalidad registrada: "
                    + modalidad.getCodigo() + " | "
                    + modalidad.getNombre() + " | "
                    + modalidad.getDescripcion() + " | "
                    + modalidad.getDuracionMinimaDias() + " días | $"
                    + modalidad.getValorDiario() + " | Estado: "
                    + modalidad.getEstado());

            SistemaController.getInstance().registrarModalidad(modalidad);

            lblMensaje.setText("Modalidad registrada correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green;");
            limpiarCampos();

        } catch (NumberFormatException e) {
            lblMensaje.setText("Duración y valor deben ser números válidos.");
            lblMensaje.setStyle("-fx-text-fill: red;");
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        cbNombre.setValue(null);
        txtDescripcion.clear();
        txtDuracionMinima.clear();
        txtValorDiario.clear();
        cbEstado.setValue(null);
        txtCobertura.clear();
        txtConductores.clear();
        txtCaracteristicas.clear();
    }
}