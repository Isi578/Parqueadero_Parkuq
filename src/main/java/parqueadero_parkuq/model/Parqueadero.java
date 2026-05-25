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
     * metodo para verficar que el vehiculo no este registrado.
     * @param placa
     * @return
     */
    public boolean verificarVehiculo(String placa) {
        for (Vehiculo vehiculo : listVehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return false;
            }
        }
        return true;
    }

    /**
     * metodo de registro de vehiculo.
     * @param vehiculo
     * @return
     */
    public Vehiculo registrarVehiculo(Vehiculo vehiculo){
        if (verificarVehiculo(vehiculo.getPlaca())){
            this.listVehiculos.add(vehiculo);
        }
        return vehiculo;
    }

    /**
     * metodo para buscar un vehiculo.
     * @param placa
     * @return
     */
    public Vehiculo buscarVehiculo(String placa){
        for (Vehiculo vehiculo : listVehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * metodo para calcular el pago de un vehiculo.
     * @param vehiculo
     * @param horas
     * @return
     */
    public double calcularPago(Vehiculo vehiculo, int horas) {
        Tarifa tarifa = buscarTarifa(vehiculo.getTipoVehiculo());
        Usuario usuario = buscarUsuario(vehiculo.getIdConductor());
        double total = tarifa.calcularTarifa(horas, usuario);
        return total;
    }

    /**
     * metodo para eliminar un vehiculo.
     * @param placa
     * @return
     */
    public boolean eliminarVehiculo(String placa){
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo != null) {
            listVehiculos.remove(vehiculo);
            return true;
        }
        return  false;
    }

    /**
     * metodo para observar la lista de vehiculos.
     * @return
     */
    public ArrayList<Vehiculo> obtenerVehiculos(){
        return new ArrayList<>(listVehiculos);
    }

    /**
     * metodo para agregar un espacio.
     * @param espacio
     */
    public void agregarEspacio(Espacio espacio){
        listEspacios.add(espacio);
    }

    /**
     * metodo para buscar un espacio.
     * @param codigo
     * @return
     */
    public Espacio buscarEspacio(int codigo){
        for (Espacio espacio : listEspacios){
            if(espacio.getCodigo() == codigo){
                return espacio;
            }
        }
        return null;
    }

    /**
     * metodo para eliminar un espacio.
     * @param codigo
     * @return
     */
    public boolean eliminarEspacio(int codigo){
        Espacio espacio = buscarEspacio(codigo);
        if(espacio != null){
            listEspacios.remove(espacio);
            return true;
        }
        return false;
    }

    /**
     * metodo para verificar que el espacio no este ocupado.
     * @param placa
     * @return
     */
    public boolean verificarEspacioOcupado(String placa) {
        for (Espacio espacio : listEspacios) {
            if (espacio.getVehiculoAsignado() != null &&
                    espacio.getVehiculoAsignado().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    /**
     * metodo para observar la lista de espacios.
     * @return
     */
    public ArrayList<Espacio> obtenerEspacios(){
        return new ArrayList<>(listEspacios);
    }

    /**
     * metodo de registrar un usuario.
     * @param usuario
     */
    public void registrarUsuario(Usuario usuario) {
        listUsuarios.add(usuario);
    }

    /**
     * metodo para buscar un
     * @param idUsuario
     * @return
     */
    public Usuario buscarUsuario(String idUsuario) {
        for (Usuario usuario : listUsuarios) {
            if (usuario.getIdUsuario().equals(idUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * metodo para modificar un usuario.
     * @param identificacion
     * @param nuevoUsuario
     * @return
     */
    public boolean modificarUsuario(String identificacion, Usuario nuevoUsuario) {
        Usuario usuarioEncontrado =
                buscarUsuario(identificacion);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNombreusuario(
                    nuevoUsuario.getNombreusuario());

            usuarioEncontrado.setTipoUsuario(
                    nuevoUsuario.getTipoUsuario());

            return true;
        }

        return false;
    }

    /**
     * metodo para eliminar un usuario.
     * @param idUsuario
     * @return
     */
    public boolean eliminarUsuario(String idUsuario) {
        Usuario usuario =
                buscarUsuario(idUsuario);
        if (usuario != null) {
            listUsuarios.remove(usuario);
            return true;
        }
        return false;
    }

    /**
     * metodo para observar la lista de usuarios.
     * @return
     */
    public ArrayList<Usuario> obtenerUsuariosAutorizados(){
        return new ArrayList<>(listUsuarios);
    }

    /**
     * metodo para agregar tarifa.
     * @param tarifa
     */
    public void agregarTarifa(Tarifa tarifa){
        listTarifas.add(tarifa);
    }

    /**
     * metodo para buscar una tarifa.
     * @param tipoVehiculo
     * @return
     */
    public Tarifa buscarTarifa(TipoVehiculo tipoVehiculo){
        for(Tarifa tarifa : listTarifas){
            if(tarifa.getTipoVehiculo() == tipoVehiculo){
                return tarifa;
            }
        }
        return null;
    }

    /**
     * metodo para observar la lista de tarifas.
     * @return
     */
    public ArrayList<Tarifa> obtenerTarifas(){
        return new ArrayList<>(listTarifas);
    }


}