module com.mycompany.carracesimulator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.carracesimulator to javafx.fxml;
    exports com.mycompany.carracesimulator;
}
