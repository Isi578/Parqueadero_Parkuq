package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import parqueadero_parkuq.ParqueaderoApp;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista principal del administrador (admin.fxml).
 * Gestiona la navegación entre las diferentes pestañas de administración.
 */
public class AdminViewController implements Initializable {

    @FXML
    private TabPane tabPane;

    /**
     * Maneja el evento de clic en el botón "Cerrar Sesión".
     * Vuelve a la pantalla de inicio de sesión.
     *
     * @param event El evento de acción que disparó el método.
     */
    @FXML
    void cerrarSesion(ActionEvent event) {
        if (ParqueaderoApp.mainStage != null && ParqueaderoApp.sceneLogin != null) {
            ParqueaderoApp.goToLogin();
        }
    }

    /**
     * Método de inicialización del controlador.
     *
     * @param location  La ubicación utilizada para resolver rutas relativas.
     * @param resources Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Aquí se podría añadir lógica de inicialización si fuera necesario,
        // como por ejemplo, cargar datos específicos al abrir el panel de admin.
    }
}