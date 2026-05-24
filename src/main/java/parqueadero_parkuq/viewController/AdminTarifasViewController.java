package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import parqueadero_parkuq.model.Tarifa;
import parqueadero_parkuq.model.TipoVehiculo;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de tarifas (adminTarifas.fxml).
 */
public class AdminTarifasViewController implements Initializable {

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtValorHora;

    @FXML
    private ComboBox<TipoVehiculo> comboBoxTipoVehiculo;

    @FXML
    private Button btnCalcular;

    @FXML
    private TableView<Tarifa> tableTarifa;

    @FXML
    private TableColumn<Tarifa, String> tcTipoVehiculo;

    @FXML
    private TableColumn<Tarifa, Double> tcValorHora;

    @FXML
    private TableColumn<Tarifa, Double> tcDescuento;

    @FXML
    private TableColumn<Tarifa, Double> tcTotal;

    /**
     * Maneja el evento de clic en el botón "Calcular".
     *
     * @param actionEvent El evento de acción.
     */
    public void onCalcular(ActionEvent actionEvent) {
        // Lógica para calcular la tarifa
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para la gestión de tarifas.
        // Por ejemplo, poblar el ComboBox y configurar la tabla.
    }
}