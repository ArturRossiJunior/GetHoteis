package Controllers;

import java.io.*;
import com.n2.hotelaria.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import DAO.*;
import Models.*;

public class CadastroClienteController extends PadraoController<ClienteModel>{

    @FXML
    private TextField nomeCompletoField, cpfField, dataNascimentoField, enderecoField;

    @FXML
    private Button cadastrarButton, voltarButton;

    private ClienteModel clienteSelecionado;
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public void setClienteSelecionado(int ClienteSelecionadoID) {
        clienteSelecionado = clienteDAO.montaClienteModel(ClienteSelecionadoID);
        if (clienteSelecionado != null) {
            nomeCompletoField.setText(clienteSelecionado.getNome());
            cpfField.setText(clienteSelecionado.getCpf());
            dataNascimentoField.setText(desinverterData(clienteSelecionado.getDataNascimento()));
            enderecoField.setText(clienteSelecionado.getEndereco());
            cadastrarButton.setText("Modificar");
        }
    }

    @FXML
    private void initialize() { 
        mascaraCPF(cpfField);
        mascaraNome(nomeCompletoField);
        mascaraData(dataNascimentoField);
    }

    @FXML
    private void handleCadastroCliente(ActionEvent event) {
        try {
            boolean isCadastrar = cadastrarButton.getText().equals("Cadastrar");

            if (clienteDAO.consultaCPFCliente(cpfField.getText().replaceAll("[.\\-]", ""))) {
                if (isCadastrar || (!isCadastrar && !clienteSelecionado.getCpf().replaceAll("[.\\-]", "").equals(cpfField.getText().replaceAll("[.\\-]", "")))) {
                    showAlert(Alert.AlertType.WARNING, "Erro", "CPF já existe");
                    return;
                }
            }
            
            if(validarCadastroCliente(cpfField.getText(), nomeCompletoField.getText(), dataNascimentoField.getText())){
                ClienteModel cliente = new ClienteModel(cpfField.getText().replaceAll("[.\\-]", ""), 
                nomeCompletoField.getText(), inverterData(dataNascimentoField.getText()), enderecoField.getText());
                boolean sucesso = isCadastrar ? clienteDAO.inserirCliente(cliente) : clienteDAO.modificarCliente(clienteSelecionado.getID(), cliente);
                if (sucesso) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", isCadastrar ? "Cadastro de cliente bem-sucedido" : "Modificação de cliente bem-sucedida");
                    App.changeScene("Home", (Stage) ((Node) event.getSource()).getScene().getWindow());
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", isCadastrar ? "Falha ao cadastrar o cliente" : "Falha ao modificar o cliente");
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }

<<<<<<< HEAD
private void closeCadastroCliente(ActionEvent event){
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    App.closeWindow(stage);
}


    @FXML
    private void handleCloseButtonAction() {
        // Obtém o Stage atual a partir do botão
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        // Fecha a janela
        stage.close();
    }
}
=======
    @FXML
    private void closeCadastroCliente(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App.closeWindow(stage);
    }
}
>>>>>>> fa3a532a38284228a4792077d1e72f52bd117376
