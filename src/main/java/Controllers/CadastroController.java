package Controllers;

import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CadastroController extends PadraoController<CadastroModel> {

    @FXML
    private TextField nomeCompletoField, emailField, cpfField, dataNascimentoField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cadastrarButton, voltarButton;

    private final CadastroDAO cadastroDao = new CadastroDAO();

    @FXML
    private void initialize() {
        mascaraCPF(cpfField);
        mascaraData(dataNascimentoField);
        mascaraNome(nomeCompletoField);
        mascaraEmail(emailField);
        mascaraSenha(senhaField);
    }

    @FXML
    private void handleCadastro() {
        try {
            if (validacaoCadastro(cadastroDao, nomeCompletoField.getText(), emailField.getText(), cpfField.getText(), dataNascimentoField.getText(), senhaField.getText())) {
                if (cadastroDao.inserirUsuario(new CadastroModel(nomeCompletoField.getText(), emailField.getText(), cpfField.getText(), dataNascimentoField.getText(), criptogafarSenha(senhaField.getText())))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro bem-sucedido!");
                    App.changeScene("Login");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Usuário ou senha inválidos");
                }
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