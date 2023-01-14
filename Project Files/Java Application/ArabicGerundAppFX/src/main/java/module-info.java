module com.massadirapplication.massadirappfx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.xml.bind;

    opens com.massadirapplication.massadirappfx to javafx.fxml,jakarta.xml.bind;
    exports com.massadirapplication.massadirappfx;
    exports com.massadirapplication.massadirappfx.nooj;
    opens com.massadirapplication.massadirappfx.nooj to javafx.fxml,jakarta.xml.bind;
}
