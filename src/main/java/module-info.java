module parqueadero_parkuq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens parqueadero_parkuq to javafx.fxml;
    exports parqueadero_parkuq;
    opens parqueadero_parkuq.viewController to javafx.fxml;
}