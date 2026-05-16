module unknown.parqueadero_parkuq {
    requires javafx.controls;
    requires javafx.fxml;


    opens parqueadero_parkuq to javafx.fxml;
    exports parqueadero_parkuq;
}