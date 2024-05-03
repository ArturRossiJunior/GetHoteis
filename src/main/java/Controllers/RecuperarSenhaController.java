package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import java.io.IOException;
import com.n2.hotelaria.App;
import DAO.*;
import Models.PadraoModel;

public class RecuperarSenhaController extends PadraoController<PadraoModel> {

    @FXML
    private Button recuperarSenhaButton, voltarButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaAntigaField, senhaNovaField, confirmarSenhaNovaField;

    private RecuperarSenhaDAO recuperarSenhaDAO = new RecuperarSenhaDAO();

    @FXML
    private void initialize() {
        mascaraEmail(emailField);
        mascaraSenha(senhaAntigaField);
        mascaraSenha(senhaNovaField);
        mascaraSenha(confirmarSenhaNovaField);
    }

    @FXML
    private void handleRecuperarSenha() {
        try{
            if(validacaoRecuperarSenha(emailField.getText(), senhaAntigaField.getText(), senhaNovaField.getText(), confirmarSenhaNovaField.getText())){
                if (recuperarSenhaDAO.recuperarSenha(emailField.getText(), senhaAntigaField.getText(), senhaNovaField.getText())) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Senha alterada com sucesso.");
                    App.changeScene("Login");
                } else{
                    showAlert(Alert.AlertType.ERROR, "Erro", "Email ou senha antiga inválidos.");
                }
            }
        } catch (IOException e){
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena.");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.changeScene("Login");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena.");
        }
    }
}