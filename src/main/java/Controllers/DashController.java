package Controllers;

import DAO.ReservaDAO;
import Models.ReservaModel;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class DashController extends PadraoController<ReservaModel> implements Initializable {

    @FXML
    private BarChart<String, Integer> chartReserva;

    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherGraficoReservasPorMes();
    }

    private void preencherGraficoReservasPorMes() {
      
        Map<String, Integer> reservasPorMes = reservaDAO.getReservasPorMes();

        chartReserva.getData().clear();

        CategoryAxis xAxis = new CategoryAxis(); // Eixo X para meses
        NumberAxis yAxis = new NumberAxis(); // Eixo Y para números inteiros
       
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Reservas por Mês");

        
        for (Map.Entry<String, Integer> entry : reservasPorMes.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        chartReserva.getData().add(series);
    }
}