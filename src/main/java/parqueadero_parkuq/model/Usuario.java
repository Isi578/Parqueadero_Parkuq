package parqueadero_parkuq.model;

public class Usuario implements IDescuento {
    private String nombreusuario;
    private String idUsuario;
    private TipoUsuario tipoUsuario;

    public Usuario(String nombreusuario, String idUsuario, TipoUsuario tipoUsuario) {
        this.nombreusuario = nombreusuario;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

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
