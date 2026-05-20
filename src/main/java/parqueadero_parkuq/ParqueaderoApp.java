package parqueadero_parkuq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parqueadero_parkuq.dataUtil.Principal;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ParqueaderoApp extends Application {

    public static Stage mainStage;
    public static Scene sceneLogin;
    public static Scene sceneOperador;
    public static Scene sceneAdministrador;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;

        Principal.getInstance();

        URL loginUrl = ParqueaderoApp.class.getResource("/parqueadero_parkuq/login.fxml");
        URL operaUrl = ParqueaderoApp.class.getResource("/parqueadero_parkuq/opera.fxml");
        URL adminUrl = ParqueaderoApp.class.getResource("/parqueadero_parkuq/admin.fxml");


        sceneLogin = new Scene(FXMLLoader.load(Objects.requireNonNull(loginUrl)));
        sceneOperador = new Scene(FXMLLoader.load(Objects.requireNonNull(operaUrl)));
        sceneAdministrador = new Scene(FXMLLoader.load(Objects.requireNonNull(adminUrl)));

        goToLogin();
        mainStage.show();
    }

    public static void goToLogin() {
        mainStage.setScene(sceneLogin);
        mainStage.setTitle("Iniciar sesión");
    }

    public static void goToOperador() {
        mainStage.setScene(sceneOperador);
        mainStage.setTitle("Panel de Operador");
    }

    public static void goToAdministrador() {
        mainStage.setScene(sceneAdministrador);
        mainStage.setTitle("Panel de Administrador");
    }
}
