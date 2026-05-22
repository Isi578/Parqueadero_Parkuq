package parqueadero_parkuq.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import parqueadero_parkuq.ParqueaderoApp;

public class OperaViewController {

    @FXML
    private TabPane tabPane;

    @FXML
    void cerrarSesion(ActionEvent event) {
        if (ParqueaderoApp.mainStage != null && ParqueaderoApp.sceneLogin != null) {
            ParqueaderoApp.mainStage.setScene(ParqueaderoApp.sceneLogin);
        }
    }

    @FXML
    void initialize() {
    }

}
