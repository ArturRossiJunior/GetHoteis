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
import java.util.stream.*;

public class FuncionarioController extends PadraoController<ClienteModel> {

    @FXML
    private ListView<String> funcionariosListView;
    @FXML
    private List<funeModel> funcionarios = homeDAO.listaClientes();
  
    @FXML
    private void initialize() {
       
    }
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