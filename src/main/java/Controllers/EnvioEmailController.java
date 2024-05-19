package Controllers;

import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class EnvioEmailController extends PadraoController {

    @FXML
    private TextField emailField;

    private final EnvioEmailDAO dao = new EnvioEmailDAO();

    @FXML
    private void initialize() {
        mascaraEmail(emailField);
    }

    @FXML
    void handleEnviar() {
        try {
            if(validacaoEnvioEmail(emailField.getText(), dao)) {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Email para alteração de senha enviado com sucesso");
                App.changeScene("Login");
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.changeScene("Login");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }
}
