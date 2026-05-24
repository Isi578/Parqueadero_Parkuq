package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Espacio;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoEspacio;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista que muestra los espacios disponibles en el parqueadero.
 */
public class OperaEspacioDisponibleViewController implements Initializable {

    @FXML
    private TableView<Espacio> tableEspacio;
    @FXML
    private TableColumn<Espacio, String> tcCodigo;
    @FXML
    private TableColumn<Espacio, String> tcTipoEspacio;
    @FXML
    private TableColumn<Espacio, String> tcEstado;

    private Parqueadero parqueadero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        // Configurar las columnas
        tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCodigo())));
        tcTipoEspacio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoEspacio().toString()));
        tcEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerEstado()));

        // Conectar la tabla a la lista de espacios
        tableEspacio.setItems(parqueadero.getListEspacios());
    }
}