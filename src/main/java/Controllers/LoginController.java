package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import com.n2.hotelaria.App;
import DAO.*;

public class LoginController extends PadraoController {

    @FXML
    private Button cadastroButton;
    @FXML
    private Button recuperarSenhaButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;

    private LoginDAO loginDao = new LoginDAO();

    @FXML
    private void initialize() {
        mascaraEmail(emailField);
        mascaraSenha(senhaField);
    }

    @FXML
    private void handleLogin() {
        if (loginDao.login(emailField.getText(), criptogafar(senhaField.getText()))) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Login bem-sucedido!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Email ou senha incorretos!");
        }
    }

    @FXML
    private void handleCadastro() {
        try {
            App.changeScene("Cadastro");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRecuperarSenha() {
        try {
            App.changeScene("RecuperarSenha");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}