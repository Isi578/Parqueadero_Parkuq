package parqueadero_parkuq.model;

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

    /**
     * Obtiene la lista de usuarios.
     *
     * @return La lista de usuarios.
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * Establece la lista de usuarios.
     *
     * @param listUsuarios La nueva lista de usuarios.
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Obtiene la lista de espacios.
     *
     * @return La lista de espacios.
     */
    public List<Espacio> getListEspacios() {
        return listEspacios;
    }

    /**
     * Establece la lista de espacios.
     *
     * @param listEspacios La nueva lista de espacios.
     */
    public void setListEspacios(List<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }

    /**
     * Obtiene la lista de tarifas.
     *
     * @return La lista de tarifas.
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
     * Obtiene la lista de vehículos.
     *
     * @return La lista de vehículos.
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
     * Obtiene la lista de administradores.
     *
     * @return La lista de administradores.
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
     * @return La lista de operarios.
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
     * Verifica si un vehículo con la placa especificada ya existe en el sistema.
     *
     * @param placa La placa del vehículo a verificar.
     * @return {@code true} si el vehículo existe, {@code false} en caso contrario.
     */
    public boolean vehiculoExiste(String placa) {
        for (Vehiculo vehiculo : listVehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registra un nuevo vehículo en el parqueadero.
     *
     * @param vehiculo El vehículo a registrar.
     * @return {@code true} si el registro fue exitoso, {@code false} si el vehículo ya existía o es nulo.
     */
    public boolean registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            return false;
        }
        if (!vehiculoExiste(vehiculo.getPlaca())) {
            listVehiculos.add(vehiculo);
            return true;
        }
        return false;
    }

    /**
     * Busca un vehículo por su placa.
     *
     * @param placa La placa del vehículo a buscar.
     * @return El objeto {@link Vehiculo} si se encuentra, o {@code null} si no existe.
     */
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo vehiculo : listVehiculos) {
            if (vehiculo.getPlaca().equals(placa)) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * Obtiene una lista de todos los vehículos que se encuentran actualmente dentro del parqueadero.
     *
     * @return Una {@link ArrayList} de vehículos con estado activo.
     */
    public ArrayList<Vehiculo> obtenerVehiculosDentro() {
        ArrayList<Vehiculo> vehiculosDentro = new ArrayList<>();
        for (Vehiculo vehiculo : listVehiculos) {
            if (vehiculo.getEstado()) {
                vehiculosDentro.add(vehiculo);
            }
        }
        return vehiculosDentro;
    }

    /**
     * Registra la salida de un vehículo del parqueadero.
     *
     * @param placa La placa del vehículo que sale.
     * @return {@code true} si la salida fue exitosa, {@code false} en caso contrario.
     */
    public boolean registrarSalida(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo != null && vehiculo.getEstado()) {
            vehiculo.setEstado(false);
            liberarEspacio(vehiculo.getEspacioAsignado());
            return true;
        }
        return false;
    }

    /**
     * Busca un espacio por su código.
     *
     * @param codigo El código del espacio a buscar.
     * @return El objeto {@link Espacio} si se encuentra, o {@code null} si no existe.
     */
    public Espacio buscarEspacio(int codigo) {
        for (Espacio espacio : listEspacios) {
            if (espacio.getCodigo() == codigo) {
                return espacio;
            }
        }
        return null;
    }

    /**
     * Asigna un vehículo a un espacio de parqueo.
     *
     * @param placa         La placa del vehículo a asignar.
     * @param codigoEspacio El código del espacio a ocupar.
     * @return {@code true} si la asignación fue exitosa, {@code false} en caso contrario.
     */
    public boolean asignarEspacio(String placa, int codigoEspacio) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        Espacio espacio = buscarEspacio(codigoEspacio);
        if (vehiculo != null && espacio != null && !espacio.isEstado()) {
            espacio.setVehiculoAsignado(placa);
            espacio.setEstado(true);
            vehiculo.setEspacioAsignado(codigoEspacio);
            return true;
        }
        return false;
    }

    /**
     * Libera un espacio de parqueo.
     *
     * @param codigoEspacio El código del espacio a liberar.
     * @return {@code true} si la liberación fue exitosa, {@code false} si el espacio no se encontró.
     */
    public boolean liberarEspacio(int codigoEspacio) {
        Espacio espacio = buscarEspacio(codigoEspacio);
        if (espacio != null) {
            espacio.setVehiculoAsignado(null);
            espacio.setEstado(false);
            return true;
        }
        return false;
    }

}