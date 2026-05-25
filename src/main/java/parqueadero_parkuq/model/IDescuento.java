package parqueadero_parkuq.model;

/**
 * Interfaz para objetos que pueden tener un descuento aplicable.
 * Define un contrato para que cualquier clase que la implemente (como {@link Usuario})
 * deba proporcionar una forma de obtener un porcentaje de descuento.
 */
public interface IDescuento {

    /**
     * Calcula y devuelve el descuento aplicable para el objeto.
     *
     * @return El valor del descuento como un double
     */
    double obtenerDescuento();
}