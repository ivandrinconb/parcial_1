package co.edu.uniquindio.parcial.parcial.model;

import co.edu.uniquindio.parcial.parcial.patrones.builder.Reserva;
import java.time.LocalDate;
import java.util.List;

public class Reporte {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double ingresosTotales;
    private int cantidadReservas;
    private int cantidadClientes;
    private int cantidadVehiculos;
    private List<Reserva> reservasIncluidas;

    public Reporte(LocalDate inicio, LocalDate fin, double ingresosTotales,
                   int cantidadReservas, int cantidadClientes, int cantidadVehiculos,
                   List<Reserva> reservasIncluidas) {
        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.ingresosTotales = ingresosTotales;
        this.cantidadReservas = cantidadReservas;
        this.cantidadClientes = cantidadClientes;
        this.cantidadVehiculos = cantidadVehiculos;
        this.reservasIncluidas = reservasIncluidas;
    }


    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public double getIngresosTotales() { return ingresosTotales; }
    public int getCantidadReservas() { return cantidadReservas; }
    public int getCantidadClientes() { return cantidadClientes; }
    public int getCantidadVehiculos() { return cantidadVehiculos; }
    public List<Reserva> getReservasIncluidas() { return reservasIncluidas; }
}
