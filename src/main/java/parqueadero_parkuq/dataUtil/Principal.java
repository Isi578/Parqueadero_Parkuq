package parqueadero_parkuq.dataUtil;

public class Principal {
    private Parqueadero parqueadero;

    private static Principal instance;

    private Principal() {
        this.parqueadero = Datautil.inicializarDatos();
    }

    public static Principal getInstance() {
        if (instance == null) {
            instance = new Principal ();
        }
        return instance;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }
}
