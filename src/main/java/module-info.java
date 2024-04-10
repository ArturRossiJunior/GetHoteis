module com.mycompany.hotelaria {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.hotelaria to javafx.fxml;
    exports com.mycompany.hotelaria;
}
