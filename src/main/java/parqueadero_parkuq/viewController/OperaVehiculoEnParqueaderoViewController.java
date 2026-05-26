package parqueadero_parkuq.viewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.TipoVehiculo;
import parqueadero_parkuq.model.Vehiculo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista que muestra los vehículos actualmente en el parqueadero.
 */
public class OperaVehiculoEnParqueaderoViewController implements Initializable {

    @FXML
    private TableView<Vehiculo> tableVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcPlaca;
    @FXML
    private TableColumn<Vehiculo, String> tcNombreConductor;
    @FXML
    private TableColumn<Vehiculo, TipoVehiculo> tcTipoVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> tcHoraIngreso;
    @FXML
    private TableColumn<Vehiculo, Integer> tcEspacio;

    private Parqueadero parqueadero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();

        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tcNombreConductor.setCellValueFactory(new PropertyValueFactory<>("nombreConductor"));
        tcTipoVehiculo.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        tcHoraIngreso.setCellValueFactory(new PropertyValueFactory<>("horaIngreso"));
        tcEspacio.setCellValueFactory(new PropertyValueFactory<>("espacioAsignado"));

        cargarVehiculosActivos();
    }

    /**
     * Filtra la lista de todos los vehículos para obtener solo los que están
     * actualmente dentro del parqueadero y los muestra en la tabla.
     */
    private void cargarVehiculosActivos() {
        List<Vehiculo> todosLosVehiculos = parqueadero.getListVehiculos();
        List<Vehiculo> vehiculosActivos = new ArrayList<>();

        for (Vehiculo vehiculo : todosLosVehiculos) {
            if (vehiculo.getEstado()) {
                vehiculosActivos.add(vehiculo);
            }
        }

        ObservableList<Vehiculo> listaObservable = FXCollections.observableArrayList(vehiculosActivos);
        tableVehiculo.setItems(listaObservable);
    }
}