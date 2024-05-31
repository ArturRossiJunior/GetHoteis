package DAO;

import java.util.*;
import Models.*;

public class HomeDAO extends PadraoDAO{

    public List<QuartoModel> listaQuartos() {
        String sql = "SELECT Q.ID AS QuartoID, Q.Numero_Quarto, TQ.ID, TQ.Nome, TQ.Quantidade_Camas, TQ.Valor_Diaria, TQ.Descricao FROM Quarto Q INNER JOIN Tipo_Quarto TQ ON Q.TipoQuarto_ID = TQ.ID;";
        return montaListaQuartos(sql);
    }

    public List<TipoQuartoModel> listaTiposQuartos() {
        String sql = "SELECT * FROM Tipo_Quarto";
        return montaListaModelTipoQuarto(sql);
    }

    public boolean excluirQuarto(int id){
        String sql = "DELETE FROM Quarto WHERE ID = ?";
        return executarOperacao(sql, id);
    }

    public boolean excluirTipoQuarto(int id){
        String sql = "DELETE FROM Tipo_Quarto WHERE ID = ?";
        return executarOperacao(sql, id);
    }
}