package parqueadero_parkuq.model;

public class Usuario {
    private String nombreusuario;
    private String idUsuario;
    private TipoUsuario tipoUsuario;

    public Usuario(String nombreusuario, String idUsuario, TipoUsuario tipoUsuario) {
        this.nombreusuario=nombreusuario;
        this.idUsuario=idUsuario;
        this.tipoUsuario=tipoUsuario;
    }
}
