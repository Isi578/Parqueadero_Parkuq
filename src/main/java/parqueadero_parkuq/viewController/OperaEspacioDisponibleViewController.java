package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoEspacio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de espacios disponibles (operaEspacioDisponible.fxml).
 * Permite a los operarios ver, filtrar y asignar espacios a los vehículos.
 */
public class OperaEspacioDisponibleViewController implements Initializable {

    @FXML
    private TableView<Espacio> tableEspacio;
    @FXML
    private TableColumn<Espacio, String> tcCodigo;
    @FXML
    private TableColumn<Espacio, String> tcTipoEspacio;
    @FXML
    private TableColumn<Espacio, String> tcEstado;
    @FXML
    private ComboBox<TipoEspacio> comboBoxFiltro;
    @FXML
    private TextField txtPlaca;
    @FXML
    private Button btnAsignar;

    private Parqueadero parqueadero;
    private Espacio espacioSeleccionado;

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
        tcEstado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Espacio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Espacio, String> param) {
                return new SimpleStringProperty(param.getValue().isEstado() ? "Ocupado" : "Disponible");
            }
        });


        if (comboBoxFiltro != null) {
            comboBoxFiltro.getItems().addAll(TipoEspacio.values());
            comboBoxFiltro.getItems().add(null);
        }

        tableEspacio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Espacio>() {
            @Override
            public void changed(ObservableValue<? extends Espacio> observable, Espacio oldValue, Espacio newValue) {
                espacioSeleccionado = newValue;
                if (btnAsignar != null) {
                    btnAsignar.setDisable(newValue == null);
                }
            }
        });

        filtrarTabla();

        if (btnAsignar != null) {
            btnAsignar.setDisable(true);
        }
    }

    /**
     * Maneja el evento de selección en el ComboBox de filtro.
     * Actualiza la tabla para mostrar solo los espacios del tipo seleccionado.
     */
    @FXML
    private void onFiltroSeleccionado() {
        filtrarTabla();
    }

    /**
     * Filtra y actualiza la tabla de espacios según el tipo seleccionado en el ComboBox.
     */
    private void filtrarTabla() {
        TipoEspacio tipoSeleccionado = null;
        if (comboBoxFiltro != null) {
            tipoSeleccionado = comboBoxFiltro.getValue();
        }

        List<Espacio> todosLosEspacios = parqueadero.getListEspacios();
        List<Espacio> espaciosFiltrados = new ArrayList<>();

        for (Espacio espacio : todosLosEspacios) {
            if (!espacio.isEstado()) {
                if (tipoSeleccionado == null || espacio.getTipoEspacio() == tipoSeleccionado) {
                    espaciosFiltrados.add(espacio);
                }
            }
        }

        ObservableList<Espacio> listaObservable = FXCollections.observableArrayList(espaciosFiltrados);
        tableEspacio.setItems(listaObservable);
    }

    /**
     * Maneja el evento de clic en el botón "Asignar".
     * Asigna el vehículo con la placa ingresada al espacio seleccionado en la tabla.
     */
    @FXML
    private void onAsignar() {
        if (txtPlaca == null || btnAsignar == null) {
            mostrarAlerta("Error de Interfaz", "Los componentes para asignar vehículos no están cargados en el FXML.", Alert.AlertType.ERROR);
            return;
        }

        String placa = txtPlaca.getText();
        if (placa == null || placa.trim().isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una placa.", Alert.AlertType.WARNING);
            return;
        }

        if (espacioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un espacio.", Alert.AlertType.WARNING);
            return;
        }

        if (parqueadero.buscarVehiculo(placa) == null) {
            mostrarAlerta("Error", "El vehículo con la placa '" + placa + "' no está registrado.", Alert.AlertType.ERROR);
            return;
        }

        boolean asignado = parqueadero.asignarEspacio(placa, espacioSeleccionado.getCodigo());

        if (asignado) {
            mostrarAlerta("Éxito", "El vehículo con placa '" + placa + "' ha sido asignado al espacio " + espacioSeleccionado.getCodigo(), Alert.AlertType.INFORMATION);
            filtrarTabla();
            txtPlaca.clear();
        } else {
            mostrarAlerta("Error", "No se pudo asignar el espacio. Verifique que el vehículo y el espacio sean compatibles y que el espacio esté realmente libre.", Alert.AlertType.ERROR);
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
}