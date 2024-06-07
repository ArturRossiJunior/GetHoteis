package Controllers;

import java.io.*;
import java.util.*;
import Models.*;
import com.n2.hotelaria.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.*;
import DAO.*;
import java.util.stream.Collectors;

public class ReservaController extends PadraoController<ReservaModel> {

    @FXML
    private Button btnBuscarReserva, cadastroReservaButton, excluirReservaButton;
    
    @FXML
    private TextField consultaReservaField;
    @FXML
    private ListView<String> reservasListView;

    @FXML
    private final HomeDAO homeDAO = new HomeDAO();

   @FXML
    private List<ReservaModel> reservas = homeDAO.listaReservas();

    @FXML
    private void initialize() {
        mascaraNumero(consultaReservaField);
        for (ReservaModel reserva : reservas) {
            reservasListView.getItems().add(formatarReserva(reserva));
        }
    }
    
    @FXML
    private void consultarReserva() {
        if (consultaReservaField.getText().isEmpty()) {
            reservasListView.getItems().clear();
            for (ReservaModel reserva : reservas) {
                reservasListView.getItems().add(formatarReserva(reserva));
            }
        } else {
            List<ReservaModel> reservasFiltradas = reservas.stream()
                .filter(reserva -> String.valueOf(reserva.getID()).equals(consultaReservaField.getText()))
                .collect(Collectors.toList());

            if (reservasFiltradas.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Aviso", "Reserva não encontrada");
            } else {
                reservasListView.getItems().clear();
                for (ReservaModel reserva : reservasFiltradas) {
                    reservasListView.getItems().add(formatarReserva(reserva));
                }
            }
        }
    }

    @FXML
    private void modificarReservaSelecionada(ActionEvent event) {
        try {
            int selectedIndex = reservasListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("CadastroReserva.fxml"));

                Parent root = loader.load();
                CadastroReservaController controller = loader.getController();
                controller.setReservaSelecionada(reservas.get(selectedIndex).getID());

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } else {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, selecione uma reserva para modificar");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void excluirReservaSelecionada() {
        int selectedIndex = reservasListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Optional<ButtonType> result = confirmaExclusao();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int reservaSelecionada = reservas.get(selectedIndex).getID();
                if (homeDAO.excluirReserva(reservaSelecionada)) {
                    reservasListView.getItems().remove(selectedIndex);
                    reservas = homeDAO.listaReservas();
                    showAlert(AlertType.INFORMATION, "Sucesso", "Reserva excluída com sucesso");
                } else {
                    showAlert(AlertType.ERROR, "Erro", "Falha ao excluir a reserva do banco de dados");
                }
            }
        } else {
            showAlert(AlertType.WARNING, "Erro", "Por favor, selecione uma reserva para excluir");
        }
    }

    @FXML
    private void irCadastroReserva(ActionEvent event){
        try {
            App.openNewWindow("CadastroReserva");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}