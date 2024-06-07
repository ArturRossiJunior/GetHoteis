package Controllers;

import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.event.*;
import javafx.stage.*;

public class CadastroUsuarioController extends PadraoController<UsuarioModel> {

    @FXML
    private TextField cpfField, nomeCompletoField, dataNascimentoField, emailField, respostaField;
    @FXML
    private PasswordField senhaField, confirmaSenhaField;
    @FXML
    private Button cadastrarButton, voltarButton;

    private final UsuarioDAO cadastroDao = new UsuarioDAO();

    @FXML
    private void initialize() {
        mascaraCPF(cpfField);
        mascaraNome(nomeCompletoField);
        mascaraData(dataNascimentoField);
        mascaraEmail(emailField);
        mascaraSenha(senhaField);
        mascaraResposta(respostaField);
        perguntaSegurancaCombo();
    }

    @FXML
    private void handleCadastro(ActionEvent event) {
        try {
            if (validacaoCadastroUsuario(cadastroDao, cpfField.getText(), nomeCompletoField.getText(), dataNascimentoField.getText(), emailField.getText(), senhaField.getText(), confirmaSenhaField.getText())) {
                if (cadastroDao.inserirUsuario(new UsuarioModel(cpfField.getText().replaceAll("[.\\-]", ""), nomeCompletoField.getText(), inverterData(dataNascimentoField.getText()), 
                        emailField.getText(), criptografar(senhaField.getText()), perguntaSegurancaCombo.getValue(), criptografar(respostaField.getText())))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro bem-sucedido");
                    App.changeScene("Login", (Stage)((Node)event.getSource()).getScene().getWindow());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao cadastrar");
                }
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
