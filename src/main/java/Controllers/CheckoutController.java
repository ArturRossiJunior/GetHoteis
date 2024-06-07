package Controllers;

import java.util.List;
import DAO.*;
import Models.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckoutController extends PadraoController<PadraoModel>{

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField codigoReservaField;

    private final HomeDAO homeDAO = new HomeDAO();
    private List<ReservaModel> reservas = homeDAO.listaReservas();

    @FXML
    private void initialize() {
        mascaraNumero(codigoReservaField);
    }

    @FXML
    private void handleConfirmButton(ActionEvent event){
        boolean reservaEncontrada = false;
        for(ReservaModel reserva : reservas){
            if(reserva.getID() == Integer.parseInt(codigoReservaField.getText())){
                reservaEncontrada = true;
                showAlert(AlertType.INFORMATION, "Sucesso", "Checkout confirmado");
                showRatingPopup();
                break;
            }
        }
        if (!reservaEncontrada) {
            showAlert(AlertType.ERROR, "Erro", "Reserva não encontrada");
        }
    }

    private void showRatingPopup() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Avaliação da Reserva");

        VBox dialogVBox = new VBox(10);
        dialogVBox.setStyle("-fx-padding: 10;");

        Text message = new Text("Por favor, avalie sua experiência:");
        
        Slider ratingSlider = new Slider(1, 5, 3);
        ratingSlider.setShowTickLabels(true);
        ratingSlider.setShowTickMarks(true);
        ratingSlider.setMajorTickUnit(1);
        ratingSlider.setMinorTickCount(0);
        ratingSlider.setSnapToTicks(true);

        Button submitButton = new Button("Enviar");
        submitButton.setOnAction(e -> {
            int rating = (int) ratingSlider.getValue();
            dialog.close();
            showAlert(AlertType.INFORMATION, "Obrigado", "Sua avaliação foi registrada. Nota: " + rating);
        });

        dialogVBox.getChildren().addAll(message, ratingSlider, submitButton);

        Scene dialogScene = new Scene(dialogVBox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
