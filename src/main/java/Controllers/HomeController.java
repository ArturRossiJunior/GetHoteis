package Controllers;

import java.io.*;
import java.util.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import DAO.*;

public class HomeController extends PadraoController {

    @FXML
    private ListView<String> quartosListView, tiposQuartoListView;

    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

    @FXML
    private List<QuartoModel> quartos = homeDAO.listaQuartos();

    @FXML
    private List<TipoQuartoModel> tiposQuartos = homeDAO.listaTiposQuartos();

    @FXML
    private void initialize() {
        for (QuartoModel quarto : quartos) {
            quartosListView.getItems().add(formatarQuarto(quarto));
        }
        for (TipoQuartoModel tipoQuarto : tiposQuartos) {
            tiposQuartoListView.getItems().add(formatarTipoQuarto(tipoQuarto));
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
    private void modificarTipoQuartoSelecionado(ActionEvent event) {
        try{
            int selectedIndex = tiposQuartoListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("CadastroTipoQuarto.fxml"));

                Parent root = loader.load();
                CadastroTipoQuartoController controller = loader.getController();
                controller.setQuartoSelecionado(tiposQuartos.get(selectedIndex).getID());

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.sizeToScene();
            } else {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um tipo de quarto para modificar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void excluirQuartoSelecionado(ActionEvent event) {
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

    @FXML
    private void excluirTipoQuartoSelecionado(ActionEvent event) {
        int selectedIndex = tiposQuartoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            int tipoQuartoSelecionado = tiposQuartos.get(selectedIndex).getID();
            if (homeDAO.excluirTipoQuarto(tipoQuartoSelecionado)) {
                tiposQuartoListView.getItems().remove(selectedIndex);
                tiposQuartos = homeDAO.listaTiposQuartos();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Tipo quarto excluído com sucesso");
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", "Não é permitido deletar um tipo de quarto que esteja associado a um quarto");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um tipo quarto para excluir");
        }
    }

    @FXML
    private void openCadastroQuarto(ActionEvent event) {
        try {
            if(!tiposQuartos.isEmpty())
                App.changeScene("CadastroQuarto", (Stage)((Node)event.getSource()).getScene().getWindow());
            else
                showAlert(Alert.AlertType.ERROR, "Erro", "Necessário criar ao menos um tipo de quarto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openCadastroTipoQuarto(ActionEvent event) {
        try {
            App.changeScene("CadastroTipoQuarto", (Stage)((Node)event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}