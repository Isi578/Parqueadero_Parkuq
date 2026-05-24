package parqueadero_parkuq.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import parqueadero_parkuq.model.Vehiculo;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista que muestra los vehículos actualmente en el parqueadero.
 * (operaVehiculoEnParqueadero.fxml).
 */
public class OperaVehiculoEnParqueadero implements Initializable {

    @FXML
    private TableView<Vehiculo> tableUsuario; // Recomendación: Cambiar fx:id a tableVehiculo en el FXML

    @FXML
    private TableColumn<Vehiculo, String> tcPlaca;

    @FXML
    private TableColumn<Vehiculo, String> tcNombreConductor;

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para mostrar los vehículos.
        // Por ejemplo, configurar las columnas y cargar los datos de los vehículos
        // que están actualmente en el parqueadero.
    }
}