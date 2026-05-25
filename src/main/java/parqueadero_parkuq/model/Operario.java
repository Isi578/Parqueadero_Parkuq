package parqueadero_parkuq.model;

/**
 * Representa a un operario del sistema.
 * Esta clase contiene la información de autenticación para un rol de operario.
 */
public class Operario {

    private String nombreRol;
    private String contrasena;

    /**
     * Constructor para crear una nueva instancia de Operario.
     *
     * @param nombreRol  El nombre del rol (ej. "Operario").
     * @param contrasena La contraseña asociada a este rol.
     */
    public Operario(String nombreRol, String contrasena) {
        this.nombreRol = nombreRol;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el nombre del rol.
     *
     * @return El nombre del rol.
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombre del rol.
     *
     * @param nombreRol El nuevo nombre del rol.
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * Obtiene la contraseña del rol.
     *
     * @return La contraseña.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del rol.
     *
     * @param contrasena La nueva contraseña.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Devuelve una representación en cadena del objeto Operario.
     *
     * @return Una cadena con los detalles del rol.
     */
    @Override
    public String toString() {
        return "Operario{" +
                "nombreRol='" + nombreRol + '\'' +
                '}';
    }
}