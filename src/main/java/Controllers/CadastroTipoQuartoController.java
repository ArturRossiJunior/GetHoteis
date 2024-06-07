package Controllers;

import java.io.*;
import DAO.*;
import Models.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.*;

public class CadastroTipoQuartoController extends PadraoController<TipoQuartoModel> {

    @FXML
    private TextField nomeTipoQuartoField, quantidadeCamasField, valorDiariaField;
    @FXML
    private TextArea descricaoArea;
    @FXML
    private Button cadastrarButton,voltarButton;

    private TipoQuartoModel quartoSelecionado;
    private final TipoQuartoDAO cadastroTipoQuartoDAO = new TipoQuartoDAO();

    @FXML
    private void initialize() {
        mascaraNome(nomeTipoQuartoField);
        mascaraNumero(quantidadeCamasField);
        mascaraValor(valorDiariaField);
    }

    @FXML
    public void setTipoQuartoSelecionado(int tipoQuartoSelecionado) {
        quartoSelecionado = cadastroTipoQuartoDAO.montaTipoQuartoModel(tipoQuartoSelecionado);
        if (quartoSelecionado != null) {
            nomeTipoQuartoField.setText(String.valueOf(quartoSelecionado.getNome()));
            quantidadeCamasField.setText(String.valueOf(quartoSelecionado.getQuantidadeCamas()));
            valorDiariaField.setText(String.valueOf(quartoSelecionado.getValorDiaria()));
            descricaoArea.setText(String.valueOf(quartoSelecionado.getDescricao()));
            cadastrarButton.setText("Modificar");
        }
    }

    @FXML
    private void handleCadastroTipoQuarto(ActionEvent event) throws IOException {
        try {
            String nome = nomeTipoQuartoField.getText();
            String camas = quantidadeCamasField.getText();
            String valor = valorDiariaField.getText();
            String descricao = descricaoArea.getText();
            
            if (validacaoCadastroTipoQuarto(cadastroTipoQuartoDAO, nome, camas, valor, descricao)) {
                TipoQuartoModel tipoQuarto = new TipoQuartoModel(nome, Integer.parseInt(camas), Double.parseDouble(valor), descricao);
                boolean sucesso = cadastrarButton.getText().equals("Cadastrar") ? 
                                cadastroTipoQuartoDAO.inserirTipoQuarto(tipoQuarto) : 
                                cadastroTipoQuartoDAO.modificarTipoQuarto(quartoSelecionado.getID(), tipoQuarto);
                
                if (sucesso) {
                    showAlert(Alert.AlertType.INFORMATION, "Sucesso", cadastrarButton.getText().equals("Cadastrar") ? "Cadastro bem-sucedido" : "Modificação bem-sucedida");
                    closeDialog(event);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao " + (cadastrarButton.getText().equals("Cadastrar") ? "cadastrar" : "modificar"));
                }
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira valores numéricos válidos");
        }
    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        closeDialog(event);
    }
}