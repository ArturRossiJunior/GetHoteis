package Controllers;

import javafx.fxml.*;
import javafx.scene.control.*;
import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class PerguntaSegurancaController extends PadraoController<PadraoModel> {

    @FXML
    private Button recuperarSenhaButton, voltarButton;
    @FXML
    private TextField emailField, respostaField;
    @FXML
    private PasswordField senhaNovaField, confirmarSenhaNovaField;

    private PerguntaSegurancaDAO perguntaSegurancaDAO = new PerguntaSegurancaDAO();


    @FXML
    private void initialize() {
        mascaraEmail(emailField);
        mascaraSenha(senhaNovaField);
        mascaraSenha(confirmarSenhaNovaField);
        mascaraResposta(respostaField);
        perguntaSegurancaCombo();
    }

    @FXML
    private void handlePerguntaSeguranca(ActionEvent event) {
        try{
            if(validacaoPerguntaSeguranca(perguntaSegurancaDAO, emailField.getText(), senhaNovaField.getText(), confirmarSenhaNovaField.getText(), perguntaSegurancaCombo.getValue(), respostaField.getText())){
                if (perguntaSegurancaDAO.reuperarSenhaPerguntaSeguranca(emailField.getText(), criptografar(senhaNovaField.getText()), perguntaSegurancaCombo.getValue(), criptografar(respostaField.getText()))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Senha alterada com sucesso");
                    App.changeScene("Login", (Stage)((Node)event.getSource()).getScene().getWindow());
                } else{
                    showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao alterar a senha");
                }
            }
        } catch (IOException e){
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