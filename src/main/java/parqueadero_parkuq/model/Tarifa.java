package parqueadero_parkuq.model;

/**
 * Representa una tarifa de parqueo para un tipo de vehículo específico.
 * Esta clase define el costo por hora y puede calcular el total a pagar aplicando descuentos.
 */
public class Tarifa {

    private TipoVehiculo tipoVehiculo;
    private double valorHora;
    /**
     * Constructor para crear una nueva instancia de Tarifa.
     *
     * @param tipoVehiculo El tipo de vehículo al que aplica la tarifa (ej. MOTO, CARRO).
     * @param valorHora    El costo por hora de parqueo.
     * */
    public Tarifa(TipoVehiculo tipoVehiculo, double valorHora) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorHora = valorHora;
    }

    /**
     * Obtiene el tipo de vehículo de la tarifa.
     *
     * @return El tipo de vehículo.
     */
    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Establece el tipo de vehículo de la tarifa.
     *
     * @param tipoVehiculo El nuevo tipo de vehículo.
     */
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * Obtiene el valor por hora de la tarifa.
     *
     * @return El valor por hora.
     */
    public double getValorHora() {
        return valorHora;
    }

    /**
     * Establece el valor por hora de la tarifa.
     *
     * @param valorHora El nuevo valor por hora.
     */
    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    /**
     * Devuelve una representación en cadena del objeto Tarifa.
     *
     * @return Una cadena con los detalles de la tarifa.
     */
    @Override
    public String toString() {
        return "Tarifa{" +
                "tipoVehiculo=" + tipoVehiculo +
                ", valorHora=" + valorHora +
                '}';
    }

    /**
     * Calcula el costo total del parqueo para un número de horas y un tipo de usuario.
     * El método aplica el descuento correspondiente al usuario sobre el total.
     *
     * @param horas   El número de horas que el vehículo estuvo estacionado.
     * @param usuario El usuario al que se le aplicará el descuento.
     * @return El costo total a pagar después de aplicar el descuento.
     */
    public double calcularTarifa(double horas, IDescuento usuario) {
        if(horas < 0){
            return 0;
        }
        double total = horas * valorHora;
        double descuentoUsuario = usuario.obtenerDescuento();
        total = total - (total * descuentoUsuario);
        return total;
    }
}