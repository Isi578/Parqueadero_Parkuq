package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de registro de salida de vehículos (operaSalidaVehiculo.fxml).
 */
public class OperaSalidaVehiculoViewController implements Initializable {

    @FXML
    private TextField txtPlaca;

    @FXML
    private Button btnSalida1;

    @FXML
    private Button btnBuscar1;

    @FXML
    private TableView<?> tableSalida; // Ajustar el tipo de dato al modelo de salida

    @FXML
    private TableColumn<?, ?> tcPlaca;

    @FXML
    private TableColumn<?, ?> tcNombreConductor;

    @FXML
    private TableColumn<?, ?> tcIdConductor;

    @FXML
    private TableColumn<?, ?> tcEspacioAsignado;

    /**
     * Maneja el evento de clic en el botón "Salida".
     *
     * @param actionEvent El evento de acción.
     */
    public void onSalida(ActionEvent actionEvent) {
        // Lógica para registrar la salida de un vehículo
    }

    /**
     * Maneja el evento de clic en el botón "Buscar".
     *
     * @param actionEvent El evento de acción.
     */
    public void onBuscar(ActionEvent actionEvent) {
        // Lógica para buscar un vehículo por su placa
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para la vista de salida de vehículos.
    }
}