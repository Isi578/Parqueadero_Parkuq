package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoEspacio;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de espacios del parqueadero (adminEspacioParqueadero.fxml).
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
    private TableColumn<Espacio, Integer> tcCodigo;
    @FXML
    private TableColumn<Espacio, TipoEspacio> tcTipoEspacio;
    @FXML
    private TableColumn<Espacio, String> tcEstado;
    @FXML
    private TableColumn<Espacio, String> tcVehiculoAsignado;

    private Parqueadero parqueadero;
    private Espacio espacioSeleccionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        this.tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        this.tcTipoEspacio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoEspacio()()));
        this.tcVehiculoAsignado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehiculoAsignado()));
        this.tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().verificarEspacioOcupado()));

        tableEspacio.setItems(parqueadero.getListEspacios());
        comboBoxTipoEspacio.getItems().addAll(TipoEspacio.values());

        tableEspacio.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            espacioSeleccionado = newSelection;
            mostrarInformacionEspacio(espacioSeleccionado);
        });

        btnModificar.setDisable(true);
        btnDeshabilitar.setDisable(true);
        btnRegistrar.setDisable(true);
    }

    @FXML
    void onRegistrar(ActionEvent actionEvent) {
        mostrarAlerta("Información", "La creación de nuevos espacios se realiza desde la configuración inicial del sistema.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void onModificar(ActionEvent actionEvent) {
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

    @FXML
    void onDeshabilitar(ActionEvent actionEvent) {
        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio para deshabilitarlo.", Alert.AlertType.WARNING);
            return;
        }
        Optional<ButtonType> resultado = mostrarAlertaConfirmacion("Confirmar Acción",
                "¿Está seguro de que desea cambiar el estado de este espacio?");
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            espacioSeleccionado.setEstado(!espacioSeleccionado.isEstado());
            tableEspacio.refresh();
            mostrarAlerta("Éxito", "El estado del espacio ha sido actualizado.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    private void mostrarInformacionEspacio(Espacio espacio) {
        if (espacio != null) {
            txtCodigo.setText(String.valueOf(espacio.getCodigo()));
            txtEstado.setText(espacio.obtenerEstado());
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