package parqueadero_parkuq.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de registro de salida de vehículos (operaSalidaVehiculo.fxml).
 * Permite buscar un vehículo por su placa, calcular el costo y registrar su salida.
 */
public class OperaSalidaVehiculoViewController implements Initializable {

    @FXML
    private TextField txtPlaca;
    @FXML
    private Button btnSalida1;
    @FXML
    private Button btnBuscar1;
    @FXML
    private TableView<Factura> tableSalida;
    @FXML
    private TableColumn<Factura, String> tcPlaca;
    @FXML
    private TableColumn<Factura, String> tcNombreConductor;

    private Parqueadero parqueadero;
    private Vehiculo vehiculoEncontrado;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla de factura y deshabilita el botón de salida.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        btnSalida1.setDisable(true);
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tcNombreConductor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    /**
     * Maneja el evento de clic en el botón "Buscar".
     * Busca un vehículo activo por la placa ingresada, calcula el costo y muestra la factura.
     */
    @FXML
    void onBuscar() {
        String placa = txtPlaca.getText();
        if (placa.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una placa para buscar.", Alert.AlertType.WARNING);
            return;
        }

        vehiculoEncontrado = null;
        for (Vehiculo v : parqueadero.getListVehiculos()) {
            if (v.getPlaca().equalsIgnoreCase(placa) && v.getEstado()) {
                vehiculoEncontrado = v;
                break;
            }
        }

        if (vehiculoEncontrado != null) {
            long horas = ChronoUnit.HOURS.between(LocalTime.parse(vehiculoEncontrado.getHoraIngreso()), LocalTime.now());
            if (horas == 0) {
                horas = 1;
            }

            double valorHora = 0;
            for (Tarifa t : parqueadero.getListTarifas()) {
                if (t.getTipoVehiculo() == vehiculoEncontrado.getTipoVehiculo()) {
                    valorHora = t.getValorHora();
                    break;
                }
            }

            double totalPagar = horas * valorHora;

            List<Factura> facturaItems = new ArrayList<>();
            facturaItems.add(new Factura("Placa:", vehiculoEncontrado.getPlaca()));
            facturaItems.add(new Factura("Tiempo (horas):", String.valueOf(horas)));
            facturaItems.add(new Factura("Total a pagar:", "$" + totalPagar));

            tableSalida.setItems(FXCollections.observableArrayList(facturaItems));
            btnSalida1.setDisable(false);
        } else {
            mostrarAlerta("No Encontrado", "No se encontró ningún vehículo activo con esa placa.", Alert.AlertType.ERROR);
            limpiarCampos();
        }
    }

    /**
     * Maneja el evento de clic en el botón "Salida".
     * Registra la salida del vehículo encontrado y limpia la vista.
     */
    @FXML
    void onSalida() {
        if (vehiculoEncontrado != null) {
            parqueadero.registrarSalida(vehiculoEncontrado.getPlaca());
            mostrarAlerta("Éxito", "Salida del vehículo registrada correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Limpia todos los campos del formulario y restablece el estado de los botones.
     */
    private void limpiarCampos() {
        txtPlaca.clear();
        tableSalida.getItems().clear();
        vehiculoEncontrado = null;
        btnSalida1.setDisable(true);
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