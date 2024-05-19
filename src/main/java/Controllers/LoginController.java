package Controllers;


import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.*;
import java.io.*;
import java.util.*;
import com.n2.hotelaria.*;
import DAO.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController extends PadraoController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button loginButton, cadastroButton, esqueciMinhaSenhaButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private ButtonType enviarEmailButtonType, mudarCenaButtonType, voltarButtonType;

    private int tentativasFalhadas;
    private LoginDAO loginDao = new LoginDAO();

    @FXML
    private void initialize() {
        mascaraEmail(emailField);
        mascaraSenha(senhaField);
    }

    @FXML
    private void handleLogin() {
        if (loginDao.login(emailField.getText(), criptografar(senhaField.getText()))) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Login bem-sucedido!");
            tentativasFalhadas = 0;
        } else {
            tentativasFalhadas++;
            if (tentativasFalhadas >= 3) {
                int duracao = 5;
                showAlert(AlertType.WARNING, "Tentativas em excesso", "Você excedeu o número máximo de tentativas de login. Por favor, espere " + duracao + " segundos antes de tentar novamente");
                exibirPopupTemporizado( duracao,
                                        new TextField[]{emailField, senhaField}, 
                                        loginButton, cadastroButton, esqueciMinhaSenhaButton);
                duracao *= 2;
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Email ou senha incorretos!");
            }
        }
    }

    @FXML
    private void handleCadastro(ActionEvent event) {
        try {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App.changeScene("Cadastro", stage);
            
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }

    @FXML
    private void handleRecuperarSenha() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Esqueci minha senha");
        dialog.setHeaderText("Escolha uma opção:");

        enviarEmailButtonType = new ButtonType("Enviar email");
        mudarCenaButtonType = new ButtonType("Pergunta de segurança");
        voltarButtonType = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(enviarEmailButtonType, mudarCenaButtonType, voltarButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        result.ifPresent(buttonType -> {
            try {
                if (buttonType == enviarEmailButtonType) 
                    App.changeScene("EnvioEmail");
                else if (buttonType == mudarCenaButtonType)
                    App.changeScene("PerguntaSeguranca");
                 else if (buttonType == voltarButtonType) 
                    dialog.close();
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
            }
        });
    }
}