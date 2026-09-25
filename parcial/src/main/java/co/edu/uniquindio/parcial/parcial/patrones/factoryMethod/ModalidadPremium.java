package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class ModalidadPremium extends Modalidad {
    private String cobertura;
    private int conductoresAdicionales;
    private String caracteristicasEspeciales;

    public ModalidadPremium(String cobertura, int conductoresAdicionales, String caracteristicasEspeciales) {
        super("PRE", "Premium", "Modalidad premium con cobertura total",
                3, 200.0, EstadoModalidad.DISPONIBLE);
        this.cobertura = cobertura;
        this.conductoresAdicionales = conductoresAdicionales;
        this.caracteristicasEspeciales = caracteristicasEspeciales;
    }

    public String getCobertura() { return cobertura; }
    public int getConductoresAdicionales() { return conductoresAdicionales; }
    public String getCaracteristicasEspeciales() { return caracteristicasEspeciales; }
}
