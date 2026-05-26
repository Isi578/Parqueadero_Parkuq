package parqueadero_parkuq.model;

/**
 * Representa una fila de datos en un reporte diario.
 * Esta clase se utiliza para estructurar la información que se muestra en la vista de reportes.
 */
public class ReporteDiario {
    private String concepto;
    private String valor;

    /**
     * Constructor para crear una nueva instancia de ReporteDiario.
     *
     * @param concepto La descripción del dato (ej. "Total Vehículos").
     * @param valor    El valor asociado a ese concepto (ej. "150").
     */
    public ReporteDiario(String concepto, String valor) {
        this.concepto = concepto;
        this.valor = valor;
    }

    /**
     * Obtiene el concepto o descripción del dato del reporte.
     *
     * @return El concepto.
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Establece el concepto del dato del reporte.
     *
     * @param concepto El nuevo concepto.
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * Obtiene el valor del dato del reporte.
     *
     * @return El valor.
     */
    public String getValor() {
        return valor;
    }

    /**
     * Establece el valor del dato del reporte.
     *
     * @param valor El nuevo valor.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
}