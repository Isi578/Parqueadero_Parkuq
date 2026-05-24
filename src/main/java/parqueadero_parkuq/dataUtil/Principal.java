package parqueadero_parkuq.dataUtil;

import parqueadero_parkuq.model.Parqueadero;

/**
 * Clase principal que gestiona el acceso a la instancia única del parqueadero.
 * Implementa el patrón de diseño Singleton para asegurar que solo exista una instancia
 * del modelo de datos (Parqueadero) en toda la aplicación.
 */
public class Principal {
    private Parqueadero parqueadero;

    private static Principal instance;

    /**
     * Constructor privado para prevenir la creación de instancias desde fuera de la clase.
     * Inicializa el parqueadero con los datos por defecto.
     */
    private Principal() {
        this.parqueadero = Datautil.inicializarDatos();
    }

    /**
     * Devuelve la instancia única de la clase Principal.
     * Si la instancia no existe, la crea (inicialización perezosa).
     *
     * @return La única instancia de {@link Principal}.
     */
    public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal();
        }
        return instance;
    }

    /**
     * Obtiene el objeto principal del parqueadero que contiene todos los datos de la aplicación.
     *
     * @return La instancia del {@link Parqueadero}.
     */
    public Parqueadero getParqueadero() {
        return parqueadero;
    }
}