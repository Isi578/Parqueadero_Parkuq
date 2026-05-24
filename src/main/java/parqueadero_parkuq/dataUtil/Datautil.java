package parqueadero_parkuq.dataUtil;

import parqueadero_parkuq.model.Parqueadero;

/**
 * Clase de utilidad para proporcionar datos iniciales y constantes a la aplicación.
 * Contiene las credenciales de inicio de sesión y métodos para inicializar el estado del parqueadero.
 */
public class Datautil {

    /**
     * Rol para el usuario operario.
     */
    public static final String OPERARIO = "Operario";

    /**
     * Contraseña para el usuario operario.
     */
    public static final String OPERA_CONTRASENA = "operario123";

    /**
     * Rol para el usuario administrador.
     */
    public static final String ADMINISTRADOR = "Administrador";

    /**
     * Contraseña para el usuario administrador.
     */
    public static final String ADMIN_CONTRASENA = "administrador123";

    /**
     * Crea e inicializa el objeto principal del parqueadero.
     * En futuras versiones, este método podría usarse para cargar datos desde archivos o una base de datos.
     *
     * @return Una nueva instancia de {@link Parqueadero}.
     */
    public static Parqueadero inicializarDatos() {
        Parqueadero parqueadero = new Parqueadero();
        return parqueadero;
    }
}