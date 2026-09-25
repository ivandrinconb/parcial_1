package co.edu.uniquindio.parcial.parcial.patrones.factoryMethod;

public abstract class Modalidad implements IModalidad {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionMinimaDias;
    protected double valorDiario;
    protected EstadoModalidad estado;

    public Modalidad(String codigo, String nombre, String descripcion,
                     int duracionMinimaDias, double valorDiario, EstadoModalidad estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMinimaDias = duracionMinimaDias;
        this.valorDiario = valorDiario;
        this.estado = estado;
    }

    public String getDescripcion() { return descripcion; }
    public int getDuracionMinimaDias() { return duracionMinimaDias; }
    public EstadoModalidad getEstado() { return estado; }

    @Override
    public String getCodigo() { return codigo; }
    @Override
    public String getNombre() { return nombre; }
    @Override
    public double getValorDiario() { return valorDiario; }




}
