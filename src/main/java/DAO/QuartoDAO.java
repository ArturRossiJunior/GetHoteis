package DAO;

import Models.*;

public class QuartoDAO extends PadraoDAO {

    public boolean inserirQuarto(QuartoModel quarto) {
        String sql = "INSERT INTO Quarto (Numero_Quarto, TipoQuarto_ID) VALUES (?, ?)";
        return executarOperacao(sql, quarto.getNumeroQuarto(), quarto.getTipoQuarto().getID());
    }

    public boolean modificarQuarto(int ID, QuartoModel quarto) {
        String sql = "UPDATE Quarto SET Numero_Quarto = ?, TipoQuarto_ID = ? WHERE ID = ?";
        return executarOperacao(sql, quarto.getNumeroQuarto(), quarto.getTipoQuarto().getID(), ID);
    }

    public QuartoModel montaQuartoModel(int ID){
        String sql = "SELECT Q.ID AS QuartoID, Q.Numero_Quarto, TQ.ID, TQ.Nome, TQ.Quantidade_Camas, TQ.Valor_Diaria, TQ.Descricao FROM Quarto Q INNER JOIN Tipo_Quarto TQ ON Q.TipoQuarto_ID = TQ.ID WHERE Q.ID = ?;";
        return montaListaQuartos(sql, ID).get(0);
    }

    public boolean consultaNumeroQuarto(String numeroQuarto){
        String sql = "SELECT Numero_Quarto FROM Quarto WHERE Numero_Quarto = ?";
        return executarOperacao(sql, numeroQuarto);
    }
}