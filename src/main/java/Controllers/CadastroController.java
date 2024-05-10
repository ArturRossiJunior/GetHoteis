package Controllers;

import java.io.IOException;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class CadastroController extends PadraoController<CadastroModel> {

    @FXML
    private TextField cpfField, nomeCompletoField, dataNascimentoField, emailField, respostaField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cadastrarButton, voltarButton;

    private final CadastroDAO cadastroDao = new CadastroDAO();

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
    private void handleCadastro() {
        try {
            if (validacaoCadastro(cadastroDao, cpfField.getText(), nomeCompletoField.getText(), dataNascimentoField.getText(), emailField.getText(), senhaField.getText())) {
                if (cadastroDao.inserirUsuario(new CadastroModel(cpfField.getText().replaceAll("[.\\-]", ""), nomeCompletoField.getText(), dataNascimentoField.getText(), 
                        emailField.getText(), criptogafar(senhaField.getText()), perguntaSegurancaCombo.getValue(), criptogafar(respostaField.getText())))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro bem-sucedido");
                    App.changeScene("Login");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao cadastrar");
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
