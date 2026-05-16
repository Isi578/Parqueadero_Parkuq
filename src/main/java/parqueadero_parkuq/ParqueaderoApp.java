package parqueadero_parkuq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parqueadero_parkuq.dataUtil.Principal;

import java.io.IOException;

public class ParqueaderoApp extends Application {

    public static Stage mainStage;
    public static Scene sceneLogin;
    public static Scene sceneOperador;
    public static Scene sceneAdministrador;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;

        Principal.getInstance();

        java.net.URL loginUrl = ParqueaderoApp.class.getResource("login.fxml");
        java.net.URL operaUrl = ParqueaderoApp.class.getResource("opera.fxml");
        java.net.URL adminUrl = ParqueaderoApp.class.getResource("admin.fxml");


        sceneLogin = new Scene(FXMLLoader.load(loginUrl));
        sceneOperador = new Scene(FXMLLoader.load(operaUrl));
        sceneAdministrador = new Scene(FXMLLoader.load(adminUrl));

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
