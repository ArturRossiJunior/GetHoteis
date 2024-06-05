package Controllers;

import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import Models.PadraoModel;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;

public class EnvioEmailController extends PadraoController<PadraoModel> {

    @FXML
    private TextField emailField;

    private final EnvioEmailDAO dao = new EnvioEmailDAO();

    @FXML
    private void initialize() {
        mascaraEmail(emailField);
    }

    @FXML
    void handleEnviar(ActionEvent event) {
        try {
            if(validacaoEnvioEmail(emailField.getText(), dao)) {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Email para alteração de senha enviado com sucesso");
                App.changeScene("Login", (Stage)((Node)event.getSource()).getScene().getWindow());
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            App.changeScene("Login", (Stage)((Node)event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }
}
