module unknown.parqueadero_parkuq {
    requires javafx.controls;
    requires javafx.fxml;


    opens unknown.parqueadero_parkuq to javafx.fxml;
    exports unknown.parqueadero_parkuq;
}