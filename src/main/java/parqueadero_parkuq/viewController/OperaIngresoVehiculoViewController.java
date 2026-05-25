package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class OperaIngresoVehiculoViewController implements Initializable {

    @FXML
    private TextField txtPlaca;
    @FXML
    private TextField txtNombreConductor;
    @FXML
    private ComboBox<TipoVehiculo> comboBoxTIpoVehiuclo;
    @FXML
    private TextField txtHoraIngreso;
    @FXML
    private TextField txtIdConductor;
    @FXML
    private Label txtEspacioAsignado;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TableView<Vehiculo> tableIngreso;
    @FXML
    private TableColumn<Vehiculo, String> tcPlaca;
    @FXML
    private TableColumn<Vehiculo, TipoVehiculo> tcTipoVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcHoraIngreso;
    @FXML
    private TableColumn<Vehiculo, String> tcNombreConductor;
    @FXML
    private TableColumn<Vehiculo, String> tcIdConductor;
    @FXML
    private TableColumn<Vehiculo, Integer> tcEspacioAsignado;

    private Parqueadero parqueadero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        tcHoraIngreso.setCellValueFactory(new PropertyValueFactory<>("horaIngreso"));
        tcNombreConductor.setCellValueFactory(new PropertyValueFactory<>("nombreConductor"));
        tcIdConductor.setCellValueFactory(new PropertyValueFactory<>("idConductor"));
        tcEspacioAsignado.setCellValueFactory(new PropertyValueFactory<>("espacioAsignado"));

        tableIngreso.setItems(parqueadero.getListVehiculos());
        comboBoxTIpoVehiuclo.getItems().addAll(TipoVehiculo.values());
        txtHoraIngreso.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        txtHoraIngreso.setEditable(false);
    }

    @FXML
    void onRegistrar(ActionEvent actionEvent) {
        if (datosValidos()) {
            String placa = txtPlaca.getText().toUpperCase(); // Convertir a mayúsculas para consistencia

            // --- MEJORA: Verificar si el vehículo ya está dentro ---
            boolean yaEstaDentro = parqueadero.getListVehiculos().stream()
                    .anyMatch(v -> v.getPlaca().equalsIgnoreCase(placa) && v.getEstado());

            if (yaEstaDentro) {
                mostrarAlerta("Error", "Este vehículo ya se encuentra dentro del parqueadero.", Alert.AlertType.ERROR);
                return;
            }

            TipoVehiculo tipoVehiculo = comboBoxTIpoVehiuclo.getValue();
            TipoEspacio tipoEspacioCompatible = getTipoEspacioCompatible(tipoVehiculo);

            Optional<Espacio> espacioDisponible = parqueadero.getListEspacios().stream()
                    .filter(e -> e.getTipoEspacio() == tipoEspacioCompatible && e.isEstado())
                    .findFirst();

            if (espacioDisponible.isPresent()) {
                Espacio espacio = espacioDisponible.get();
                Vehiculo nuevoVehiculo = crearVehiculoDesdeFormulario(espacio.getCodigo());

                espacio.setEstado(false);
                espacio.setVehiculoAsignado(nuevoVehiculo.getPlaca());
                parqueadero.getListVehiculos().add(nuevoVehiculo);

                txtEspacioAsignado.setText("Espacio asignado: " + espacio.getCodigo());
                mostrarAlerta("Éxito", "Vehículo registrado en el espacio " + espacio.getCodigo(), Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No hay espacios disponibles para este tipo de vehículo.", Alert.AlertType.ERROR);
            }
        }
    }

    private boolean datosValidos() {
        if (txtPlaca.getText().isEmpty() || txtNombreConductor.getText().isEmpty() ||
                txtIdConductor.getText().isEmpty() || comboBoxTIpoVehiuclo.getValue() == null) {
            mostrarAlerta("Datos Incompletos", "Por favor complete todos los campos.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private Vehiculo crearVehiculoDesdeFormulario(int codigoEspacio) {
        return new Vehiculo(
                txtPlaca.getText().toUpperCase(), // Guardar en mayúsculas
                comboBoxTIpoVehiuclo.getValue(),
                txtNombreConductor.getText(),
                txtIdConductor.getText(),
                txtHoraIngreso.getText(),
                codigoEspacio,
                true
        );
    }

    private TipoEspacio getTipoEspacioCompatible(TipoVehiculo tipoVehiculo) {
        switch (tipoVehiculo) {
            case CARRO:
                return TipoEspacio.CARRO;
            case MOTOCICLETA:
                return TipoEspacio.MOTOCICLETA;
            case BICICLETA:
                return TipoEspacio.BICICLETA;
            default:
                throw new IllegalStateException("Tipo de vehículo no soportado");
        }
    }

    private void limpiarCampos() {
        txtPlaca.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
        comboBoxTIpoVehiuclo.setValue(null);
        txtHoraIngreso.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        txtEspacioAsignado.setText("Espacio asignado:");
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}