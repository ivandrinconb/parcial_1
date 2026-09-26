package co.edu.uniquindio.parcial.parcial.patrones.singleton;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Empresa;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.builder.Reserva;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.IModalidad;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.Modalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaController {
    private static SistemaController instance;
    private Empresa empresa;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private ObservableList<Modalidad> modalidades;
    private List<ServicioAdicional> servicios;
    private List<Reserva> reservas;

    private SistemaController() {
        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        modalidades = FXCollections.observableArrayList();
        servicios = new ArrayList<>();
        reservas = new ArrayList<>();

        }
    public static SistemaController getInstance() {
        if (instance == null) {
            instance = new SistemaController();
        }
        return instance;
    }

    public ObservableList<Modalidad> getModalidades() {
        return modalidades;
    }

    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Empresa getEmpresa() { return empresa; }


    // Métodos de negocio (ejemplos)
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

    // Calcular ingresos en un periodo
    public double calcularIngresos(LocalDate inicio, LocalDate fin) {
        double total = 0;
        for (Reserva r : reservas) {
            if ((r.getFechaInicio().isAfter(inicio) || r.getFechaInicio().isEqual(inicio)) &&
                    (r.getFechaFin().isBefore(fin) || r.getFechaFin().isEqual(fin))) {
                total += r.calcularCostoTotal();
            }
        }
        return total;
    }

    // Validar número perfecto
    public boolean esNumeroPerfecto(int numero) {
        int suma = 0;
        for (int i = 1; i < numero; i++) {
            if (numero % i == 0) suma += i;
        }
        return suma == numero;
    }

    // Buscar un cliente registrado por su número de teléfono
    public Cliente buscarClientePorTelefono(String telefono) {
        for (Cliente c : clientes) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    // Busca el cliente por teléfono y válida si ese número es perfecto
    public boolean telefonoEsNumeroPerfecto(String telefono) {
        Cliente cliente = buscarClientePorTelefono(telefono);
        if (cliente == null) {
            return false; // no hay cliente registrado con ese teléfono
        }
        try {
            int numero = Integer.parseInt(telefono);
            return esNumeroPerfecto(numero);
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
