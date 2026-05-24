package parqueadero_parkuq.dataUtil;

import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoEspacio;
import parqueadero_parkuq.model.TipoUsuario;
import parqueadero_parkuq.model.Usuario;

/**
 * Clase de utilidad para proporcionar datos iniciales y constantes a la aplicación.
 * Contiene las credenciales de inicio de sesión y métodos para inicializar el estado del parqueadero.
 */
public class Datautil {

    public static final String OPERARIO = "Operario";
    public static final String OPERA_CONTRASENA = "operario123";
    public static final String ADMINISTRADOR = "Administrador";
    public static final String ADMIN_CONTRASENA = "administrador123";

    /**
     * Crea e inicializa el objeto principal del parqueadero con datos de prueba.
     *
     * @return Una nueva instancia de {@link Parqueadero} con datos iniciales.
     */
    public static Parqueadero inicializarDatos() {
        Parqueadero parqueadero = new Parqueadero();

        Usuario user1 = new Usuario("Isabela Quintero", "1001", TipoUsuario.ESTUDIANTE);
        Usuario user2 = new Usuario("Juan Perez", "1002", TipoUsuario.DOCENTE);
        Usuario user3 = new Usuario("Maria Garcia", "1003", TipoUsuario.ADMINISTRATIVO);
        Usuario user4 = new Usuario("Camilo Olarte", "1004", TipoUsuario.VISITANTE);

        parqueadero.getListUsuarios().add(user1);
        parqueadero.getListUsuarios().add(user2);
        parqueadero.getListUsuarios().add(user3);

        for (int i = 1; i <= 5; i++) {
            parqueadero.getListEspacios().add(new Espacio(i, TipoEspacio.CARRO, true, null));
        }
        for (int i = 6; i <= 10; i++) {
            parqueadero.getListEspacios().add(new Espacio(i, TipoEspacio.MOTOCICLETA, true, null));
        }
        for (int i = 11; i<=20; i++){
            parqueadero.getListEspacios().add(new Espacio(i, TipoEspacio.BICICLETA, true, null));
        }
        return parqueadero;
    }
}