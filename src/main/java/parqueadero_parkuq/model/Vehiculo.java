package parqueadero_parkuq.model;

/**
 * Representa un vehículo que ingresa al parqueadero.
 * Contiene información sobre la placa, el tipo de vehículo, el conductor,
 * la hora de ingreso y el espacio que ocupa.
 */
public class Vehiculo {

    private String placa;
    private TipoVehiculo tipoVehiculo;
    private String nombreConductor;
    private String idConductor;
    private String horaIngreso;
    private int espacioAsignado;
    private Boolean estado; // true si está dentro del parqueadero, false si ya salió.

    /**
     * Constructor para crear una nueva instancia de Vehiculo.
     *
     * @param placa           La placa del vehículo.
     * @param tipoVehiculo    El tipo de vehículo (ej. MOTO, CARRO).
     * @param nombreConductor El nombre del conductor.
     * @param idConductor     La identificación del conductor.
     * @param horaIngreso     La hora en que el vehículo ingresó.
     * @param espacioAsignado El código del espacio de parqueo asignado.
     * @param estado          El estado del vehículo (true si está dentro, false si ha salido).
     */
    public Vehiculo(String placa, TipoVehiculo tipoVehiculo, String nombreConductor, String idConductor, String horaIngreso, int espacioAsignado, boolean estado) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreConductor = nombreConductor;
        this.idConductor = idConductor;
        this.horaIngreso = horaIngreso;
        this.espacioAsignado = espacioAsignado;
        this.estado = estado;
    }

    /**
     * Obtiene la placa del vehículo.
     *
     * @return La placa del vehículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece la placa del vehículo.
     *
     * @param placa La nueva placa del vehículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene el tipo de vehículo.
     *
     * @return El tipo de vehículo.
     */
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Establece el tipo de vehículo.
     *
     * @param tipoVehiculo El nuevo tipo de vehículo.
     */
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * Obtiene el nombre del conductor.
     *
     * @return El nombre del conductor.
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * Establece el nombre del conductor.
     *
     * @param nombreConductor El nuevo nombre del conductor.
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    /**
     * Obtiene la identificación del conductor.
     *
     * @return La identificación del conductor.
     */
    public String getIdConductor() {
        return idConductor;
    }

    /**
     * Establece la identificación del conductor.
     *
     * @param idConductor La nueva identificación del conductor.
     */
    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    /**
     * Obtiene la hora de ingreso del vehículo.
     *
     * @return La hora de ingreso.
     */
    public String getHoraIngreso() {
        return horaIngreso;
    }

    /**
     * Establece la hora de ingreso del vehículo.
     *
     * @param horaIngreso La nueva hora de ingreso.
     */
    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    /**
     * Obtiene el código del espacio asignado al vehículo.
     *
     * @return El código del espacio.
     */
    public int getEspacioAsignado() {
        return espacioAsignado;
    }

    /**
     * Establece el espacio asignado al vehículo.
     *
     * @param espacioAsignado El nuevo código del espacio.
     */
    public void setEspacioAsignado(int espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    /**
     * Obtiene el estado del vehículo.
     *
     * @return true si el vehículo está actualmente en el parqueadero, false si ya salió.
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * Establece el estado del vehículo.
     *
     * @param estado El nuevo estado del vehículo.
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación en cadena del objeto Vehiculo.
     *
     * @return Una cadena con los detalles del vehículo.
     */
    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipoVehiculo=" + tipoVehiculo +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", idConductor='" + idConductor + '\'' +
                ", horaIngreso='" + horaIngreso + '\'' +
                ", espacioAsignado=" + espacioAsignado +
                ", estado=" + estado +
                '}';
    }
}