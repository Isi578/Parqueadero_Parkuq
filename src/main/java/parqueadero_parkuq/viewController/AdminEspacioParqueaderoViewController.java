package parqueadero_parkuq.viewController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoEspacio;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de espacios del parqueadero (adminEspacioParqueadero.fxml).
 * Permite a los administradores modificar el tipo de espacio y habilitar/deshabilitar espacios.
 */
public class AdminEspacioParqueaderoViewController implements Initializable {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtEstado;
    @FXML
    private ComboBox<TipoEspacio> comboBoxTipoEspacio;
    @FXML
    private TextField txtVehiculoAsignado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnDeshabilitar;
    @FXML
    private TableView<Espacio> tableEspacio;
    @FXML
    private TableColumn<Espacio, String> tcCodigo;
    @FXML
    private TableColumn<Espacio, String> tcTipoEspacio;
    @FXML
    private TableColumn<Espacio, String> tcEstado;
    @FXML
    private TableColumn<Espacio, String> tcVehiculoAsignado;

    private Espacio espacioSeleccionado;
    private Parqueadero parqueadero;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla, los listeners y carga los datos iniciales.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parqueadero = Principal.getInstance().getParqueadero();

        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcTipoEspacio.setCellValueFactory(new PropertyValueFactory<>("tipoEspacio"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tcVehiculoAsignado.setCellValueFactory(new PropertyValueFactory<>("vehiculoAsignado"));

        tableEspacio.setItems(FXCollections.observableArrayList(parqueadero.getListEspacios()));
        comboBoxTipoEspacio.getItems().addAll(TipoEspacio.values());

        tableEspacio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Espacio>() {
            @Override
            public void changed(ObservableValue<? extends Espacio> observable, Espacio oldValue, Espacio newValue) {
                espacioSeleccionado = newValue;
                mostrarInformacionEspacio(espacioSeleccionado);
            }
        });

        btnModificar.setDisable(true);
        btnDeshabilitar.setDisable(true);
        btnRegistrar.setDisable(true);
    }

    /**
     * Maneja el evento de clic en el botón "Registrar".
     * Informa al usuario que la creación de espacios no se realiza desde esta interfaz.
     */
    @FXML
    void onRegistrar() {
        mostrarAlerta("Información", "La creación de nuevos espacios se realiza desde la configuración inicial del sistema.", Alert.AlertType.INFORMATION);
    }

    /**
     * Maneja el evento de clic en el botón "Modificar".
     * Cambia el tipo del espacio seleccionado.
     */
    @FXML
    void onModificar() {
        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio para modificarlo.", Alert.AlertType.WARNING);
            return;
        }
        TipoEspacio nuevoTipo = comboBoxTipoEspacio.getValue();
        if (nuevoTipo == null) {
            mostrarAlerta("Error", "Debe seleccionar un nuevo tipo para el espacio.", Alert.AlertType.WARNING);
            return;
        }
        espacioSeleccionado.setTipoEspacio(nuevoTipo);
        tableEspacio.refresh();
        mostrarAlerta("Éxito", "El tipo de espacio ha sido modificado.", Alert.AlertType.INFORMATION);
        limpiarCampos();
    }

    /**
     * Maneja el evento de clic en el botón "Deshabilitar".
     * Cambia el estado (ocupado/disponible) del espacio seleccionado.
     */
    @FXML
    void onDeshabilitar() {
        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio para deshabilitarlo.", Alert.AlertType.WARNING);
            return;
        }
        Optional<ButtonType> resultado = mostrarAlertaConfirmacion();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            espacioSeleccionado.setEstado(!espacioSeleccionado.isEstado());
            tableEspacio.refresh();
            mostrarAlerta("Éxito", "El estado del espacio ha sido actualizado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Muestra la información de un espacio seleccionado en los campos del formulario.
     * @param espacio El espacio seleccionado.
     */
    private void mostrarInformacionEspacio(Espacio espacio) {
        if (espacio != null) {
            txtCodigo.setText(String.valueOf(espacio.getCodigo()));
            txtEstado.setText(espacio.isEstado() ? "Ocupado" : "Disponible");
            comboBoxTipoEspacio.setValue(espacio.getTipoEspacio());
            txtVehiculoAsignado.setText(espacio.getVehiculoAsignado() != null ? espacio.getVehiculoAsignado() : "Ninguno");
            txtCodigo.setEditable(false);
            txtEstado.setEditable(false);
            txtVehiculoAsignado.setEditable(false);
            btnModificar.setDisable(false);
            btnDeshabilitar.setDisable(false);
        } else {
            limpiarCampos();
        }
    }

    /**
     * Limpia todos los campos del formulario y restablece el estado de los botones.
     */
    private void limpiarCampos() {
        tableEspacio.getSelectionModel().clearSelection();
        espacioSeleccionado = null;
        txtCodigo.clear();
        txtEstado.clear();
        comboBoxTipoEspacio.setValue(null);
        txtVehiculoAsignado.clear();
        btnModificar.setDisable(true);
        btnDeshabilitar.setDisable(true);
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
     * @return Un {@link Optional} que contiene el tipo de botón presionado.
     */
    private Optional<ButtonType> mostrarAlertaConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Acción");
        alert.setHeaderText(null);
        alert.setContentText("¿Está seguro de que desea cambiar el estado de este espacio?");
        return alert.showAndWait();
    }
}