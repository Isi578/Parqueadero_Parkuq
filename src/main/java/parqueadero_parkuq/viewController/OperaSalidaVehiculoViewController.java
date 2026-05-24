package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;

import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;

public class OperaSalidaVehiculoViewController implements Initializable {

    @FXML
    private TextField txtPlaca;
    @FXML
    private Button btnSalida1;
    @FXML
    private Button btnBuscar1;
    @FXML
    private TableView<String> tableSalida; // Tabla para mostrar el detalle de la factura
    @FXML
    private TableColumn<String, String> tcPlaca; // Columna para la descripción
    @FXML
    private TableColumn<String, String> tcNombreConductor; // Columna para el valor

    private Parqueadero parqueadero;
    private Vehiculo vehiculoEncontrado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        btnSalida1.setDisable(true);
    }

    @FXML
    void onBuscar(ActionEvent actionEvent) {
        String placa = txtPlaca.getText();
        if (placa.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una placa para buscar.", Alert.AlertType.WARNING);
            return;
        }

        Optional<Vehiculo> vehiculoOpt = parqueadero.getListVehiculos().stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa) && v.getEstado())
                .findFirst();

        if (vehiculoOpt.isPresent()) {
            vehiculoEncontrado = vehiculoOpt.get();
            // Lógica de cálculo de tarifa (simplificada)
            long horas = ChronoUnit.HOURS.between(LocalTime.parse(vehiculoEncontrado.getHoraIngreso()), LocalTime.now());
            if (horas == 0) horas = 1; // Cobrar mínimo una hora

            // Suponemos que existe una tarifa para el tipo de vehículo
            double valorHora = 2000; // Valor por defecto
            Optional<Tarifa> tarifaOpt = parqueadero.getListTarifas().stream()
                    .filter(t -> t.getTipoVehiculo() == vehiculoEncontrado.getTipoVehiculo()).findFirst();
            if(tarifaOpt.isPresent()) valorHora = tarifaOpt.get().getValorHora();

            double totalPagar = horas * valorHora;

            // Mostrar factura en la tabla
            tableSalida.getItems().clear();
            tableSalida.getItems().add("Placa: " + vehiculoEncontrado.getPlaca());
            tableSalida.getItems().add("Tiempo: " + horas + " horas");
            tableSalida.getItems().add("Total a pagar: $" + totalPagar);

            btnSalida1.setDisable(false);
        } else {
            mostrarAlerta("No Encontrado", "No se encontró ningún vehículo activo con esa placa.", Alert.AlertType.ERROR);
            vehiculoEncontrado = null;
            btnSalida1.setDisable(true);
        }
    }

    @FXML
    void onSalida(ActionEvent actionEvent) {
        if (vehiculoEncontrado != null) {
            // Liberar espacio
            Optional<Espacio> espacioOpt = parqueadero.getListEspacios().stream()
                    .filter(e -> e.getCodigo() == vehiculoEncontrado.getEspacioAsignado()).findFirst();
            if (espacioOpt.isPresent()) {
                Espacio espacio = espacioOpt.get();
                espacio.setEstado(true);
                espacio.setVehiculoAsignado(null);
            }

            // Marcar vehículo como fuera
            vehiculoEncontrado.setEstado(false);

            mostrarAlerta("Éxito", "Salida del vehículo registrada correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtPlaca.clear();
        tableSalida.getItems().clear();
        vehiculoEncontrado = null;
        btnSalida1.setDisable(true);
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}