package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import java.io.*;
import com.n2.hotelaria.*;
import javafx.scene.*;
import javafx.stage.*;

public class HomeAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TitledPane x1;

    @FXML
    private void openCadastroTipoQuarto(ActionEvent event) {
        try {
            App.changeScene("CadastroTipoQuarto", (Stage)((Node)event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}