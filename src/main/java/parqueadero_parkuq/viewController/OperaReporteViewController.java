package parqueadero_parkuq.viewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.Parqueadero;
import parqueadero_parkuq.model.ReporteDiario;
import parqueadero_parkuq.model.Vehiculo;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de reportes del operario (operaReporte.fxml).
 * Permite generar y visualizar un reporte diario con estadísticas del parqueadero.
 */
public class OperaReporteViewController implements Initializable {

    @FXML
    private TableView<ReporteDiario> tableReporte;
    @FXML
    private TableColumn<ReporteDiario, String> tcConcepto;
    @FXML
    private TableColumn<ReporteDiario, String> tcValor;

    private Parqueadero parqueadero;

    /**
     * Método de inicialización del controlador.
     * Configura las columnas de la tabla de reportes.
     *
     * @param location La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        tcConcepto.setCellValueFactory(new PropertyValueFactory<>("concepto"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    /**
     * Maneja el evento de clic en el botón "Generar Reporte".
     * Calcula las estadísticas del día y las muestra en la tabla.
     */
    @FXML
    private void onGenerarReporte() {
        List<ReporteDiario> reporte = new ArrayList<>();
        List<Vehiculo> vehiculosDelDia = obtenerVehiculosDelDia();

        int totalVehiculos = vehiculosDelDia.size();
        double ingresosTotales = calcularIngresos(vehiculosDelDia);
        long tiempoPromedio = calcularTiempoPromedio(vehiculosDelDia);
        String vehiculoMayorPermanencia = obtenerVehiculoMayorPermanencia(vehiculosDelDia);

        reporte.add(new ReporteDiario("Fecha del Reporte", LocalDate.now().toString()));
        reporte.add(new ReporteDiario("Total Vehículos Ingresados", String.valueOf(totalVehiculos)));
        reporte.add(new ReporteDiario("Ingresos Generados", "$" + ingresosTotales));
        reporte.add(new ReporteDiario("Tiempo Promedio (minutos)", String.valueOf(tiempoPromedio)));
        reporte.add(new ReporteDiario("Vehículo con Mayor Permanencia", vehiculoMayorPermanencia));

        tableReporte.setItems(FXCollections.observableArrayList(reporte));
    }

    /**
     * Obtiene una lista de los vehículos que han salido del parqueadero.
     * @return Una lista de vehículos.
     */
    private List<Vehiculo> obtenerVehiculosDelDia() {
        List<Vehiculo> vehiculosReporte = new ArrayList<>();
        for (Vehiculo v : parqueadero.getListVehiculos()) {
            if (!v.getEstado()) {
                vehiculosReporte.add(v);
            }
        }
        return vehiculosReporte;
    }

    /**
     * Calcula los ingresos totales generados por una lista de vehículos.
     * @param vehiculos La lista de vehículos.
     * @return El total de ingresos.
     */
    private double calcularIngresos(List<Vehiculo> vehiculos) {
        double ingresos = 0;
        for (Vehiculo v : vehiculos) {
            long horas = ChronoUnit.HOURS.between(LocalTime.parse(v.getHoraIngreso()), LocalTime.now());
            if (horas == 0) horas = 1;
            ingresos += horas * 2000;
        }
        return ingresos;
    }

    /**
     * Calcula el tiempo promedio de permanencia de una lista de vehículos.
     * @param vehiculos La lista de vehículos.
     * @return El tiempo promedio en minutos.
     */
    private long calcularTiempoPromedio(List<Vehiculo> vehiculos) {
        if (vehiculos.isEmpty()) {
            return 0;
        }
        long totalMinutos = 0;
        for (Vehiculo v : vehiculos) {
            totalMinutos += ChronoUnit.MINUTES.between(LocalTime.parse(v.getHoraIngreso()), LocalTime.now());
        }
        return totalMinutos / vehiculos.size();
    }

    /**
     * Encuentra el vehículo con el mayor tiempo de permanencia en una lista.
     * @param vehiculos La lista de vehículos.
     * @return Una cadena con la placa del vehículo y su tiempo de permanencia.
     */
    private String obtenerVehiculoMayorPermanencia(List<Vehiculo> vehiculos) {
        if (vehiculos.isEmpty()) {
            return "N/A";
        }
        Vehiculo mayorPermanencia = null;
        long maxMinutos = -1;

        for (Vehiculo v : vehiculos) {
            long minutos = ChronoUnit.MINUTES.between(LocalTime.parse(v.getHoraIngreso()), LocalTime.now());
            if (minutos > maxMinutos) {
                maxMinutos = minutos;
                mayorPermanencia = v;
            }
        }
        return mayorPermanencia.getPlaca() + " (" + maxMinutos + " min)";
    }

    /**
     * Muestra una ventana de alerta con un título, contenido y tipo de alerta específicos.
     * @param titulo El título de la ventana de alerta.
     * @param contenido El mensaje principal que se mostrará en la alerta.
     * @param alertType El tipo de alerta (ej. ERROR, WARNING, INFORMATION).
     */
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}