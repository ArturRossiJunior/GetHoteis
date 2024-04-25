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

    private final CadastroDAO dao = new CadastroDAO();

    @FXML
    private void initialize() {
        cadastrarButton.setOnAction(event -> handleCadastro());
        voltarButton.setOnAction(event -> handleVoltar());
    }

    @FXML
    private void handleCadastro() {
        try {
            LocalDate data = LocalDate.parse(dataNascimentoField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (validacaoCadastro(dao, emailField.getText(), cpfField.getText(), data)) {
                if (dao.inserirUsuario(new CadastroModel(nomeCompletoField.getText(), emailField.getText(), cpfField.getText(), data, senhaField.getText()))) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro bem-sucedido!");
                    App.changeScene("Login");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Usuário ou senha inválidos");
                }
            }
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Formato de data inválido. Use o formato dd/MM/yyyy.");
        } catch (IOException e) {
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