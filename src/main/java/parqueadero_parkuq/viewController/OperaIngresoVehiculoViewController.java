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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de registro de ingreso de vehículos (operaIngresoVehiculo.fxml).
 * Permite registrar la entrada de un nuevo vehículo, asignarle un espacio y mostrar los vehículos activos.
 */
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

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla y carga los datos iniciales.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        tcHoraIngreso.setCellValueFactory(new PropertyValueFactory<>("horaIngreso"));
        tcNombreConductor.setCellValueFactory(new PropertyValueFactory<>("nombreConductor"));
        tcIdConductor.setCellValueFactory(new PropertyValueFactory<>("idConductor"));
        tcEspacioAsignado.setCellValueFactory(new PropertyValueFactory<>("espacioAsignado"));

        tableIngreso.setItems(FXCollections.observableArrayList(parqueadero.obtenerVehiculosDentro()));
        comboBoxTIpoVehiuclo.getItems().addAll(TipoVehiculo.values());
        txtHoraIngreso.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        txtHoraIngreso.setEditable(false);
    }

    /**
     * Maneja el evento de clic en el botón "Registrar".
     * Valida los datos, busca un espacio disponible y registra el ingreso del vehículo.
     */
    @FXML
    void onRegistrar() {
        if (datosValidos()) {
            String placa = txtPlaca.getText().toUpperCase();

            for (Vehiculo v : parqueadero.getListVehiculos()) {
                if (v.getPlaca().equalsIgnoreCase(placa) && v.getEstado()) {
                    mostrarAlerta("Error", "Este vehículo ya se encuentra dentro del parqueadero.", Alert.AlertType.ERROR);
                    return;
                }
            }

            TipoVehiculo tipoVehiculo = comboBoxTIpoVehiuclo.getValue();
            TipoEspacio tipoEspacioCompatible = getTipoEspacioCompatible(tipoVehiculo);

            Espacio espacioDisponible = null;
            for (Espacio e : parqueadero.getListEspacios()) {
                if (e.getTipoEspacio() == tipoEspacioCompatible && !e.isEstado()) {
                    espacioDisponible = e;
                    break;
                }
            }

            if (espacioDisponible != null) {
                Vehiculo nuevoVehiculo = crearVehiculoDesdeFormulario(espacioDisponible.getCodigo());

                parqueadero.registrarVehiculo(nuevoVehiculo);
                parqueadero.asignarEspacio(nuevoVehiculo.getPlaca(), espacioDisponible.getCodigo());

                tableIngreso.setItems(FXCollections.observableArrayList(parqueadero.obtenerVehiculosDentro()));
                txtEspacioAsignado.setText("Espacio asignado: " + espacioDisponible.getCodigo());
                mostrarAlerta("Éxito", "Vehículo registrado en el espacio " + espacioDisponible.getCodigo(), Alert.AlertType.INFORMATION);
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No hay espacios disponibles para este tipo de vehículo.", Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Valida que los campos del formulario no estén vacíos.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean datosValidos() {
        if (txtPlaca.getText().isEmpty() || txtNombreConductor.getText().isEmpty() ||
                txtIdConductor.getText().isEmpty() || comboBoxTIpoVehiuclo.getValue() == null) {
            mostrarAlerta("Datos Incompletos", "Por favor complete todos los campos.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    /**
     * Crea un objeto Vehiculo a partir de los datos ingresados en el formulario.
     * @param codigoEspacio El código del espacio que se le asignará al vehículo.
     * @return Una nueva instancia de {@link Vehiculo}.
     */
    private Vehiculo crearVehiculoDesdeFormulario(int codigoEspacio) {
        return new Vehiculo(
                txtPlaca.getText().toUpperCase(),
                comboBoxTIpoVehiuclo.getValue(),
                txtNombreConductor.getText(),
                txtIdConductor.getText(),
                txtHoraIngreso.getText(),
                codigoEspacio,
                true
        );
    }

    /**
     * Obtiene el tipo de espacio compatible para un tipo de vehículo dado.
     * @param tipoVehiculo El tipo de vehículo.
     * @return El {@link TipoEspacio} compatible.
     * @throws IllegalStateException si el tipo de vehículo no es soportado.
     */
    private TipoEspacio getTipoEspacioCompatible(TipoVehiculo tipoVehiculo) {
        if (tipoVehiculo == TipoVehiculo.CARRO) {
            return TipoEspacio.CARRO;
        } else if (tipoVehiculo == TipoVehiculo.MOTOCICLETA) {
            return TipoEspacio.MOTOCICLETA;
        } else if (tipoVehiculo == TipoVehiculo.BICICLETA) {
            return TipoEspacio.BICICLETA;
        }
        throw new IllegalStateException("Tipo de vehículo no soportado");
    }

    /**
     * Limpia todos los campos del formulario y restablece la hora de ingreso.
     */
    private void limpiarCampos() {
        txtPlaca.clear();
        txtNombreConductor.clear();
        txtIdConductor.clear();
        comboBoxTIpoVehiuclo.setValue(null);
        txtHoraIngreso.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        txtEspacioAsignado.setText("Espacio asignado:");
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