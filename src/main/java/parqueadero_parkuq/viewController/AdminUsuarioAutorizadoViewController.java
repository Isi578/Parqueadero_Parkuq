package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    private Parqueadero parqueadero;
    private ObservableList<Usuario> listUsuarios;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        this.tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoUsuario().toString()));
        this.tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreusuario()));
        this.tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdUsuario()));

        tableUsuario.setItems(parqueadero.getListUsuarios());
        comboBoxUsuarios.getItems().addAll(TipoUsuario.values());

        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacionUsuario(usuarioSeleccionado);
        });

        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }
    @FXML
    void onRegistrar(ActionEvent event) {
        Usuario nuevoUsuario = crearUsuarioDesdeFormulario();

        if (datosValidos(nuevoUsuario)) {
            boolean existe = parqueadero.getListUsuarios().stream()
                    .anyMatch(u -> u.getIdUsuario().equals(nuevoUsuario.getIdUsuario()));

            if (existe) {
                mostrarAlerta("Error al Registrar", "Ya existe un usuario con esa identificación.", Alert.AlertType.ERROR);
            } else {
                parqueadero.getListUsuarios().add(nuevoUsuario);
                mostrarAlerta("Registro Exitoso", "El usuario ha sido registrado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            }
        }
    }

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

            tableUsuario.refresh();
            mostrarAlerta("Actualización Exitosa", "Los datos del usuario han sido actualizados.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

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
            mostrarAlerta("Eliminación Exitosa", "El usuario ha sido eliminado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        tableUsuario.getSelectionModel().clearSelection();
        usuarioSeleccionado = null;
        txtNombreUsuario.clear();
        txtIdUsuario.clear();
        comboBoxUsuarios.setValue(null);
        txtIdUsuario.setEditable(true);
    }

    private boolean datosValidos(Usuario usuario) {
        if (usuario.getNombreusuario() == null || usuario.getNombreusuario().isEmpty() ||
                usuario.getIdUsuario() == null || usuario.getIdUsuario().isEmpty() ||
                usuario.getTipoUsuario() == null) {
            mostrarAlerta("Datos Incompletos", "Por favor complete todos los campos (Nombre, ID, Tipo de Usuario).", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private Usuario crearUsuarioDesdeFormulario() {
        String nombre = txtNombreUsuario.getText();
        String id = txtIdUsuario.getText();
        TipoUsuario tipo = comboBoxUsuarios.getValue();

        return new Usuario(nombre, id, tipo);
    }

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

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private Optional<ButtonType> mostrarAlertaConfirmacion(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        return alert.showAndWait();
    }
}