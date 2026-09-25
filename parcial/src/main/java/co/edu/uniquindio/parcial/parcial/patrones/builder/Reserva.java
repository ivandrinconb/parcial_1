package co.edu.uniquindio.parcial.parcial.patrones.builder;

import co.edu.uniquindio.parcial.parcial.model.Cliente;
import co.edu.uniquindio.parcial.parcial.model.Vehiculo;
import co.edu.uniquindio.parcial.parcial.patrones.factoryMethod.IModalidad;
import co.edu.uniquindio.parcial.parcial.patrones.prototype.ServicioAdicional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private IModalidad modalidad;
    private List<ServicioAdicional> servicios;
    private int dias;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }

    public double calcularCostoTotal() {
        double total = modalidad.getValorDiario() * dias;
        if (servicios != null) {
            for (ServicioAdicional s : servicios) {
                total += s.getPrecio();
            }
        }
        return total;
    }


    private Reserva(Cliente cliente, Vehiculo vehiculo, IModalidad modalidad, List<ServicioAdicional> servicios, int dias, LocalDate fechaInicio, LocalDate fechaFin) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.modalidad = modalidad;
        this.servicios = servicios;
        this.dias = dias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public static class Builder {
        private Cliente cliente;
        private Vehiculo vehiculo;
        private IModalidad modalidad;
        private List<ServicioAdicional> servicios = new ArrayList<>();
        private int dias;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }
        public Builder vehiculo(Vehiculo vehiculo) {
            this.vehiculo = vehiculo;
            return this;
        }
        public Builder modalidad(IModalidad modalidad) {
            this.modalidad = modalidad;
            return this;
        }
        public Builder agregarServicio(ServicioAdicional servicio) { this.servicios.add(servicio); return this; }
        public Builder dias(int dias) {
            this.dias = dias;
            return this;
        }
        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
            return this;
        }

        public Builder fechaFin(LocalDate fechaFin) {
            this.fechaFin = fechaFin;
            return this;
        }

        public Reserva build() {
            return new Reserva(cliente, vehiculo, modalidad, servicios, dias,fechaInicio,fechaFin);
        }
    }
}
