package parqueadero_parkuq.viewController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de reportes del operario (operaReporte.fxml).
 */
public class OperaReporteViewController implements Initializable {

    @FXML
    private Label txFecha;

    @FXML
    private TextField txtFecha;

    @FXML
    private TableView<?> tableReporte; // El tipo de dato se debe ajustar al modelo de reporte

    @FXML
    private TableColumn<?, ?> tcPlactcTotalVehiculosIngresadosa;

    @FXML
    private TableColumn<?, ?> tcIngresosgenerados;

    @FXML
    private TableColumn<?, ?> tcTiempoPromedio;

    @FXML
    private TableColumn<?, ?> tcVehiculoMayorPermanencia;

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización para la vista de reportes.
        // Por ejemplo, cargar el reporte del día actual por defecto.
    }
}