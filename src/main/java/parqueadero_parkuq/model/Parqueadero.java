package parqueadero_parkuq.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal del modelo que representa el parqueadero en su totalidad.
 * Contiene todas las listas de datos de la aplicación, como usuarios, vehículos, tarifas y espacios.
 * Funciona como el contenedor central de la información del sistema.
 */
public class Parqueadero {

    private ObservableList<Usuario> listUsuarios;
    private ObservableList<Espacio> listEspacios;
    private ObservableList<Tarifa> listTarifas;
    private ObservableList<Vehiculo> listVehiculos;
    private List<Administrador> listAdministrador;
    private List<Operario> listOperario;

    /**
     * Constructor de la clase Parqueadero.
     * Inicializa todas las listas de datos como listas vacías.
     */
    public Parqueadero() {
        this.listUsuarios = FXCollections.observableArrayList();
        this.listEspacios = FXCollections.observableArrayList();
        this.listTarifas = FXCollections.observableArrayList();
        this.listVehiculos = FXCollections.observableArrayList();
        this.listAdministrador = new ArrayList<>();
        this.listOperario = new ArrayList<>();
    }

    public ObservableList<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(ObservableList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public ObservableList<Espacio> getListEspacios() {
        return listEspacios;
    }

    public void setListEspacios(ObservableList<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }

    public ObservableList<Tarifa> getListTarifas() {
        return listTarifas;
    }

    public void setListTarifas(ObservableList<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    public ObservableList<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    public void setListVehiculos(ObservableList<Vehiculo> listVehiculos) {
        this.listVehiculos = listVehiculos;
    }

    public List<Administrador> getListAdministrador() {
        return listAdministrador;
    }

    public void setListAdministrador(List<Administrador> listAdministrador) {
        this.listAdministrador = listAdministrador;
    }

    public List<Operario> getListOperario() {
        return listOperario;
    }

    public void setListOperario(List<Operario> listOperario) {
        this.listOperario = listOperario;
    }
}