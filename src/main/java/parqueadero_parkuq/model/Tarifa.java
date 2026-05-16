package parqueadero_parkuq.model;

public class Tarifa {
    private TipoVehiculo tipoVehiculo;
    private double valorHora;
    private double descuento;

    public Tarifa (TipoVehiculo tipoVehiculo, double valorHora, double descuento) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = valorHora;
        this.descuento = descuento;
    }

}
