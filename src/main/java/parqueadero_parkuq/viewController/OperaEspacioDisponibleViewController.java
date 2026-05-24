package parqueadero_parkuq.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.TipoEspacio;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista que muestra los espacios disponibles en el parqueadero.
 * (operaEspacioDisponible.fxml).
 */
public class OperaEspacioDisponibleViewController implements Initializable {

    @FXML
    private TableView<Espacio> tableUsuario; // Recomendación: Cambiar fx:id a tableEspacio

    @FXML
    private TableColumn<Espacio, TipoEspacio> tcPlaca; // Recomendación: Cambiar fx:id a tcTipoEspacio

    @FXML
    private TableColumn<Espacio, String> tcNombreConductor; // Recomendación: Cambiar fx:id a tcEstado

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para mostrar los espacios.
        // Por ejemplo, configurar las columnas y cargar los datos de los espacios.
    }
}