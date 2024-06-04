package Controllers;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import DAO.*;

public class QuartoController extends PadraoController {

      @FXML
    private TextField consultaQuartoField;

    @FXML
    private ListView<String> quartosListView;

    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

    @FXML
    private List<QuartoModel> quartos = homeDAO.listaQuartos();
  
    

    @FXML
    private void initialize() {
        
        mascaraNumero(consultaQuartoField);
        
        for (QuartoModel quarto : quartos) {
            quartosListView.getItems().add(formatarQuarto(quarto));
        }
        
    }

    
    
    @FXML
    private void consultarQuarto() {
        String numeroQuarto = consultaQuartoField.getText();
        List<QuartoModel> quartosFiltrados = quartos.stream()
                 .filter(quarto -> Integer.toString(quarto.getNumeroQuarto()).equals(numeroQuarto))
                .collect(Collectors.toList());

        quartosListView.getItems().clear();

        if (quartosFiltrados.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Erro", "Quarto não encontrado");
        } else {
            for (QuartoModel quarto : quartosFiltrados) {
                quartosListView.getItems().add(formatarQuarto(quarto));
            }
        }
    }
   @FXML
    private void irTelaQuarto(ActionEvent event){
     try {
        App.openNewWindow("CadastroQuarto");
    } catch (IOException e) {
        e.printStackTrace();
    }}

    @FXML
    private void modificarQuartoSelecionado(ActionEvent event) {
        try{
            int selectedIndex = quartosListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("CadastroQuarto.fxml"));

                Parent root = loader.load();
                CadastroQuartoController controller = loader.getController();
                controller.setQuartoSelecionado(quartos.get(selectedIndex).getID());

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.sizeToScene();
            } else {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um quarto para modificar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void excluirQuartoSelecionado() {
        int selectedIndex = quartosListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            int quartoSelecionado = quartos.get(selectedIndex).getID();
            if (homeDAO.excluirQuarto(quartoSelecionado)) {
                quartosListView.getItems().remove(selectedIndex);
                quartos = homeDAO.listaQuartos();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Quarto excluído com sucesso");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao excluir o quarto do banco de dados");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um quarto para excluir");
        }
    }

}