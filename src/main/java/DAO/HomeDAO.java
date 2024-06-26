package DAO;

import java.util.*;
import Models.*;

public class HomeDAO extends PadraoDAO{

    public List<QuartoModel> listaQuartos() {
        String sql = "SELECT Q.ID AS QuartoID, Q.Numero_Quarto, TQ.ID, TQ.Nome, TQ.Quantidade_Camas, TQ.Valor_Diaria, TQ.Descricao FROM Quarto Q INNER JOIN Tipo_Quarto TQ ON Q.TipoQuarto_ID = TQ.ID;";
        return montaListaModelQuartos(sql);
    }

    public List<QuartoModel> listaQuartosDisponiveis() {
        String sql =    "SELECT q.ID AS QuartoID, q.Numero_Quarto, tq.ID AS ID, tq.Nome, tq.Quantidade_Camas, tq.Valor_Diaria, tq.Descricao " +
                        "FROM Quarto q " +
                        "LEFT JOIN Reserva r ON q.ID = r.Quarto_ID " +
                        "JOIN Tipo_Quarto tq ON q.TipoQuarto_ID = tq.ID " +
                        "WHERE r.ID IS NULL;";
        return montaListaModelQuartos(sql);
    }

    public List<TipoQuartoModel> listaTiposQuartos() {
        String sql = "SELECT * FROM Tipo_Quarto";
        return montaListaModelTipoQuarto(sql);
    }

    public List<ClienteModel> listaClientes(){
        String sql = "SELECT * FROM Cliente";
        return montaListaModelCliente(sql);
    }

    public List<ClienteModel> listaClientesDisponiveis(){
        String sql = "SELECT * FROM Cliente WHERE ID NOT IN (SELECT DISTINCT Cliente_ID FROM Reserva);";
        return montaListaModelCliente(sql);
    }

    public List<ReservaModel> listaReservas(){
        String sql =    "SELECT " +
                        "Cliente.ID AS ClienteID, Cliente.CPF, Cliente.Nome, Cliente.Data_Nascimento, Cliente.Endereco, " +
                        "Quarto.ID AS QuartoID, Quarto.Numero_Quarto, " +
                        "Tipo_Quarto.ID AS TipoQuartoID, Tipo_Quarto.Nome AS TipoQuartoNome, Tipo_Quarto.Quantidade_Camas, Tipo_Quarto.Valor_Diaria, Tipo_Quarto.Descricao, " +
                        "Reserva.ID AS ReservaID, Reserva.Qtd_Pessoas, Reserva.Valor_Entrada, Reserva.Data_Reserva, Reserva.Dia_CheckIn, Reserva.Dia_CheckOut " +
                        "FROM Reserva " +
                        "INNER JOIN Cliente ON Reserva.Cliente_ID = Cliente.ID " +
                        "INNER JOIN Quarto ON Reserva.Quarto_ID = Quarto.ID " +
                        "INNER JOIN Tipo_Quarto ON Quarto.TipoQuarto_ID = Tipo_Quarto.ID;";
        return montaListaModelReserva(sql);
    }

    public boolean excluirQuarto(int ID){
        String sql = "DELETE FROM Quarto WHERE ID = ?";
        return executarOperacao(sql, ID);
    }

    public boolean excluirTipoQuarto(int ID){
        String sql = "DELETE FROM Tipo_Quarto WHERE ID = ?";
        return executarOperacao(sql, ID);
    }

    public boolean excluirCliente(int ID){
        String sql = "DELETE FROM Cliente WHERE ID = ?";
        return executarOperacao(sql, ID);
    }

    public boolean excluirReserva(int ID){
        String sql = "DELETE FROM Reserva WHERE ID = ?";
        return executarOperacao(sql, ID);
    }
}