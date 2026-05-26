package parqueadero_parkuq.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de usuarios autorizados (adminUsuarioAutorizado.fxml).
 * Permite a los administradores realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los usuarios.
 */
public class AdminUsuarioAutorizadoViewController implements Initializable {

    private Parqueadero parqueadero;
    private Usuario usuarioSeleccionado;

    @FXML
    private TableView<Usuario> tableUsuario;
    @FXML
    private TableColumn<Usuario, String> tcUsuario;
    @FXML
    private TableColumn<Usuario, String> tcNombre;
    @FXML
    private TableColumn<Usuario, String> tcIdentificacion;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtIdUsuario;
    @FXML
    private ComboBox<TipoUsuario> comboBoxUsuarios;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla, los listeners y carga los datos iniciales.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        this.tcUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        this.tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreusuario"));
        this.tcIdentificacion.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));

        tableUsuario.setItems(FXCollections.observableArrayList(parqueadero.getListUsuarios()));
        comboBoxUsuarios.getItems().addAll(TipoUsuario.values());

        tableUsuario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            usuarioSeleccionado = newValue;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });

        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    /**
     * Maneja el evento de clic en el botón "Registrar".
     * Crea y agrega un nuevo usuario si los datos son válidos y el ID no existe.
     */
    @FXML
    void onRegistrar() {
        Usuario nuevoUsuario = crearUsuarioDesdeFormulario();

        if (datosValidos(nuevoUsuario)) {
            boolean existe = false;
            for (Usuario u : parqueadero.getListUsuarios()) {
                if (u.getIdUsuario().equals(nuevoUsuario.getIdUsuario())) {
                    existe = true;
                    break;
                }
            }

            if (existe) {
                mostrarAlerta("Error al Registrar", "Ya existe un usuario con esa identificación.", Alert.AlertType.ERROR);
            } else {
                parqueadero.getListUsuarios().add(nuevoUsuario);
                tableUsuario.setItems(FXCollections.observableArrayList(parqueadero.getListUsuarios()));
                mostrarAlerta("Registro Exitoso", "El usuario ha sido registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }
        }
    }

    /**
     * Maneja el evento de clic en el botón "Actualizar".
     * Modifica el usuario seleccionado con los nuevos datos del formulario.
     */
    @FXML
    void onActualizar() {
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error de Actualización", "No ha seleccionado ningún usuario para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        Usuario datosNuevos = crearUsuarioDesdeFormulario();

        if (datosValidos(datosNuevos)) {
            usuarioSeleccionado.setNombreusuario(datosNuevos.getNombreusuario());
            usuarioSeleccionado.setTipoUsuario(datosNuevos.getTipoUsuario());

            tableUsuario.refresh();
            mostrarAlerta("Actualización Exitosa", "Los datos del usuario han sido actualizados.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Maneja el evento de clic en el botón "Eliminar".
     * Elimina el usuario seleccionado de la lista después de una confirmación.
     */
    @FXML
    void onEliminar() {
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error al Eliminar", "No ha seleccionado ningún usuario para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Optional<ButtonType> resultado = mostrarAlertaConfirmacion(
                "¿Está seguro de que desea eliminar al usuario " + usuarioSeleccionado.getNombreusuario() + "?");

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            parqueadero.getListUsuarios().remove(usuarioSeleccionado);
            tableUsuario.setItems(FXCollections.observableArrayList(parqueadero.getListUsuarios()));
            mostrarAlerta("Eliminación Exitosa", "El usuario ha sido eliminado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Limpia todos los campos del formulario y restablece el estado de los botones.
     */
    private void limpiarCampos() {
        tableUsuario.getSelectionModel().clearSelection();
        usuarioSeleccionado = null;
        txtNombreUsuario.clear();
        txtIdUsuario.clear();
        comboBoxUsuarios.setValue(null);
        txtIdUsuario.setEditable(true);
    }

    /**
     * Valida que los campos del formulario no estén vacíos.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
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
     * Crea un objeto Usuario a partir de los datos ingresados en el formulario.
     * @return Una nueva instancia de {@link Usuario}.
     */
    private Usuario crearUsuarioDesdeFormulario() {
        String nombre = txtNombreUsuario.getText();
        String id = txtIdUsuario.getText();
        TipoUsuario tipo = comboBoxUsuarios.getValue();

        return new Usuario(nombre, id, tipo);
    }

    /**
     * Muestra la información de un usuario seleccionado en los campos del formulario.
     * @param usuario El usuario seleccionado.
     */
    private void mostrarInformacionUsuario(Usuario usuario) {
        if (usuario != null) {
            txtNombreUsuario.setText(usuario.getNombreusuario());
            txtIdUsuario.setText(usuario.getIdUsuario());
            comboBoxUsuarios.setValue(usuario.getTipoUsuario());
            txtIdUsuario.setEditable(false);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
        } else {
            txtNombreUsuario.clear();
            txtIdUsuario.clear();
            comboBoxUsuarios.setValue(null);
            txtIdUsuario.setEditable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    /**
     * Muestra una ventana de alerta con un título, contenido y tipo de alerta específicos.
     * @param titulo El título de la ventana de alerta.
     * @param contenido El mensaje principal que se mostrará en la alerta.
     * @param alertType El tipo de alerta (ej. ERROR, WARNING, INFORMATION).
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
     * @param contenido El mensaje de confirmación.
     * @return Un {@link Optional} que contiene el tipo de botón presionado.
     */
    private Optional<ButtonType> mostrarAlertaConfirmacion(String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Eliminación");
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        return alert.showAndWait();
    }
}