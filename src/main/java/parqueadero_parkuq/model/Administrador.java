package parqueadero_parkuq.model;

public class Administrador {
    private String nombreRol;
    private String contrasena;

    public Administrador(String nombreRol, String contrasena) {
        this.nombreRol = nombreRol;
        this.contrasena = contrasena;
    }

    public String getNombreRol() {
        return nombreRol;
    }
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombreRol='" + nombreRol + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
