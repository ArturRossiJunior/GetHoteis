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
import javafx.scene.control.Alert.AlertType;

public class TipoQuartoController extends PadraoController<QuartoModel> {

    @FXML
    private ListView<String> tiposQuartoListView;
    
    @FXML
    private final HomeDAO homeDAO = new HomeDAO();
    
    @FXML
    private List<TipoQuartoModel> tiposQuartos = homeDAO.listaTiposQuartos();

    @FXML
    private void initialize() {
        for (TipoQuartoModel tipoQuarto : tiposQuartos) {
            tiposQuartoListView.getItems().add(formatarTipoQuarto(tipoQuarto));
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
                controller.setTipoQuartoSelecionado(tiposQuartos.get(selectedIndex).getID());

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } else {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um tipo de quarto para modificar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void excluirTipoQuartoSelecionado() {
        int selectedIndex = tiposQuartoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Optional<ButtonType> result = confirmaExclusao();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int tipoQuartoSelecionado = tiposQuartos.get(selectedIndex).getID();
                if (homeDAO.excluirTipoQuarto(tipoQuartoSelecionado)) {
                    tiposQuartoListView.getItems().remove(selectedIndex);
                    tiposQuartos = homeDAO.listaTiposQuartos();
                    showAlert(AlertType.INFORMATION, "Sucesso", "Tipo de quarto excluído com sucesso");
                } else {
                    showAlert(AlertType.ERROR, "Erro", "Não é permitido deletar um tipo de quarto que esteja associado a um quarto");
                }
            }
        } else {
            showAlert(AlertType.WARNING, "Erro", "Por favor, selecione um tipo de quarto para excluir");
        }
    }
    
     @FXML
    private void irCadastroTipoQuarto(ActionEvent event){
        try {
             App.openNewWindow("CadastroTipoQuarto");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}