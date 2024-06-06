package Controllers;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.*;
import DAO.*;

public class QuartoController extends PadraoController<QuartoModel> {

    @FXML
    private TextField consultaQuartoField;

    @FXML
    private ListView<String> quartosListView;

    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

    @FXML
    private List<QuartoModel> quartosDisponiveis = homeDAO.listaQuartosDisponiveis();
  
    @FXML
    private List<ReservaModel> quartoIndisponiveis = homeDAO.listaReservas();
    
    @FXML
    private void initialize() {
        mascaraNumero(consultaQuartoField);
        for (QuartoModel quarto : quartosDisponiveis) {
            quartosListView.getItems().add("Disponível - " + formatarQuarto(quarto));
        }
        for (ReservaModel quarto : quartoIndisponiveis){
            quartosListView.getItems().add("Indisponível - " + formatarQuarto(quarto.getQuarto()));
        }
    }
    
    @FXML
    private void consultarQuarto() {
        if (consultaQuartoField.getText().isEmpty()) {
            quartosListView.getItems().clear();
            for (QuartoModel quarto : quartosDisponiveis) {
                quartosListView.getItems().add("Disponível - " + formatarQuarto(quarto));
            }
            for (ReservaModel quarto : quartoIndisponiveis){
                quartosListView.getItems().add("Indisponível - " + formatarQuarto(quarto.getQuarto()));
            }
        } else {
            List<QuartoModel> quartosDisponiveisFiltrados = quartosDisponiveis.stream()
                .filter(quarto -> String.valueOf(quarto.getNumeroQuarto()).equals(consultaQuartoField.getText()))
                .collect(Collectors.toList());

            List<ReservaModel> quartosIndisponiveisFiltrados = quartoIndisponiveis.stream()
                .filter(quarto -> String.valueOf(quarto.getQuarto().getNumeroQuarto()).equals(consultaQuartoField.getText()))
                .collect(Collectors.toList());

            quartosListView.getItems().clear();
            if (quartosDisponiveisFiltrados.isEmpty() && quartosIndisponiveisFiltrados.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Aviso", "Quarto não encontrado");
            } else {
                for (QuartoModel quarto : quartosDisponiveisFiltrados) {
                    quartosListView.getItems().add("Disponível - " + formatarQuarto(quarto));
                }
                for (ReservaModel quarto : quartosIndisponiveisFiltrados){
                    quartosListView.getItems().add("Indisponível - " + formatarQuarto(quarto.getQuarto()));
                }
            }
        }
    }

    @FXML
    private void irTelaQuarto(ActionEvent event){
        try {
            App.openNewWindow("CadastroQuarto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modificarQuartoSelecionado(ActionEvent event) {
        try{
            int selectedIndex = quartosListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("CadastroQuarto.fxml"));

                Parent root = loader.load();
                CadastroQuartoController controller = loader.getController();
                controller.setQuartoSelecionado(quartosDisponiveis.get(selectedIndex).getID());

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
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
            int quartoSelecionado = quartosDisponiveis.get(selectedIndex).getID();
            if (homeDAO.excluirQuarto(quartoSelecionado)) {
                quartosListView.getItems().remove(selectedIndex);
                quartosDisponiveis = homeDAO.listaQuartos();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Quarto excluído com sucesso");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao excluir o quarto do banco de dados");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um quarto para excluir");
        }
    }
}