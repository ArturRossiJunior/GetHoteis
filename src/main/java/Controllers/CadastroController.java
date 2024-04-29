package Controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.n2.hotelaria.App;
import DAO.CadastroDAO;
import Models.CadastroModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController extends PadraoController<CadastroModel> {

    @FXML
    private TextField nomeCompletoField, emailField, cpfField, dataNascimentoField;
    @FXML
    private PasswordField senhaField;
    @FXML
    private Button cadastrarButton, voltarButton;

    private final CadastroDAO cadastroDao = new CadastroDAO();

    @FXML
    private void handleCadastro() {
        try {
            if (validacaoCadastro(cadastroDao, emailField.getText(), cpfField.getText(), dataNascimentoField.getText())) {
                if (cadastroDao.inserirUsuario(new CadastroModel(nomeCompletoField.getText(), emailField.getText(), cpfField.getText(), dataNascimentoField.getText(), senhaField.getText()))) {
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