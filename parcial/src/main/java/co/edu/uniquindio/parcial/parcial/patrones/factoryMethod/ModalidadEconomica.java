package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class ModalidadEconomica extends Modalidad {
    public ModalidadEconomica() {
        super("ECO", "Economica", "Modalidad economica con kilometraje incluido",
                1, 50.0, EstadoModalidad.DISPONIBLE);
    }
}