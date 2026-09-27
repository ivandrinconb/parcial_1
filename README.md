## Desarrollo del pensamiento computacional

### Abstracción

**¿Qué se solicita finalmente? (problema)**
Desarrollar un sistema de información para la empresa RentCar que permita gestionar el registro de clientes, vehículos, modalidades de alquiler y servicios adicionales, así como la creación de reservas y la realización de consultas específicas (búsqueda de cliente por teléfono con verificación de número perfecto, y cálculo de ingresos generados en un periodo), todo a través de una interfaz gráfica que facilite la interacción del personal encargado.

**¿Qué información es relevante dado el problema anterior?**
- Datos del cliente: nombre completo, documento de identidad, teléfono, correo electrónico, edad y fecha de registro
- Datos del vehículo: placa, marca, modelo, año, tipo y tarifa diaria
- Datos de la modalidad de alquiler: código, nombre, descripción, duración mínima en días, valor diario y estado (Disponible, Suspendida, Finalizada), además de los datos adicionales que requiere la modalidad Premium (tipo de cobertura, conductores adicionales permitidos, características especiales)
- Datos del servicio adicional: código, nombre, descripción, precio y disponibilidad
- Datos de la empresa: nombre comercial, NIT, dirección, teléfono, correo electrónico y página web
- La relación entre cliente, vehículo, modalidad y servicios adicionales dentro de cada reserva

**¿Cómo se agrupa la información relevante?**
La información se agrupa en las siguientes entidades del dominio: `Cliente`, `Vehiculo`, `Modalidad` (con sus variantes Económica, Ejecutiva y Premium), `ServicioAdicional`, `Reserva` y `Empresa`. Adicionalmente, se agrupa en una entidad de salida `Reporte`, que consolida la información generada a partir de las reservas para responder a la consulta de ingresos por periodo.

**¿Qué funcionalidades se solicitan finalmente?**
- Registrar clientes, vehículos, modalidades de alquiler y servicios adicionales
- Crear una reserva asociando un cliente, un vehículo, una modalidad y los servicios adicionales solicitados
- Calcular el valor final del alquiler considerando la modalidad, la duración y los servicios incluidos
- Buscar un cliente a partir de su número de teléfono y determinar si dicho número es un número perfecto
- Calcular los ingresos totales generados por las reservas dentro de un periodo determinado

### Descomposición

**¿Cómo se distribuyen las funcionalidades?**
El sistema se distribuye siguiendo el patrón MVC:
- **model**: contiene las entidades del dominio (`Cliente`, `Vehiculo`, `Empresa`, `Reporte`)
- **patrones**: contiene la lógica de creación y comportamiento especializado, organizada por patrón de diseño (Singleton, Factory Method, Builder, Prototype)
- **controller**: contiene los controladores de JavaFX que conectan la interfaz gráfica con la lógica de negocio
- **view**: contiene las vistas FXML de cada módulo (Clientes, Vehículos, Modalidades, Reservas, Reportes)

**¿Qué debo hacer para probar las funcionalidades?**
- Probar el registro de cada entidad verificando que los datos ingresados se reflejen correctamente en las tablas de la interfaz
- Probar la creación de una reserva completa y validar que el cálculo del valor final sea correcto según la modalidad y los servicios elegidos
- Probar la búsqueda de cliente por teléfono con números que sí y que no correspondan a números perfectos (ejemplo: 6, 28, 496)
- Probar el cálculo de ingresos con reservas dentro y fuera del rango de fechas consultado
- Validar los casos de error (campos vacíos, valores no numéricos)

### Reconocimiento de patrones

**¿Qué puedo reutilizar de la solución de otros problemas?**
La lógica de registro es prácticamente la misma para varias entidades del sistema (`Cliente`, `Vehiculo`, `Modalidad`, `ServicioAdicional`): un formulario que captura datos, los valida, crea el objeto correspondiente y lo agrega a una lista observable que se refleja en una tabla. Esa misma estructura se reutiliza en cada módulo, cambiando solo los campos propios de cada entidad. De igual forma, la forma de crear objetos especializados a partir de un tipo (como las distintas modalidades de alquiler) es un problema recurrente en el diseño orientado a objetos, y se resuelve reutilizando el mismo esquema de creación (una clase creadora abstracta con subclases concretas) para cualquier familia de objetos que varíe según un tipo o categoría.

### Codificación

**¿Cómo pruebo la solución en Java?**
Mediante pruebas manuales desde la interfaz gráfica (registrando datos de prueba y verificando el comportamiento esperado), y validando puntualmente los métodos de cálculo (`calcularCostoTotal()`, `telefonoEsNumeroPerfecto()`, `generarReporte()`) con datos de entrada conocidos cuyo resultado se puede verificar manualmente.

**¿Cómo escribo la solución en Java?**
Aplicando principios SOLID (clases con responsabilidad única, dependencia de abstracciones como `IModalidad` e `IPrototype`), herencia y polimorfismo para las modalidades de alquiler, y los cuatro patrones creacionales (Singleton en `SistemaController`, Factory Method en la jerarquía de `ModalidadFactory`, Builder en `Reserva.Builder` y Prototype en `ServicioAdicional`), organizando el código en paquetes según su responsabilidad dentro de la arquitectura MVC.
