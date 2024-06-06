package Controllers;

import DAO.*;
import Models.*;
import java.util.*;
import java.util.stream.Collectors;
import javafx.fxml.*;
import javafx.scene.control.*;

public class UsuarioController extends PadraoController<UsuarioModel> {

    @FXML
    private TextField consultaUsuarioField;

    @FXML
    private ListView<String> UsuariosListView;

    @FXML
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private List<UsuarioModel> usuarios = usuarioDAO.listaUsuarios();
    
    @FXML
    private void initialize() {
        mascaraCPF(consultaUsuarioField);
        for (UsuarioModel usuario : usuarios) {
            UsuariosListView.getItems().add(formatarUsuario(usuario));
        }
    }

    @FXML
    private void consultarusuario() {
        String cpf = consultausuarioField.getText().replaceAll("[.\\-]", "");
        if(cpf.isEmpty()){
            usuariosListView.getItems().clear();
            for (usuarioModel usuario : usuarios) {
                usuariosListView.getItems().add(formatarUsuario(usuario));
            }
        } else {
            List<usuarioModel> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> usuario.getCpf().replaceAll("[.\\-]", "").equals(cpf))
                .collect(Collectors.toList());
            usuariosListView.getItems().clear();
            if (usuariosFiltrados.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Usuario não encontrado");
            } else {
                for (usuarioModel usuario : usuariosFiltrados) {
                    usuariosListView.getItems().add(formatarUsuario(usuario));
                }
            }
        }
    }
}