package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.TipoEspacio;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de gestión de espacios del parqueadero (adminEspacioParqueadero.fxml).
 */
public class AdminEspacioParqueaderoViewController implements Initializable {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtEstado;

    @FXML
    private ComboBox<TipoEspacio> comboBoxTipoEspacio;

    @FXML
    private TextField txtVehiculoAsignado;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnDeshabilitar;

    @FXML
    private TableView<Espacio> tableUsuario; // Recomendación: Cambiar fx:id a tableEspacio

    @FXML
    private TableColumn<Espacio, Integer> tcCodigo;

    @FXML
    private TableColumn<Espacio, TipoEspacio> tcTipoEspacio;

    @FXML
    private TableColumn<Espacio, String> tcEstado;

    @FXML
    private TableColumn<Espacio, String> tcVehiculoAsignado;

    /**
     * Maneja el evento de clic en el botón "Registrar".
     *
     * @param actionEvent El evento de acción.
     */
    public void onRegistrar(ActionEvent actionEvent) {
        // Lógica para registrar un nuevo espacio
    }

    /**
     * Maneja el evento de clic en el botón "Modificar".
     *
     * @param actionEvent El evento de acción.
     */
    public void onModificar(ActionEvent actionEvent) {
        // Lógica para modificar un espacio existente
    }

    /**
     * Maneja el evento de clic en el botón "Deshabilitar".
     *
     * @param actionEvent El evento de acción.
     */
    public void onDeshabilitar(ActionEvent actionEvent) {
        // Lógica para deshabilitar un espacio
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para la gestión de espacios.
        // Por ejemplo, poblar el ComboBox y configurar la tabla.
    }
}