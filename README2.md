# Sistema RentCar

Sistema de gestión de reservas vehiculares desarrollado para el Parcial 1.

El sistema permite administrar clientes, vehículos, modalidades de servicio, reservas, servicios adicionales y reportes, aplicando principios de Programación Orientada a Objetos y patrones de diseño creacionales.

## Patrones de diseño implementados

- **Singleton** — `SistemaController` centraliza la gestión de clientes, vehículos, modalidades, reservas y reportes mediante una única instancia global.

- **Factory Method** — `ModalidadFactory` y sus subclases (`FactoryEconomica`, `FactoryEjecutiva`, `FactoryPremium`) permiten crear diferentes tipos de modalidades sin depender directamente de sus clases concretas.

- **Builder** — `ReservaBuilder` construye objetos `Reserva` paso a paso, facilitando la creación de reservas complejas.

- **Prototype** — `ServicioAdicional` implementa `IPrototype<T>` para clonar servicios previamente configurados.

## Funcionalidades principales

- Registro y administración de clientes.
- Registro y administración de vehículos.
- Gestión de modalidades de servicio.
- Creación de reservas.
- Asociación de servicios adicionales a las reservas.
- Cálculo de costos de reserva.
- Generación de reportes y estadísticas.
- Consulta de información desde el dashboard.

## Estructura del sistema

### Entidades principales

- Empresa
- Cliente
- Vehículo
- Reserva
- Modalidad
- ServicioAdicional
- Reporte

### Modalidades disponibles

- ModalidadEconomica
- ModalidadEjecutiva
- ModalidadPremium

### Controladores

- ClienteController
- VehiculoController
- ModalidadController
- ReservaController
- ReportesController
- DashboardController

## Relaciones UML

- Un cliente puede realizar varias reservas.
- Un vehículo puede estar asociado a varias reservas.
- Una reserva utiliza una modalidad específica.
- Una reserva puede contener múltiples servicios adicionales.
- Las modalidades heredan de la clase base `Modalidad`.
- `Modalidad` implementa la interfaz `IModalidad`.

## Uso

1. Registrar clientes.
2. Registrar vehículos.
3. Registrar modalidades.
4. Crear reservas.
5. Agregar servicios adicionales.
6. Consultar información desde el dashboard.
7. Generar reportes del sistema.

## Integrantes

- Estefanía Cubides Restrepo 
- Ivan Dario Rincon Betancourt
