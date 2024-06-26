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
import java.net.URL;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;

public class HomeController extends PadraoController<PadraoModel> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Menu, MenuBack;
    
     @FXML
    private Button btnCheckIn_Menu, btnCheckOut_Menu, btnClientes_Menu, btnDashboard_Menu, btnUsuario_Menu, btnQuartos_Menu, btnReserva_Menu;

    @FXML
    private AnchorPane slider,rootPane;;
    
    @FXML
    private StackPane telaHome;

    @FXML
    private ListView<String> quartosListView, tiposQuartoListView;

    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

    @FXML
    private List<QuartoModel> quartos = homeDAO.listaQuartos();

    @FXML
    private List<TipoQuartoModel> tiposQuartos = homeDAO.listaTiposQuartos();
    
    private boolean MenuVisivel = true;
    
    @FXML
    private void initialize() {
        slider.setTranslateX(-200);
        //Menu.setOnMouseClicked(event ->esconderMenu());
        MenuVisivel=true;
    }
    
    @FXML
    private void esconderMenu(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);
        if(MenuVisivel){
            slide.setToX(0);
            slider.setTranslateX(-200);
            MenuVisivel = false;
        } else {
            slide.setToX(-200);
            slider.setTranslateX(0);
            MenuVisivel = true;
        }
        slide.play();
    }
    
    @FXML
    private void showCliente(javafx.event.ActionEvent event) throws IOException {
        try{
        Parent fxml = FXMLLoader.load(App.class.getResource("Clientes.fxml"));
        telaHome.getChildren().removeAll();
        telaHome.getChildren().setAll(fxml);
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    @FXML
    private void showQuarto(javafx.event.ActionEvent event) throws IOException {
        try{
        Parent fxml = FXMLLoader.load(App.class.getResource("Quartos.fxml"));
        telaHome.getChildren().removeAll();
        telaHome.getChildren().setAll(fxml);
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    
    @FXML
    private void showUsuario(javafx.event.ActionEvent event) throws IOException {
        try{
        Parent fxml = FXMLLoader.load(App.class.getResource("Usuarios.fxml"));
        telaHome.getChildren().removeAll();
        telaHome.getChildren().setAll(fxml);
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
       
    @FXML
    private void showDash(javafx.event.ActionEvent event) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(App.class.getResource("Dashboard.fxml"));
            telaHome.getChildren().removeAll();
            telaHome.getChildren().setAll(fxml);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void showReserva(javafx.event.ActionEvent event) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(App.class.getResource("Reservas.fxml"));
            telaHome.getChildren().removeAll();
            telaHome.getChildren().setAll(fxml);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void showTipoQuarto(javafx.event.ActionEvent event) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(App.class.getResource("TipoQuarto.fxml"));
            telaHome.getChildren().removeAll();
            telaHome.getChildren().setAll(fxml);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
       private void showCheckOut(javafx.event.ActionEvent event) throws IOException {
        try {
            Parent fxml = FXMLLoader.load(App.class.getResource("Checkout.fxml"));
            telaHome.getChildren().removeAll();
            telaHome.getChildren().setAll(fxml);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
     
    @FXML
    private void irTelaClientes(ActionEvent event){
        try {
            App.openNewWindow("Clientes");
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
                controller.setTipoQuartoSelecionado(tiposQuartos.get(selectedIndex).getID());

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
}