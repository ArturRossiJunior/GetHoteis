package DAO;

import Models.*;

public class TipoQuartoDAO extends PadraoDAO {

    public boolean inserirTipoQuarto(TipoQuartoModel tipoQuarto) {
        String sql = "INSERT INTO Tipo_Quarto (Nome, Quantidade_Camas, Valor_Diaria, Descricao) VALUES (?, ?, ?, ?)";
        return executarOperacao(sql, tipoQuarto.getNome(), tipoQuarto.getQuantidadeCamas(), tipoQuarto.getValorDiaria(), tipoQuarto.getDescricao());
    }

    public TipoQuartoModel montaTipoQuartoModel(int ID){
        String sql = "SELECT * FROM Tipo_Quarto WHERE ID = ?;";
        return montaListaModelTipoQuarto(sql, ID).get(0);
    }

    public boolean modificarTipoQuarto(int ID, TipoQuartoModel tipoQuartoModel){
        String sql = "UPDATE Tipo_Quarto SET Nome = ?, Quantidade_Camas = ?, Valor_Diaria = ?, Descricao = ? WHERE ID = ?";
        return executarOperacao(sql, tipoQuartoModel.getNome(), tipoQuartoModel.getQuantidadeCamas(), tipoQuartoModel.getValorDiaria(), tipoQuartoModel.getDescricao(), ID);
    }
}