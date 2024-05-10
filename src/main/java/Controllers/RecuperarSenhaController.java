package Controllers;

import javafx.fxml.*;
import javafx.scene.control.*;
import java.io.*;
import com.n2.hotelaria.*;
import DAO.*;
import Models.*;

public class RecuperarSenhaController extends PadraoController<PadraoModel> {

    @FXML
    private Button recuperarSenhaButton, voltarButton;
    @FXML
    private TextField emailField, respostaField;
    @FXML
    private PasswordField senhaNovaField, confirmarSenhaNovaField;

    private RecuperarSenhaDAO recuperarSenhaDAO = new RecuperarSenhaDAO();


    @FXML
    private void initialize() {
        mascaraEmail(emailField);
        mascaraSenha(senhaNovaField);
        mascaraSenha(confirmarSenhaNovaField);
        mascaraResposta(respostaField);
        perguntaSegurancaCombo();
    }

    @FXML
    private void handleRecuperarSenha() {
        try{
            if(validacaoRecuperarSenha(recuperarSenhaDAO, emailField.getText(), senhaNovaField.getText(), confirmarSenhaNovaField.getText(), perguntaSegurancaCombo.getValue(), respostaField.getText())){
                if (recuperarSenhaDAO.recuperarSenha(emailField.getText(), criptogafar(senhaNovaField.getText()), perguntaSegurancaCombo.getValue(), criptogafar(respostaField.getText()))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Senha alterada com sucesso");
                    App.changeScene("Login");
                } else{
                    showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao alterar a senha");
                }
            }
        } catch (IOException e){
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