package Controllers;

import DAO.ReservaDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class DashController extends PadraoController implements Initializable {

    @FXML
    private BarChart<String, Integer> chartReserva;

    private final ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherGraficoReservasPorMes();
    }

    private void preencherGraficoReservasPorMes() {
        // Obtém os dados de reservas por mês
        Map<String, Integer> reservasPorMes = reservaDAO.getReservasPorMes();

        // Limpa o gráfico antes de adicionar novas séries
        chartReserva.getData().clear();

        // Cria uma nova série para adicionar ao gráfico
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Reservas por Mês");

        // Adiciona os dados ao gráfico
        for (Map.Entry<String, Integer> entry : reservasPorMes.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        // Adiciona a série ao gráfico
        chartReserva.getData().add(series);
    }
}
