package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoUsuario;
import parqueadero_parkuq.model.Usuario;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de usuarios autorizados (adminUsuarioAutorizado.fxml).
 * Permite a los administradores realizar operaciones CRUD sobre los usuarios.
 */
public class AdminUsuarioAutorizadoViewController implements Initializable {

    @FXML
    private TableView<Usuario> tablaUsuario;

    @FXML
    private TableColumn<Usuario, String> columnaNombre;

    @FXML
    private TableColumn<Usuario, String> columnaId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;

    @FXML
    private Button botonActualizar;

    @FXML
    private Button botonEliminar;

    private Parqueadero parqueadero;
    private Usuario usuarioSeleccionado;

    /**
     * Maneja el evento de clic en el botón "Registrar".
     * Crea un nuevo usuario, valida sus datos, verifica que no exista y lo añade al sistema.
     *
     * @param event El evento de acción que disparó el método.
     */
    @FXML
    void onRegistrar(ActionEvent event) {
        Usuario nuevoUsuario = crearUsuarioDesdeFormulario();

        if (datosValidos(nuevoUsuario)) {
            // Verificar si el usuario ya existe
            boolean existe = parqueadero.getListUsuarios().stream()
                    .anyMatch(u -> u.getIdUsuario().equals(nuevoUsuario.getIdUsuario()));

            if (existe) {
                mostrarAlerta("Error al Registrar", "Ya existe un usuario con esa identificación.", Alert.AlertType.ERROR);
            } else {
                parqueadero.getListUsuarios().add(nuevoUsuario);
                tablaUsuario.setItems(parqueadero.Usuario()); // Refrescar la tabla
                mostrarAlerta("Registro Exitoso", "El usuario ha sido registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }
        }
    }

    /**
     * Maneja el evento de clic en el botón "Actualizar".
     * Actualiza los datos del usuario seleccionado en la tabla con la información del formulario.
     *
     * @param event El evento de acción que disparó el método.
     */
    @FXML
    void onActualizar(ActionEvent event) {
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error de Actualización", "No ha seleccionado ningún usuario para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        Usuario datosNuevos = crearUsuarioDesdeFormulario();

        if (datosValidos(datosNuevos)) {
            usuarioSeleccionado.setNombreusuario(datosNuevos.getNombreusuario());
            usuarioSeleccionado.setTipoUsuario(datosNuevos.getTipoUsuario());

            tablaUsuario.refresh();
            mostrarAlerta("Actualización Exitosa", "Los datos del usuario han sido actualizados.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Maneja el evento de clic en el botón "Eliminar".
     * Pide confirmación y, si se acepta, elimina el usuario seleccionado del sistema.
     *
     * @param event El evento de acción que disparó el método.
     */
    @FXML
    void onEliminar(ActionEvent event) {
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error al Eliminar", "No ha seleccionado ningún usuario para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Optional<ButtonType> resultado = mostrarAlertaConfirmacion("Confirmar Eliminación",
                "¿Está seguro de que desea eliminar al usuario " + usuarioSeleccionado.getNombreusuario() + "?");

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            parqueadero.getListUsuarios().remove(usuarioSeleccionado);
            tablaUsuario.setItems(parqueadero.Usuario());
            mostrarAlerta("Eliminación Exitosa", "El usuario ha sido eliminado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Método de inicialización del controlador. Se llama automáticamente después de que se carga el FXML.
     * Configura la tabla, carga los datos iniciales y establece los listeners.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si no se conoce.
     * @param resources Los recursos utilizados para localizar el objeto raíz, o null si no se conoce.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreusuario"));
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));

        tablaUsuario.setItems(parqueadero.Usuario());

        comboBoxTipoUsuario.getItems().addAll(TipoUsuario.values());

        tablaUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });

        botonActualizar.setDisable(true);
        botonEliminar.setDisable(true);
    }

    /**
     * Limpia los campos del formulario y deselecciona cualquier fila en la tabla.
     * Restablece la interfaz a su estado inicial.
     */
    private void limpiarCampos() {
        tablaUsuario.getSelectionModel().clearSelection();
        usuarioSeleccionado = null;
        txtNombre.clear();
        txtId.clear();
        comboBoxTipoUsuario.setValue(null);
        txtId.setEditable(true);
    }

    /**
     * Valida que los datos del usuario en el formulario no estén incompletos.
     *
     * @param usuario El objeto Usuario a validar.
     * @return true si los datos son válidos, false en caso contrario.
     */
    private boolean datosValidos(Usuario usuario) {
        if (usuario.getNombreusuario() == null || usuario.getNombreusuario().isEmpty() ||
                usuario.getIdUsuario() == null || usuario.getIdUsuario().isEmpty() ||
                usuario.getTipoUsuario() == null) {
            mostrarAlerta("Datos Incompletos", "Por favor complete todos los campos (Nombre, ID, Tipo de Usuario).", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Crea un nuevo objeto Usuario a partir de los datos introducidos en el formulario.
     *
     * @return Un nuevo objeto Usuario.
     */
    private Usuario crearUsuarioDesdeFormulario() {
        String nombre = txtNombre.getText();
        String id = txtId.getText();
        TipoUsuario tipo = comboBoxTipoUsuario.getValue();

        return new Usuario(nombre, id, tipo);
    }

    /**
     * Muestra la información de un usuario en los campos del formulario.
     *
     * @param usuario El usuario cuyos datos se van a mostrar. Si es null, limpia los campos.
     */
    private void mostrarInformacionUsuario(Usuario usuario) {
        if (usuario != null) {
            txtNombre.setText(usuario.getNombreusuario());
            txtId.setText(usuario.getIdUsuario());
            comboBoxTipoUsuario.setValue(usuario.getTipoUsuario());
            txtId.setEditable(false);
            botonEliminar.setDisable(false);
        } else {
            txtNombre.clear();
            txtId.clear();
            comboBoxTipoUsuario.setValue(null);
            txtId.setEditable(true);
            botonActualizar.setDisable(true);
            botonEliminar.setDisable(true);
        }
    }

    /**
     * Muestra una ventana de alerta al usuario.
     *
     * @param titulo    El título de la ventana de alerta.
     * @param contenido El mensaje a mostrar en la alerta.
     * @param alertType El tipo de alerta (ERROR, INFORMATION, WARNING, etc.).
     */
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Muestra una ventana de confirmación y espera la respuesta del usuario.
     *
     * @param titulo    El título de la ventana de confirmación.
     * @param contenido El mensaje de pregunta para el usuario.
     * @return Un Optional que contiene el tipo de botón presionado (OK o CANCEL).
     */
    private Optional<ButtonType> mostrarAlertaConfirmacion(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        return alert.showAndWait();
    }
}