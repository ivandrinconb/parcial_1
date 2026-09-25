package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class FactoryEconomica extends ModalidadFactory {
    @Override
    public IModalidad crearModalidad() {
        return new ModalidadEconomica();
    }
}
