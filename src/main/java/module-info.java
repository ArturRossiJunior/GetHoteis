module com.mycompany.hotelaria {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.hotelaria to javafx.fxml;
    exports com.mycompany.hotelaria;
}
