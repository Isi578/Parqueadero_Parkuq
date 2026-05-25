package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminTarifasViewController implements Initializable {

    @FXML
    private ComboBox<TipoUsuario> comboBoxTipousuario;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        this.tcTipoVehiculo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoVehiculo()));
        this.tcValorHora.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValorHora()).asObject());
        this.tcDescuento.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getDescuento()).asObject());
        this.tcTotal.setCellValueFactory(cellData -> {
            Tarifa tarifa = cellData.getValue();
            double total = tarifa.getValorHora() * (1 - tarifa.getDescuento());
            return new SimpleDoubleProperty(total).asObject();
        });

        this.comboBoxTipoVehiculo.getItems().addAll(TipoVehiculo.values());
        this.comboBoxTipousuario.getItems().addAll(TipoUsuario.values());
        this.tableTarifa.setItems(parqueadero.getListTarifas());

        tableTarifa.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            tarifaSeleccionada = newSelection;
            mostrarInformacionTarifa(tarifaSeleccionada);
        });

        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    @FXML
    void onAnadir(ActionEvent actionEvent) {
        try {
            if (datosValidos()) {
                Tarifa nuevaTarifa = crearTarifaDesdeFormulario();
                boolean existe = parqueadero.getListTarifas().stream()
                        .anyMatch(t -> t.getTipoVehiculo() == nuevaTarifa.getTipoVehiculo());
                if (existe) {
                    mostrarAlerta("Error", "Ya existe una tarifa para este tipo de vehículo.", Alert.AlertType.ERROR);
                    return;
                }
                parqueadero.getListTarifas().add(nuevaTarifa);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El valor por hora debe ser un número válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onActualizar(ActionEvent actionEvent) {
        if (tarifaSeleccionada != null && datosValidos()) {
            try {
                double nuevoValor = Double.parseDouble(txtValorHora.getText());
                Usuario tempUser = new Usuario("", "", comboBoxTipousuario.getValue());
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

    @FXML
    void onEliminar(ActionEvent actionEvent) {
        if (tarifaSeleccionada != null) {
            Optional<ButtonType> resultado = mostrarAlertaConfirmacion("Confirmar Eliminación",
                    "¿Está seguro de que desea eliminar la tarifa para " + tarifaSeleccionada.getTipoVehiculo() + "?");
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                parqueadero.getListTarifas().remove(tarifaSeleccionada);
                limpiarCampos();
            }
        }
    }

    private boolean datosValidos() {
        if (comboBoxTipoVehiculo.getValue() == null || comboBoxTipousuario.getValue() == null || txtValorHora.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private Tarifa crearTarifaDesdeFormulario() throws NumberFormatException {
        TipoVehiculo tipoVehiculo = comboBoxTipoVehiculo.getValue();
        TipoUsuario tipoUsuario = comboBoxTipousuario.getValue();
        double valorHora = Double.parseDouble(txtValorHora.getText());
        Usuario usuarioTemporal = new Usuario("", "", tipoUsuario);
        double descuento = usuarioTemporal.obtenerDescuento();
        return new Tarifa(tipoVehiculo, valorHora);
    }

    private void mostrarInformacionTarifa(Tarifa tarifa) {
        if (tarifa != null) {
            txtValorHora.setText(String.valueOf(tarifa.getValorHora()));
            comboBoxTipoVehiculo.setValue(tarifa.getTipoVehiculo());
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            comboBoxTipoVehiculo.setDisable(true);
        } else {
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        tableTarifa.getSelectionModel().clearSelection();
        txtValorHora.clear();
        comboBoxTipoVehiculo.setValue(null);
        comboBoxTipousuario.setValue(null);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        comboBoxTipoVehiculo.setDisable(false);
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