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

}
