package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import com.n2.hotelaria.App;

import DAO.*;

public class LoginController extends PadraoController {

    @FXML
    private Button cadastroButton;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField senhaField;

    private LoginDAO loginDao;

    public LoginController() {
        loginDao = new LoginDAO();
    }


    @FXML
    private void handleLogin() {
        String usuario = usuarioField.getText();
        String senha = senhaField.getText();

        if (loginDao.login(usuario, senha)) {
            showAlert("Login bem-sucedido!");
        } else {
            showAlert("Nome de usuário ou senha incorretos!");
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
}