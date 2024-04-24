package Controllers;

import DAO.CadastroDAO;
import Models.CadastroModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroController extends PadraoController<CadastroModel> {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField senhaField;
    
    @FXML
    private Button cadastrarButton;

    @FXML
    private void initialize() {
        cadastrarButton.setOnAction(event -> handleCadastro());
    }

    @FXML
    private void handleCadastro() {
        String usuario = usuarioField.getText();
        String senha = senhaField.getText();

        CadastroModel novoUsuario = new CadastroModel(usuario, senha);

        CadastroDAO cadastroDAO = new CadastroDAO();

        if (cadastroDAO.inserirUsuario(novoUsuario)) {
            showAlert("Cadastro bem-sucedido!");
        } else {
            showAlert("Usuario ou senha inválidos");
        }
    }
}