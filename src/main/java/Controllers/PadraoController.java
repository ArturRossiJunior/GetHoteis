package Controllers;

import Models.PadraoModel;
import javafx.scene.control.Alert;

public class PadraoController <T extends PadraoModel>{

    protected void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}