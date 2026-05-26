package parqueadero_parkuq.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de espacios disponibles (operaEspacioDisponible.fxml).
 * Permite a los operarios ver y filtrar los espacios que no están ocupados.
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
    @FXML
    private ComboBox<TipoEspacio> comboBoxFiltro;

    private Parqueadero parqueadero;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla, el filtro y carga los datos iniciales.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parqueadero = Principal.getInstance().getParqueadero();

        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcTipoEspacio.setCellValueFactory(new PropertyValueFactory<>("tipoEspacio"));
        tcEstado.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().isEstado() ? "Ocupado" : "Disponible"));

        if (comboBoxFiltro != null) {
            comboBoxFiltro.getItems().addAll(TipoEspacio.values());
            comboBoxFiltro.getItems().add(null);
        }

        filtrarTabla();
    }

    /**
     * Maneja el evento de selección en el ComboBox de filtro.
     * Actualiza la tabla para mostrar solo los espacios del tipo seleccionado.
     */
    @FXML
    private void onFiltroSeleccionado() {
        filtrarTabla();
    }

    /**
     * Filtra y actualiza la tabla de espacios según el tipo seleccionado en el ComboBox.
     * Muestra únicamente los espacios que no están ocupados.
     */
    private void filtrarTabla() {
        TipoEspacio tipoSeleccionado = null;
        if (comboBoxFiltro != null) {
            tipoSeleccionado = comboBoxFiltro.getValue();
        }

        List<Espacio> todosLosEspacios = parqueadero.getListEspacios();
        List<Espacio> espaciosFiltrados = new ArrayList<>();

        for (Espacio espacio : todosLosEspacios) {
            if (!espacio.isEstado()) {
                if (tipoSeleccionado == null || espacio.getTipoEspacio() == tipoSeleccionado) {
                    espaciosFiltrados.add(espacio);
                }
            }
        }

        ObservableList<Espacio> listaObservable = FXCollections.observableArrayList(espaciosFiltrados);
        tableEspacio.setItems(listaObservable);
    }
}