package co.edu.uniquindio.parcial.parcial.patrones.singleton;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Empresa;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.model.Reporte;
import co.edu.uniquindio.parcial.parcial.patrones.builder.Reserva;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.Modalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaController {
    private static SistemaController instance;
    private Empresa empresa;

    private final ObservableList<Cliente> clientes;
    private final ObservableList<Vehiculo> vehiculos;
    private final ObservableList<Modalidad> modalidades;
    private final ObservableList<ServicioAdicional> servicios;
    private final ObservableList<Reserva> reservas;

    private SistemaController() {
        clientes = FXCollections.observableArrayList();
        vehiculos = FXCollections.observableArrayList();
        modalidades = FXCollections.observableArrayList();
        servicios = FXCollections.observableArrayList();
        reservas = FXCollections.observableArrayList();
    }

    public static SistemaController getInstance() {
        if (instance == null) {
            instance = new SistemaController();
        }
        return instance;
    }

    // Getters
    public ObservableList<Reserva> getReservas() {
        return reservas;
    }

    public ObservableList<ServicioAdicional> getServicios() {
        return servicios;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public ObservableList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ObservableList<Modalidad> getModalidades() {
        return modalidades;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    // Registro
    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public void registrarModalidad(Modalidad modalidad) {
        modalidades.add(modalidad);
    }

    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void registrarServicio(ServicioAdicional servicio) {
        servicios.add(servicio);
    }


    public Reporte generarReporte(LocalDate inicio, LocalDate fin) {
        List<Reserva> reservasFiltradas = reservas.stream()
                .filter(r -> !r.getFechaInicio().isBefore(inicio) && !r.getFechaFin().isAfter(fin))
                .collect(Collectors.toList());

        double ingresos = reservasFiltradas.stream()
                .mapToDouble(Reserva::calcularCostoTotal)
                .sum();

        int cantidadReservas = reservasFiltradas.size();
        int cantidadClientes = (int) reservasFiltradas.stream()
                .map(Reserva::getCliente)
                .distinct()
                .count();
        int cantidadVehiculos = (int) reservasFiltradas.stream()
                .map(Reserva::getVehiculo)
                .distinct()
                .count();

        return new Reporte(inicio, fin, ingresos, cantidadReservas, cantidadClientes, cantidadVehiculos, reservasFiltradas);
    }
    public boolean telefonoEsNumeroPerfecto(String telefono) {
        try {
            int numero = Integer.parseInt(telefono);
            int sumaDivisores = 0;
            for (int i = 1; i < numero; i++) {
                if (numero % i == 0) sumaDivisores += i;
            }
            return sumaDivisores == numero;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}