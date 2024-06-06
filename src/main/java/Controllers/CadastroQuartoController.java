package Controllers;

import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.*;
import DAO.*;
import Models.*;

public class CadastroQuartoController extends PadraoController<QuartoModel> {

    @FXML
    private TextField numeroQuartoField;

    @FXML
    private ComboBox<String> tipoQuartoComboNome;

    @FXML
    private Button cadastrarButton, voltarButton;

    private QuartoModel quartoSelecionado;
    private QuartoDAO cadastroQuartoDAO = new QuartoDAO();
    private List<TipoQuartoModel> tiposDeQuarto = new HomeDAO().listaTiposQuartos();

    public void setQuartoSelecionado(int quartoSelecionadoID) {
        quartoSelecionado = cadastroQuartoDAO.montaQuartoModel(quartoSelecionadoID);
        if (quartoSelecionado != null) {
            tipoQuartoComboNome.getItems().add(quartoSelecionado.getTipoQuarto().getNome());
            tiposDeQuarto.add(quartoSelecionado.getTipoQuarto());
            numeroQuartoField.setText(String.valueOf(quartoSelecionado.getNumeroQuarto()));
            tipoQuartoComboNome.setValue(quartoSelecionado.getTipoQuarto().getNome());
            cadastrarButton.setText("Modificar");
        }
    }

    @FXML
    private void initialize() { 
        mascaraNumero(numeroQuartoField);
        for (TipoQuartoModel tipoQuarto : tiposDeQuarto) {
            tipoQuartoComboNome.getItems().add(tipoQuarto.getNome());
        }
    }

    @FXML
    private void handleCadastroQuarto(ActionEvent event) {
        try {
            int selectedIndex = tipoQuartoComboNome.getSelectionModel().getSelectedIndex();
            if (selectedIndex == -1) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um tipo de quarto");
                return;
            }
    
            String numeroQuarto = numeroQuartoField.getText();
            boolean numeroExistente = cadastroQuartoDAO.consultaNumeroQuarto(numeroQuarto);
            boolean isCadastrar = cadastrarButton.getText().equals("Cadastrar");
    
            if (numeroExistente && (isCadastrar || (!isCadastrar && quartoSelecionado.getNumeroQuarto() != Integer.valueOf(numeroQuarto)))) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Número do quarto já existe");
                return;
            }
    
            QuartoModel quarto = new QuartoModel(Integer.parseInt(numeroQuarto), tiposDeQuarto.get(selectedIndex));
            boolean sucesso = isCadastrar ? cadastroQuartoDAO.inserirQuarto(quarto) : cadastroQuartoDAO.modificarQuarto(quartoSelecionado.getID(), quarto);
    
            if (sucesso) {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", isCadastrar ? "Cadastro de quarto bem-sucedido" : "Modificação de quarto bem-sucedida");
                closeDialog(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", isCadastrar ? "Falha ao cadastrar o quarto" : "Falha ao modificar o quarto");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }
    
    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        closeDialog(event);
    }
}