package parqueadero_parkuq.model;

public class Factura {
    private String concepto;
    private String valor;

    public Factura(String concepto, String valor) {
        this.concepto = concepto;
        this.valor = valor;
    }

    public String getConcepto() {
        return concepto;
    }

    public String getValor() {
        return valor;
    }
}