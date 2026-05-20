package parqueadero_parkuq.model;

public class Espacio {

        private int codigo;
        private TipoEspacio tipoEspacio;
        private boolean estado;
        private String vehiculoAsignado;

        public Espacio(int codigo, TipoEspacio tipoEspacio, boolean estado, String vehiculoAsignado) {
            this.codigo = codigo;
            this.tipoEspacio = tipoEspacio;
            this.estado = estado;
            this.vehiculoAsignado = vehiculoAsignado;
        }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public void setVehiculoAsignado(String vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }
}

