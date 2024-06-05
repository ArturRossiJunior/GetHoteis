package Controllers;

import java.io.*;
import java.util.List;

import com.n2.hotelaria.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import DAO.*;
import Models.*;

public class CadastroReservaController extends PadraoController<ReservaModel>{

    @FXML
    private ComboBox<String> clienteComboBox, quartoComboBox;

    @FXML
    private TextField qtdPessoasField, valorEntradaField, dataReservaField, diaCheckInField, diaCheckOutField;

    @FXML
    private Button cadastrarButton, voltarButton;

    private ReservaModel reservaSelecionada;
    ReservaDAO reservaDAO = new ReservaDAO();
    List<ClienteModel> clientes = new HomeDAO().listaClientesDisponiveis();
    List<QuartoModel> quartos = new HomeDAO().listaQuartosDisponiveis();

    public void setReservaSelecionada(int reservaSelecionadaID) {
        reservaSelecionada = reservaDAO.montaReservaModel(reservaSelecionadaID);
        if (reservaSelecionada != null) {
            clienteComboBox.setValue(reservaSelecionada.getCliente().getNome());
            quartoComboBox.setValue(String.valueOf(reservaSelecionada.getQuarto().getNumeroQuarto()));
            qtdPessoasField.setText(String.valueOf(reservaSelecionada.getQtdPessoas()));
            valorEntradaField.setText(String.valueOf(reservaSelecionada.getValorEntrada()));
            dataReservaField.setText(desinverterData(reservaSelecionada.getDataReserva()));
            diaCheckInField.setText(desinverterData(reservaSelecionada.getDiaCheckIn()));
            diaCheckOutField.setText(desinverterData(reservaSelecionada.getDiaCheckOut()));
            cadastrarButton.setText("Modificar");
        }
    }

    @FXML
    private void initialize() {
        mascaraNumero(qtdPessoasField);
        mascaraValor(valorEntradaField);
        mascaraData(dataReservaField);
        mascaraData(diaCheckInField);
        mascaraData(diaCheckOutField);
        for (ClienteModel cliente : clientes) {
            clienteComboBox.getItems().add(cliente.getNome());
        }
        for (QuartoModel quarto : quartos) {
            quartoComboBox.getItems().add(String.valueOf(quarto.getNumeroQuarto()));
        }
    }

    @FXML
    private void handleCadastroReserva(ActionEvent event) {
        try {
            int selectedIndexQuarto = quartoComboBox.getSelectionModel().getSelectedIndex();
            int selectedIndexCliente = clienteComboBox.getSelectionModel().getSelectedIndex();
            
            if (selectedIndexQuarto == -1 || selectedIndexCliente == -1 || clienteComboBox.getValue() == null || quartoComboBox.getValue() == null || 
                    qtdPessoasField.getText().isEmpty() || valorEntradaField.getText().isEmpty() || dataReservaField.getText().isEmpty() || diaCheckInField.getText().isEmpty() || 
                        diaCheckOutField.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Erro", "Por favor, preencha todos os campos obrigatórios");
                return;
            }

            if(Integer.parseInt(qtdPessoasField.getText()) > quartos.get(selectedIndexQuarto).getTipoQuarto().getQuantidadeCamas()){
                showAlert(Alert.AlertType.WARNING, "Erro", "Quantidade de pessoas acima da quantidade de camas disponíveis");
                return;
            }

            ReservaModel reserva = new ReservaModel(quartos.get(selectedIndexQuarto), clientes.get(selectedIndexCliente), Integer.parseInt(qtdPessoasField.getText()),
                                                        Double.parseDouble(valorEntradaField.getText()), inverterData(dataReservaField.getText()), inverterData(diaCheckInField.getText()), 
                                                            inverterData(diaCheckOutField.getText()));

            boolean isCadastrar = cadastrarButton.getText().equals("Cadastrar");
            boolean sucesso = isCadastrar ? reservaDAO.inserirReserva(reserva) : reservaDAO.modificarReserva(reservaSelecionada.getID(), reserva);

            if (sucesso) {
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", isCadastrar ? "Cadastro de reserva bem-sucedido" : "Modificação de reserva bem-sucedida");
                App.changeScene("Home", (Stage) ((Node) event.getSource()).getScene().getWindow());
            } else {
                showAlert(Alert.AlertType.ERROR, "Erro", isCadastrar ? "Falha ao cadastrar a reserva" : "Falha ao modificar a reserva");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Por favor, insira valores numéricos válidos para quantidade de pessoas e valor de entrada.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) {
        try {
            App.changeScene("Home", (Stage)((Node)event.getSource()).getScene().getWindow());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao tentar mudar de cena");
        }
    }
}