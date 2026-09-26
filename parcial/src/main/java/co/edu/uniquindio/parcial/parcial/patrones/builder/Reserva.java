package co.edu.uniquindio.parcial.parcial.patrones.builder;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.IModalidad;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.Modalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Modalidad modalidad;
    private List<ServicioAdicional> servicios;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int dias;

    // Getters
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public Modalidad getModalidad() { return modalidad; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public List<ServicioAdicional> getServicios() { return servicios; }

    public double calcularCostoTotal() {
        double costoBase = dias * modalidad.getValorDiario();
        double extras = servicios.stream().mapToDouble(ServicioAdicional::getPrecio).sum();
        return costoBase + extras;
    }

    // Builder interno
    public static class Builder {
        private Cliente cliente;
        private Vehiculo vehiculo;
        private Modalidad modalidad;
        private List<ServicioAdicional> servicios = new ArrayList<>();
        private LocalDate fechaInicio;
        private LocalDate fechaFin;
        private int dias;

        public Builder cliente(Cliente c) { this.cliente = c; return this; }
        public Builder vehiculo(Vehiculo v) { this.vehiculo = v; return this; }
        public Builder modalidad(Modalidad m) { this.modalidad = m; return this; }
        public Builder servicios(List<ServicioAdicional> s) { this.servicios = s; return this; }
        public Builder fechaInicio(LocalDate f) { this.fechaInicio = f; return this; }
        public Builder fechaFin(LocalDate f) { this.fechaFin = f; return this; }
        public Builder dias(int d) { this.dias = d; return this; }

        public Reserva build() {
            Reserva r = new Reserva();
            r.cliente = this.cliente;
            r.vehiculo = this.vehiculo;
            r.modalidad = this.modalidad;
            r.servicios = this.servicios;
            r.fechaInicio = this.fechaInicio;
            r.fechaFin = this.fechaFin;
            r.dias = this.dias;
            return r;
        }
    }
}
