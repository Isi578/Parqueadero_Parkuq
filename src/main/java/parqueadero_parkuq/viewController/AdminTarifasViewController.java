package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleDoubleProperty;
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
 * Controlador para la vista de gestión de tarifas (adminTarifas.fxml).
 * Permite a los administradores crear, actualizar y eliminar las tarifas del parqueadero.
 */
public class AdminTarifasViewController implements Initializable {

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipoUsuario;
    @FXML
    private TextField txtValorHora;
    @FXML
    private ComboBox<TipoVehiculo> comboBoxTipoVehiculo;
    @FXML
    private Button btnAnadir;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Tarifa> tableTarifa;
    @FXML
    private TableColumn<Tarifa, TipoVehiculo> tcTipoVehiculo;
    @FXML
    private TableColumn<Tarifa, Double> tcValorHora;
    @FXML
    private TableColumn<Tarifa, Double> tcDescuento;
    @FXML
    private TableColumn<Tarifa, Double> tcTotal;

    private Parqueadero parqueadero;
    private Tarifa tarifaSeleccionada;

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

        this.tcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        this.tcValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));
        this.tcDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));

        this.tcTotal.setCellValueFactory(param -> {
            Tarifa tarifa = param.getValue();
            double total = tarifa.getValorHora() * (1 - tarifa.getDescuento());
            return new SimpleDoubleProperty(total).asObject();
        });

        this.comboBoxTipoVehiculo.getItems().addAll(TipoVehiculo.values());
        this.comboBoxTipoUsuario.getItems().addAll(TipoUsuario.values());
        this.tableTarifa.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));

        tableTarifa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tarifaSeleccionada = newValue;
            mostrarInformacionTarifa(tarifaSeleccionada);
        });

        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    /**
     * Maneja el evento de clic en el botón "Añadir".
     * Crea y agrega una nueva tarifa si los datos son válidos y no existe una tarifa para ese tipo de vehículo.
     */
    @FXML
    void onAnadir() {
        try {
            if (datosValidos()) {
                Tarifa nuevaTarifa = crearTarifaDesdeFormulario();
                boolean existe = false;
                for (Tarifa t : parqueadero.getListTarifas()) {
                    if (t.getTipoVehiculo() == nuevaTarifa.getTipoVehiculo()) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    mostrarAlerta("Error", "Ya existe una tarifa para este tipo de vehículo.", Alert.AlertType.ERROR);
                    return;
                }
                parqueadero.getListTarifas().add(nuevaTarifa);
                tableTarifa.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El valor por hora debe ser un número válido.", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de clic en el botón "Actualizar".
     * Modifica la tarifa seleccionada con los nuevos datos del formulario.
     */
    @FXML
    void onActualizar() {
        if (tarifaSeleccionada != null && datosValidos()) {
            try {
                double nuevoValor = Double.parseDouble(txtValorHora.getText());
                Usuario tempUser = new Usuario("", "", comboBoxTipoUsuario.getValue());
                double nuevoDescuento = tempUser.obtenerDescuento();

                tarifaSeleccionada.setValorHora(nuevoValor);
                tarifaSeleccionada.setDescuento(nuevoDescuento);
                tableTarifa.refresh();
                mostrarAlerta("Éxito", "La tarifa ha sido actualizada.", Alert.AlertType.INFORMATION);
                limpiarCampos();
            } catch (NumberFormatException e) {
                mostrarAlerta("Error de Formato", "El valor por hora debe ser un número válido.", Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Maneja el evento de clic en el botón "Eliminar".
     * Elimina la tarifa seleccionada de la lista después de una confirmación.
     */
    @FXML
    void onEliminar() {
        if (tarifaSeleccionada != null) {
            Optional<ButtonType> resultado = mostrarAlertaConfirmacion(
                    "¿Está seguro de que desea eliminar la tarifa para " + tarifaSeleccionada.getTipoVehiculo() + "?");
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                parqueadero.getListTarifas().remove(tarifaSeleccionada);
                tableTarifa.setItems(FXCollections.observableArrayList(parqueadero.getListTarifas()));
                limpiarCampos();
            }
        }
    }

    /**
     * Valida que los campos del formulario no estén vacíos.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean datosValidos() {
        if (comboBoxTipoVehiculo.getValue() == null || comboBoxTipoUsuario.getValue() == null || txtValorHora.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    /**
     * Crea un objeto Tarifa a partir de los datos ingresados en el formulario.
     * @return Una nueva instancia de {@link Tarifa}.
     * @throws NumberFormatException si el valor por hora no es un número válido.
     */
    private Tarifa crearTarifaDesdeFormulario() throws NumberFormatException {
        TipoVehiculo tipoVehiculo = comboBoxTipoVehiculo.getValue();
        TipoUsuario tipoUsuario = comboBoxTipoUsuario.getValue();
        double valorHora = Double.parseDouble(txtValorHora.getText());
        Usuario usuarioTemporal = new Usuario("", "", tipoUsuario);
        double descuento = usuarioTemporal.obtenerDescuento();
        return new Tarifa(tipoVehiculo, valorHora, descuento);
    }

    /**
     * Muestra la información de una tarifa seleccionada en los campos del formulario.
     * @param tarifa La tarifa seleccionada.
     */
    private void mostrarInformacionTarifa(Tarifa tarifa) {
        if (tarifa != null) {
            txtValorHora.setText(String.valueOf(tarifa.getValorHora()));
            comboBoxTipoVehiculo.setValue(tarifa.getTipoVehiculo());
            comboBoxTipoUsuario.setValue(null);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            comboBoxTipoVehiculo.setDisable(true);
        } else {
            limpiarCampos();
        }
    }

    /**
     * Limpia todos los campos del formulario y restablece el estado de los botones.
     */
    private void limpiarCampos() {
        tableTarifa.getSelectionModel().clearSelection();
        txtValorHora.clear();
        comboBoxTipoVehiculo.setValue(null);
        comboBoxTipoUsuario.setValue(null);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        comboBoxTipoVehiculo.setDisable(false);
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