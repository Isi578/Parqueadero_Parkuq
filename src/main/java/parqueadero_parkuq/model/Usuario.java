package parqueadero_parkuq.model;

/**
 * Representa a un usuario del parqueadero.
 * Esta clase contiene la información básica de un usuario, como su nombre, identificación y tipo,
 * y también implementa la lógica para calcular descuentos según el tipo de usuario.
 */
public class Usuario implements IDescuento {

    private String nombreusuario;
    private String idUsuario;
    private TipoUsuario tipoUsuario;

    /**
     * Constructor para crear una nueva instancia de Usuario.
     *
     * @param nombreusuario
     * @param idUsuario
     * @param tipoUsuario
     */
    public Usuario(String nombreusuario, String idUsuario, TipoUsuario tipoUsuario) {
        this.nombreusuario = nombreusuario;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombreusuario() {
        return nombreusuario;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombreusuario El nuevo nombre para el usuario.
     */
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    /**
     * Obtiene la identificación del usuario.
     *
     * @return La identificación del usuario.
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece la identificación del usuario.
     *
     * @param idUsuario La nueva identificación para el usuario.
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return El tipo de usuario.
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipoUsuario El nuevo tipo de usuario.
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve una representación en cadena del objeto Usuario.
     *
     * @return Una cadena con los detalles del usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombreusuario='" + nombreusuario + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }

    /**
     * Calcula el descuento aplicable según el tipo de usuario.
     *
     * @return El porcentaje de descuento (ej. 0.25 para 25%).
     */
    @Override
    public double obtenerDescuento() {
        switch (tipoUsuario) {
            case DOCENTE:
                return 0.15;
            case ESTUDIANTE:
                return 0.25;
            case ADMINISTRATIVO:
                return 0.20;
            default:
                return 0;
        }
    }
}