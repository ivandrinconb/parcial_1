package co.edu.uniquindio.parcial.parcial.patrones.singleton;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Empresa;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.builder.Reserva;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.IModalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaController {
    private static SistemaController instance;
    private Empresa empresa;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<IModalidad> modalidades;
    private List<ServicioAdicional> servicios;
    private List<Reserva> reservas;

    private SistemaController() {
        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        modalidades = new ArrayList<>();
        servicios = new ArrayList<>();
        reservas = new ArrayList<>();

        }
    public static SistemaController getInstance() {
        if (instance == null) {
            instance = new SistemaController();
        }
        return instance;
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

    public void registrarModalidad(IModalidad modalidad) {
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

    public boolean validarTelefonoPerfecto(Cliente cliente) {
        try {
            int telefono = Integer.parseInt(cliente.getTelefono());
            return esNumeroPerfecto(telefono);
        } catch (NumberFormatException e) {
            return false; // si el teléfono no es numérico
        }
    }


}
