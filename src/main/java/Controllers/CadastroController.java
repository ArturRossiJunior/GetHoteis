package Controllers;

import java.io.IOException;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CadastroController extends PadraoController<UsuarioModel> {

    @FXML
    private TextField cpfField, nomeCompletoField, dataNascimentoField, emailField, respostaField;
    @FXML
    private PasswordField senhaField, confirmaSenhaField;
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
            if (validacaoCadastro(cadastroDao, cpfField.getText(), nomeCompletoField.getText(), dataNascimentoField.getText(), emailField.getText(), senhaField.getText(), confirmaSenhaField.getText())) {
                if (cadastroDao.inserirUsuario(new UsuarioModel(cpfField.getText().replaceAll("[.\\-]", ""), nomeCompletoField.getText(), dataNascimentoField.getText(), 
                        emailField.getText(), criptografar(senhaField.getText()), perguntaSegurancaCombo.getValue(), criptografar(respostaField.getText())))) {
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
    private void handleVoltar(ActionEvent event) {
        try {
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            App.changeScene("Login", stage);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }
}
