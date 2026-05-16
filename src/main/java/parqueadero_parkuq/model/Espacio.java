package parqueadero_parkuq.model;

public class Espacio {

        private int codigo;
        private TipoEspacio tipoEspacio;
        private String vehiculoAsignado;

        public Espacio(int codigo, TipoEspacio tipoEspacio, String vehiculoAsignado){
            this.codigo=codigo;
            this.tipoEspacio=tipoEspacio;
            this.vehiculoAsignado=vehiculoAsignado;
        }
}

