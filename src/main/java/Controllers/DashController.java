package Controllers;

import DAO.ReservaDAO;
import Models.ReservaModel;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DashController extends PadraoController<ReservaModel> implements Initializable {

    @FXML
    private BarChart<String, Number> chartReserva; // Modificado para usar Number no eixo Y

    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherGraficoReservasPorMes();
    }

    private void preencherGraficoReservasPorMes() {
        Map<String, Integer> reservasPorMes = reservaDAO.getReservasPorMes();

        chartReserva.getData().clear();

        // Configurar o eixo X e Y
        CategoryAxis xAxis = (CategoryAxis) chartReserva.getXAxis();
        NumberAxis yAxis = (NumberAxis) chartReserva.getYAxis();

        // Garantir que o eixo Y mostre apenas números inteiros
        yAxis.setTickUnit(1);
        yAxis.setMinorTickCount(0);
        yAxis.setForceZeroInRange(true);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Reservas por Mês");

        for (Map.Entry<String, Integer> entry : reservasPorMes.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        chartReserva.getData().add(series);
    }
}
