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
    private List<Espacio> listEspacios;
    private List<Tarifa> listTarifas;
    private List<Vehiculo> listVehiculos;
    private List<Administrador> listAdministrador;
    private List<Operario> listOperario;

    /**
     * Constructor de la clase Parqueadero.
     * Inicializa todas las listas de datos como listas vacías.
     */
    public Parqueadero() {
        this.listUsuarios = new ArrayList<>();
        this.listEspacios = new ArrayList<>();
        this.listTarifas = new ArrayList<>();
        this.listVehiculos = new ArrayList<>();
        this.listAdministrador = new ArrayList<>();
        this.listOperario = new ArrayList<>();
    }

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(ObservableList<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Espacio> getListEspacios() {
        return listEspacios;
    }

    public void setListEspacios(ObservableList<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }

    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    public void setListTarifas(ObservableList<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    public List<Vehiculo> getListVehiculos() {
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

    /**
     *
     * @param idUsuario
     * @return
     */
    public boolean verificarUsuario(String idUsuario) {
        boolean existe = false;
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                existe = true;
            }
        }
        return existe;
    }

    /**
     *
     * @param usuario
     * @return
     *
     */
    public Usuario registrarUsuario(Usuario usuario) {
        if (verificarUsuario(usuario.getIdUsuario())) {
            this.listUsuarios.add(usuario);
        }
        return usuario;
    }

    /**
     *
     * @param placa
     * @return
     */
    public boolean verificarVehiculo (String placa){
        boolean existe = false;
        for (Vehiculo vehiculo : listVehiculos){
            if (vehiculo.getPlaca().equals(placa)){
                existe = true;
            }
        }
        return existe;
    }

    public Vehiculo registarVehiculo(Vehiculo vehiculo){
        if (verificarVehiculo(vehiculo.getPlaca())){
            this.listVehiculos.add(vehiculo);
        }
        return vehiculo;
    }

}