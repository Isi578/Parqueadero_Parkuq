package parqueadero_parkuq.model;

/**
 * Representa un espacio o lugar de estacionamiento dentro del parqueadero.
 * Cada espacio tiene un código único, un tipo, un estado y puede tener un vehículo asignado.
 */
public class Espacio {

    private int codigo;
    private TipoEspacio tipoEspacio;
    private boolean estado;
    private String vehiculoAsignado;

    /**
     * Constructor para crear una nueva instancia de Espacio.
     *
     * @param codigo           El código o número identificador del espacio.
     * @param tipoEspacio      El tipo de espacio (ej. CARRO, MOTOCICLETA).
     * @param estado           El estado inicial del espacio (true si está disponible, false si está ocupado).
     * @param vehiculoAsignado La placa del vehículo asignado, o null si está libre.
     */
    public Espacio(int codigo, TipoEspacio tipoEspacio, boolean estado, String vehiculoAsignado) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = estado;
        this.vehiculoAsignado = vehiculoAsignado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el tipo de espacio.
     *
     * @return El tipo de espacio.
     */
    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    /**
     * Establece el tipo de espacio.
     *
     * @param tipoEspacio El nuevo tipo de espacio.
     */
    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    /**
     * Verifica el estado del espacio.
     *
     * @return true si el espacio está ocupado, false si está libre.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del espacio.
     *
     * @param estado El nuevo estado (true para ocupado, false para libre).
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la placa del vehículo asignado al espacio.
     *
     * @return La placa del vehículo, o null si el espacio está libre.
     */
    public String getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    /**
     * Asigna un vehículo a este espacio.
     *
     * @param vehiculoAsignado La placa del vehículo a asignar.
     */
    public void setVehiculoAsignado(String vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    /**
     * Devuelve una representación en cadena del objeto Espacio.
     *
     * @return Una cadena con los detalles del espacio.
     */    @Override
    public String toString() {
        return "Espacio{" +
                "codigo=" + codigo +
                ", tipoEspacio=" + tipoEspacio +
                ", estado=" + estado +
                ", vehiculoAsignado='" + vehiculoAsignado + '\'' +
                '}';
    }
}