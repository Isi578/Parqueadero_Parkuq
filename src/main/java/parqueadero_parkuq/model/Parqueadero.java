package parqueadero_parkuq.model;

import java.util.ArrayList;
import java.util.List;

public class Parqueadero {

    private List<Usuario> listUsuarios;
    private List<Vehiculo> listVehiculos;
    private List<Tarifa> listTarifas;
    private List<Espacio> listEspacios;

    public Parqueadero() {
        this.listUsuarios = new ArrayList<>();
        this.listVehiculos = new  ArrayList<>();
        this.listTarifas = new  ArrayList<>();
        this.listEspacios = new  ArrayList<>();
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    public void setListVehiculos(List<Vehiculo> listVehiculos) {
        this.listVehiculos = listVehiculos;
    }

    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    public void setListTarifas(List<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    public List<Espacio> getListEspacios() {
        return listEspacios;
    }

    public void setListEspacios(List<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }
}
