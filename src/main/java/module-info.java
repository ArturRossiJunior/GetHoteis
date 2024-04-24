module com.n2.hotelaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.n2.hotelaria to javafx.fxml;
    exports com.n2.hotelaria;
}
