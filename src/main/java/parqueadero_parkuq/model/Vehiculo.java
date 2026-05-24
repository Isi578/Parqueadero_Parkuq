package parqueadero_parkuq.model;

public class Vehiculo {

    private String placa;
    private TipoVehiculo tipoVehiculo;
    private String nombreConductor;
    private String idConductor;
    private String horaIngreso;
    private int espacioAsignado;
    private Boolean estado;

    public Vehiculo (String placa, TipoVehiculo tipoVehiculo,String nombreConductor, String idConductor,String horaIngreso,int espacioAsignado, boolean estado ){
        this.placa=placa;
        this.tipoVehiculo=tipoVehiculo;
        this.nombreConductor=nombreConductor;
        this.idConductor=idConductor;
        this.horaIngreso=horaIngreso;
        this.espacioAsignado=espacioAsignado;
        this.estado=estado;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(String idConductor) {
        this.idConductor = idConductor;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public int getEspacioAsignado() {
        return espacioAsignado;
    }

    public void setEspacioAsignado(int espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipoVehiculo=" + tipoVehiculo +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", idConductor='" + idConductor + '\'' +
                ", horaIngreso='" + horaIngreso + '\'' +
                ", espacioAsignado=" + espacioAsignado +
                ", estado=" + estado +
                '}';
    }
}
