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
     * @return La lista de usuarios.
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * Establece la lista de usuarios.
     * @param listUsuarios La nueva lista de usuarios.
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    /**
     * Obtiene la lista de espacios.
     * @return La lista de espacios.
     */
    public List<Espacio> getListEspacios() {
        return listEspacios;
    }

    /**
     * Establece la lista de espacios.
     * @param listEspacios La nueva lista de espacios.
     */
    public void setListEspacios(List<Espacio> listEspacios) {
        this.listEspacios = listEspacios;
    }

    /**
     * Obtiene la lista de tarifas.
     * @return La lista de tarifas.
     */
    public List<Tarifa> getListTarifas() {
        return listTarifas;
    }

    /**
     * Establece la lista de tarifas.
     * @param listTarifas La nueva lista de tarifas.
     */
    public void setListTarifas(List<Tarifa> listTarifas) {
        this.listTarifas = listTarifas;
    }

    /**
     * Obtiene la lista de vehículos.
     * @return La lista de vehículos.
     */
    public List<Vehiculo> getListVehiculos() {
        return listVehiculos;
    }

    /**
     * Establece la lista de vehículos.
     * @param listVehiculos La nueva lista de vehículos.
     */
    public void setListVehiculos(List<Vehiculo> listVehiculos) {
        this.listVehiculos = listVehiculos;
    }

    /**
     * Obtiene la lista de administradores.
     * @return La lista de administradores.
     */
    public List<Administrador> getListAdministrador() {
        return listAdministrador;
    }

    /**
     * Establece la lista de administradores.
     * @param listAdministrador La nueva lista de administradores.
     */
    public void setListAdministrador(List<Administrador> listAdministrador) {
        this.listAdministrador = listAdministrador;
    }

    /**
     * Obtiene la lista de operarios.
     * @return La lista de operarios.
     */
    public List<Operario> getListOperario() {
        return listOperario;
    }

    /**
     * Establece la lista de operarios.
     * @param listOperario La nueva lista de operarios.
     */
    public void setListOperario(List<Operario> listOperario) {
        this.listOperario = listOperario;
    }

    /**
     * Verifica si un vehículo con la placa especificada ya existe en el sistema.
     * @param placa La placa del vehículo a verificar.
     * @return {@code true} si el vehículo existe, {@code false} en caso contrario.
     */
    public boolean vehiculoExiste(String placa){
        for(Vehiculo vehiculo : listVehiculos){
            if(vehiculo.getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }

    /**
     * Registra un nuevo vehículo en el parqueadero.
     * @param vehiculo El vehículo a registrar.
     * @return {@code true} si el registro fue exitoso, {@code false} si el vehículo ya existía o es nulo.
     */
    public boolean registrarVehiculo(Vehiculo vehiculo){
        if(vehiculo == null){
            return false;
        }
        if(!vehiculoExiste(vehiculo.getPlaca())){
            listVehiculos.add(vehiculo);
            return true;
        }
        return false;
    }

    /**
     * Busca un vehículo por su placa.
     * @param placa La placa del vehículo a buscar.
     * @return El objeto {@link Vehiculo} si se encuentra, o {@code null} si no existe.
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
     * Obtiene una lista de todos los vehículos que se encuentran actualmente dentro del parqueadero.
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
     * Calcula el monto a pagar por el estacionamiento de un vehículo.
     * @param vehiculo El vehículo para el cual se calcula el pago.
     * @param horas El número de horas que el vehículo estuvo estacionado.
     * @return El total a pagar, o 0 si los datos son inválidos.
     */
    public double calcularPago(Vehiculo vehiculo, int horas) {
        if(vehiculo == null || horas < 0){
            return 0;
        }
        Tarifa tarifa = buscarTarifa(vehiculo.getTipoVehiculo());
        Usuario usuario = buscarUsuario(vehiculo.getIdConductor());
        if(tarifa == null || usuario == null){
            return 0;
        }
        return tarifa.calcularTarifa(horas, usuario);
    }

    /**
     * Registra la salida de un vehículo del parqueadero.
     * @param placa La placa del vehículo que sale.
     * @return {@code true} si la salida fue exitosa, {@code false} en caso contrario.
     */
    public boolean registrarSalida(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);
        if(vehiculo != null && vehiculo.getEstado()) {
            vehiculo.setEstado(false);
            liberarEspacio(vehiculo.getEspacioAsignado());
            return true;
        }
        return false;
    }

    /**
     * Elimina un vehículo del sistema.
     * @param placa La placa del vehículo a eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    public boolean eliminarVehiculo(String placa){
        Vehiculo vehiculo = buscarVehiculo(placa);
        if (vehiculo != null) {
            liberarEspacio(vehiculo.getEspacioAsignado());
            listVehiculos.remove(vehiculo);
            return true;
        }
        return false;
    }

    /**
     * Obtiene una copia de la lista de todos los vehículos registrados.
     * @return Una {@link ArrayList} con todos los vehículos.
     */
    public ArrayList<Vehiculo> obtenerVehiculos(){
        return new ArrayList<>(listVehiculos);
    }

    /**
     * Agrega un nuevo espacio al parqueadero.
     * @param espacio El espacio a agregar.
     * @return {@code true} si se agregó correctamente, {@code false} si el espacio ya existía o es nulo.
     */
    public boolean agregarEspacio(Espacio espacio){
        if(espacio == null){
            return false;
        }
        if(buscarEspacio(espacio.getCodigo()) == null){
            listEspacios.add(espacio);
            return true;
        }
        return false;
    }

    /**
     * Busca un espacio por su código.
     * @param codigo El código del espacio a buscar.
     * @return El objeto {@link Espacio} si se encuentra, o {@code null} si no existe.
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
     * Busca un espacio disponible para un tipo de vehículo específico.
     * @param tipoEspacio El tipo de espacio requerido.
     * @return Un {@link Espacio} disponible, o {@code null} si no hay ninguno.
     */
    public Espacio buscarEspacioDisponible(TipoEspacio tipoEspacio) {
        for (Espacio espacio : listEspacios) {
            if (espacio.getTipoEspacio().equals(tipoEspacio) && !espacio.isEstado()) {
                return espacio;
            }
        }
        return null;
    }

    /**
     * Cuenta el número total de espacios disponibles en el parqueadero.
     * @return El número de espacios libres.
     */
    public int contarEspaciosDisponibles() {
        int contador = 0;
        for (Espacio espacio : listEspacios) {
            if (!espacio.isEstado()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Cuenta el número total de espacios ocupados en el parqueadero.
     * @return El número de espacios ocupados.
     */
    public int contarEspaciosOcupados() {
        int contador = 0;
        for (Espacio espacio : listEspacios) {
            if (espacio.isEstado()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Asigna un vehículo a un espacio de parqueo.
     * @param placa La placa del vehículo a asignar.
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

    /**
     * Elimina un espacio del parqueadero.
     * @param codigo El código del espacio a eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
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
     * Verifica si un vehículo con una placa dada está ocupando algún espacio.
     * @param placa La placa del vehículo a verificar.
     * @return {@code true} si el vehículo está ocupando un espacio, {@code false} en caso contrario.
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
     * Obtiene una copia de la lista de todos los espacios.
     * @return Una {@link ArrayList} con todos los espacios.
     */
    public ArrayList<Espacio> obtenerEspacios(){
        return new ArrayList<>(listEspacios);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario El usuario a registrar.
     * @return {@code true} si el registro fue exitoso, {@code false} si el usuario ya existía o es nulo.
     */
    public boolean registrarUsuario(Usuario usuario) {
        if(usuario == null){
            return false;
        }
        if(buscarUsuario(usuario.getIdUsuario()) == null){
            listUsuarios.add(usuario);
            return true;
        }
        return false;
    }

    /**
     * Busca un usuario por su ID.
     * @param idUsuario El ID del usuario a buscar.
     * @return El objeto {@link Usuario} si se encuentra, o {@code null} si no existe.
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
     * Modifica los datos de un usuario existente.
     * @param identificacion El ID del usuario a modificar.
     * @param nuevoUsuario Un objeto {@link Usuario} con los nuevos datos.
     * @return {@code true} si la modificación fue exitosa, {@code false} en caso contrario.
     */
    public boolean modificarUsuario(String identificacion, Usuario nuevoUsuario) {
        Usuario usuarioEncontrado = buscarUsuario(identificacion);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNombreusuario(nuevoUsuario.getNombreusuario());
            usuarioEncontrado.setTipoUsuario(nuevoUsuario.getTipoUsuario());
            return true;
        }
        return false;
    }

    /**
     * Elimina un usuario del sistema.
     * @param idUsuario El ID del usuario a eliminar.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    public boolean eliminarUsuario(String idUsuario) {
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario != null) {
            listUsuarios.remove(usuario);
            return true;
        }
        return false;
    }

    /**
     * Obtiene una copia de la lista de todos los usuarios autorizados.
     * @return Una {@link ArrayList} con todos los usuarios.
     */
    public ArrayList<Usuario> obtenerUsuariosAutorizados(){
        return new ArrayList<>(listUsuarios);
    }

    /**
     * Agrega una nueva tarifa al sistema.
     * @param tarifa La tarifa a agregar.
     */
    public void agregarTarifa(Tarifa tarifa){
        listTarifas.add(tarifa);
    }

    /**
     * Busca una tarifa por tipo de vehículo.
     * @param tipoVehiculo El tipo de vehículo para el cual se busca la tarifa.
     * @return La {@link Tarifa} correspondiente, o {@code null} si no se encuentra.
     */
    public Tarifa buscarTarifa(TipoVehiculo tipoVehiculo){
        for(Tarifa tarifa : listTarifas){
            if(tarifa.getTipoVehiculo().equals(tipoVehiculo)){
                return tarifa;
            }
        }
        return null;
    }

    /**
     * Obtiene una copia de la lista de todas las tarifas.
     * @return Una {@link ArrayList} con todas las tarifas.
     */
    public ArrayList<Tarifa> obtenerTarifas(){
        return new ArrayList<>(listTarifas);
    }
}