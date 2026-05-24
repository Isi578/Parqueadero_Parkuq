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

    private List<Usuario> listUsuarios;
    private List<Vehiculo> listVehiculos;
    private List<Tarifa> listTarifas;
    private List<Espacio> listEspacios;
    private List<Administrador> listAdministrador;
    private List<Operario> listOperario;

    /**
     * Constructor de la clase Parqueadero.
     * Inicializa todas las listas de datos como listas vacías.
     */
    public Parqueadero() {
        this.listUsuarios = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
        this.listEspacios = new ArrayList<>();
        this.listAdministrador = new ArrayList<>();
        this.listOperario = new ArrayList<>();
    }

    /**
     * Obtiene la lista de todos los usuarios autorizados.
     *
     * @return Una lista de objetos {@link Usuario}.
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * Establece la lista de usuarios autorizados.
     *
     * @param listUsuarios La nueva lista de usuarios.
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Obtiene la lista de todos los vehículos registrados.
     *
     * @return Una lista de objetos {@link Vehiculo}.
     */
    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    /**
     * Establece la lista de vehículos.
     *
     * @param listVehiculos La nueva lista de vehículos.
     */
    public void setListVehiculos(List<Vehiculo> listVehiculos) {
        this.listVehiculos = listVehiculos;
    }

    /**
     * Obtiene la lista de todas las tarifas.
     *
     * @return Una lista de objetos {@link Tarifa}.
     */
    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    /**
     * Establece la lista de tarifas.
     *
     * @param listTarifas La nueva lista de tarifas.
     */
    public void setListTarifas(List<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    /**
     * Obtiene la lista de todos los espacios de estacionamiento.
     *
     * @return Una lista de objetos {@link Espacio}.
     */
    public List<Espacio> getListEspacios() {
        return listEspacios;
    }

    /**
     * Establece la lista de espacios de estacionamiento.
     *
     * @param listEspacios La nueva lista de espacios.
     */
    public void setListEspacios(List<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }

    /**
     * Obtiene la lista de administradores.
     *
     * @return Una lista de objetos {@link Administrador}.
     */
    public List<Administrador> getListAdministrador() {
        return listAdministrador;
    }

    /**
     * Establece la lista de administradores.
     *
     * @param listAdministrador La nueva lista de administradores.
     */
    public void setListAdministrador(List<Administrador> listAdministrador) {
        this.listAdministrador = listAdministrador;
    }

    /**
     * Obtiene la lista de operarios.
     *
     * @return Una lista de objetos {@link Operario}.
     */
    public List<Operario> getListOperario() {
        return listOperario;
    }

    /**
     * Establece la lista de operarios.
     *
     * @param listOperario La nueva lista de operarios.
     */
    public void setListOperario(List<Operario> listOperario) {
        this.listOperario = listOperario;
    }

    /**
     * Devuelve la lista de usuarios como una {@link ObservableList} de JavaFX.
     * Esto permite que las tablas y otros componentes de la interfaz se actualicen automáticamente
     * cuando la lista de usuarios cambia.
     *
     * @return Una lista observable de usuarios.
     */
    public ObservableList<Usuario> Usuario() {
        return FXCollections.observableArrayList(this.listUsuarios);
    }
}