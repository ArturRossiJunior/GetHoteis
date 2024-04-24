module com.n2.hotelaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports Controllers;
    requires lombok;
    opens Controllers to javafx.fxml;

    opens com.n2.hotelaria to javafx.fxml;
    exports com.n2.hotelaria;
}
