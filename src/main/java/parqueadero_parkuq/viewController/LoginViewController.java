package parqueadero_parkuq.viewController;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import parqueadero_parkuq.ParqueaderoApp;
import parqueadero_parkuq.dataUtil.Datautil;
import parqueadero_parkuq.dataUtil.Principal;
import parqueadero_parkuq.model.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    private Parqueadero parqueadero;

    @FXML
    private Button loginButton;
    @FXML
    private Label txtAdvertencia;
    @FXML
    private PasswordField txtPasswordLogin;
    @FXML
    private ComboBox<String> comboBoxUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.parqueadero = Principal.getInstance().getParqueadero();
        comboBoxUser.getItems().addAll(Datautil.OPERARIO, Datautil.ADMINISTRADOR);
        txtAdvertencia.setText("");
    }

    @FXML
    void login(ActionEvent event) {
        String user = comboBoxUser.getSelectionModel().getSelectedItem();
        String pass = txtPasswordLogin.getText();

        if (user == null || user.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
            mostrarAlerta("Error", "Los campos de usuario y contraseña no pueden estar vacíos.", Alert.AlertType.WARNING);
            return;
        }

        if (user.equals(Datautil.OPERARIO) && pass.equals(Datautil.OPERA_CONTRASENA)) {
            ParqueaderoApp.goToOperador();
        } else if (user.equals(Datautil.ADMINISTRADOR) && pass.equals(Datautil.ADMIN_CONTRASENA)) {
            ParqueaderoApp.goToAdministrador();
        } else {
            mostrarAlerta("Error de Autenticación", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
            txtAdvertencia.setText("Credenciales incorrectas");
        }
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}