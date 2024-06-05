package Controllers;

import java.io.*;
import java.util.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.*;
import DAO.*;
import java.util.stream.*;

public class ClienteController extends PadraoController<ClienteModel> {

    @FXML
    private ListView<String> clientesListView;
  
    @FXML
    private TextField consultaClienteField;
     
    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

    @FXML
    private List<ClienteModel> clientes = homeDAO.listaClientes();

    @FXML
    private List<TipoQuartoModel> tiposQuartos = homeDAO.listaTiposQuartos();

    @FXML
    private List<ReservaModel> reservas = homeDAO.listaReservas();
      
    @FXML
    private void initialize() {
        for (ClienteModel cliente : clientes) {
            String clienteInfo = formatarCliente(cliente);
            for (ReservaModel reserva : reservas) {
                if (reserva.getCliente().getID() == cliente.getID()) {
                    clienteInfo += " - Código da Reserva: " + reserva.getID();
                    break;
                }
            }
            clientesListView.getItems().add(clienteInfo);
        }
    }
    
    @FXML
    private void consultarCliente() {
        String cpf = consultaClienteField.getText().replaceAll("[.\\-]", "");
        if(cpf.isEmpty()){
            clientesListView.getItems().clear();
            for (ClienteModel cliente : clientes) {
                clientesListView.getItems().add(formatarCliente(cliente));
            }
        } else {
            List<ClienteModel> clientesFiltrados = clientes.stream()
                .filter(cliente -> cliente.getCpf().replaceAll("[.\\-]", "").equals(cpf))
                .collect(Collectors.toList());
            clientesListView.getItems().clear();
            if (clientesFiltrados.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Cliente não encontrado");
            } else {
                for (ClienteModel cliente : clientesFiltrados) {
                    clientesListView.getItems().add(formatarCliente(cliente));
                }
            }
        }
    }
    
    @FXML
    private void modificarClienteSelecionado(ActionEvent event) {
        try {
            int selectedIndex = clientesListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("CadastroCliente.fxml"));

                Parent root = loader.load();
                CadastroClienteController controller = loader.getController();
                controller.setClienteSelecionado(clientes.get(selectedIndex).getID());

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.sizeToScene();
            } else {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um cliente para modificar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void excluirClienteSelecionado() {
        int selectedIndex = clientesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Optional<ButtonType> result = confirmaExclusao();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int clienteSelecionado = clientes.get(selectedIndex).getID();
                boolean clienteTemReserva = false;
                for (ReservaModel reserva : reservas) {
                    if (reserva.getCliente().getID() == clienteSelecionado) {
                        clienteTemReserva = true;
                        break;
                    }
                }
                if (!clienteTemReserva) {
                    if (homeDAO.excluirCliente(clienteSelecionado)) {
                        clientesListView.getItems().remove(selectedIndex);
                        clientes = homeDAO.listaClientes();
                        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cliente excluído com sucesso");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erro", "Falha ao excluir o cliente do banco de dados");
                    }
                } else {
                    showAlert(Alert.AlertType.ERROR, "Erro", "Não é possível excluir um cliente com reserva efetuada");
                }
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione um cliente para excluir");
        }
    }
    
    @FXML
    private void irCadastroCliente(ActionEvent event){
        try {
            if(!tiposQuartos.isEmpty())
                App.openNewWindow("CadastroCliente");
            else
                showAlert(Alert.AlertType.ERROR, "Erro", "Necessário criar ao menos um tipo de quarto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}