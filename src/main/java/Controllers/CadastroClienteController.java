package Controllers;

import javafx.fxml.*;
import javafx.scene.control.*;
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
            if (validarCadastroCliente(cpfField.getText(), nomeCompletoField.getText(), dataNascimentoField.getText())) {
                ClienteModel cliente = new ClienteModel(cpfField.getText().replaceAll("[.\\-]", ""), 
                nomeCompletoField.getText(), inverterData(dataNascimentoField.getText()), enderecoField.getText());
                boolean sucesso = isCadastrar ? clienteDAO.inserirCliente(cliente) : clienteDAO.modificarCliente(clienteSelecionado.getID(), cliente);
                if (sucesso) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", isCadastrar ? "Cadastro de cliente bem-sucedido" : "Modificação de cliente bem-sucedida");
                    closeDialog(event);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", isCadastrar ? "Falha ao cadastrar o cliente" : "Falha ao modificar o cliente");
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar realizar a operação");
        }
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        closeDialog(event);
    }
}