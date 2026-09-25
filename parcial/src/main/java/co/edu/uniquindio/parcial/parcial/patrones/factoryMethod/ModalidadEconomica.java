package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class ModalidadEconomica extends Modalidad {

    public ModalidadEconomica(String codigo, String nombre, String descripcion, int duracionMinimaDias, double valorDiario, EstadoModalidad estado) {
        super("EJE", "Economica", "Modalidad basica con seguro basico", 1, 50.0, EstadoModalidad.DISPONIBLE);
    }
}
