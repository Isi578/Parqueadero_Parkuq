package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import parqueadero_parkuq.model.TipoVehiculo;
import parqueadero_parkuq.model.Vehiculo;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de registro de ingreso de vehículos (operaIngresoVehiculo.fxml).
 */
public class OperaIngresoVehiculoViewController implements Initializable {

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtNombreConductor;

    @FXML
    private ComboBox<TipoVehiculo> comboBoxTIpoVehiuclo; // Recomendación: Corregir errata en FXML

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

    /**
     * Maneja el evento de clic en el botón "Registrar".
     *
     * @param actionEvent El evento de acción.
     */
    public void onRegistrar(ActionEvent actionEvent) {
        // Lógica para registrar el ingreso de un vehículo
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para la vista de ingreso de vehículos.
        // Por ejemplo, poblar el ComboBox de tipos de vehículo.
    }
}