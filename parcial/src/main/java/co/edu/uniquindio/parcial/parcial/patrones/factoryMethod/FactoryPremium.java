package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class FactoryPremium extends ModalidadFactory {
    @Override
    public IModalidad crearModalidad() {
        return new ModalidadPremium("Cobertura Total", 0, "Ninguna");
    }
}