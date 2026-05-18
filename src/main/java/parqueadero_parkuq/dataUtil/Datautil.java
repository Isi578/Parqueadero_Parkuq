package parqueadero_parkuq.dataUtil;

import parqueadero_parkuq.model.Parqueadero;

public class Datautil {

    public static final String OPERADOR = "Operador";
    public static final String OPERA_CONTRASENA = "operario123";
    public static final String ADMINISTRADOR = "Administrador";
    public static final String ADMIN_CONTRASENA = "administrador123";

    public static Parqueadero inicializarDatos() {
        Parqueadero parqueadero = new Parqueadero();

        return parqueadero;
    }

}
