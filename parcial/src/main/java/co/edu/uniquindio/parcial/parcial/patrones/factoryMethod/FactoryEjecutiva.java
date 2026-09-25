package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public class FactoryEjecutiva extends ModalidadFactory {
    @Override
    public IModalidad crearModalidad() {
        return new ModalidadEjecutiva();
    }
}
