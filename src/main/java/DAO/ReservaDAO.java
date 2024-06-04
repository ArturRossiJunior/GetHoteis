package DAO;

import Models.*;

public class ReservaDAO extends PadraoDAO {

    public boolean inserirReserva(ReservaModel reservaModel) {
        String sql = "INSERT INTO Reserva (Quarto_ID, Cliente_ID, Qtd_Pessoas, Valor_Entrada, Data_Reserva, Dia_CheckIn, Dia_CheckOut) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executarOperacao(sql, reservaModel.getQuarto().getID(), reservaModel.getCliente().getID(), reservaModel.getQtdPessoas(), reservaModel.getValorEntrada(), reservaModel.getDataReserva(), reservaModel.getDiaCheckIn(), reservaModel.getDiaCheckOut());
    }

    public ReservaModel montaReservaModel(int ID){
        String sql =    "SELECT " +
                        "Cliente.ID AS ClienteID, Cliente.CPF, Cliente.Nome, Cliente.Data_Nascimento, Cliente.Endereco, " +
                        "Quarto.ID AS QuartoID, Quarto.Numero_Quarto, Quarto.Numero_Andar, Quarto.Ramal, " +
                        "Tipo_Quarto.ID AS TipoQuartoID, Tipo_Quarto.Nome AS TipoQuartoNome, Tipo_Quarto.Quantidade_Camas, Tipo_Quarto.Valor_Diaria, Tipo_Quarto.Descricao, " +
                        "Reserva.ID AS ReservaID, Reserva.Qtd_Pessoas, Reserva.Valor_Entrada, Reserva.Data_Reserva, Reserva.Dia_CheckIn, Reserva.Dia_CheckOut " +
                        "FROM Reserva " +
                        "INNER JOIN Cliente ON Reserva.Cliente_ID = Cliente.ID " +
                        "INNER JOIN Quarto ON Reserva.Quarto_ID = Quarto.ID " +
                        "INNER JOIN Tipo_Quarto ON Quarto.TipoQuarto_ID = Tipo_Quarto.ID WHERE Reserva.ID = ?";
        return montaListaModelReserva(sql, ID).get(0);
    }

    public boolean modificarReserva(int ID, ReservaModel reservaModel){
        String sql = "UPDATE Reserva SET Quarto_ID = ?, Cliente_ID = ?, Qtd_Pessoas = ?, Valor_Entrada = ?, Data_Reserva = ?, Dia_CheckIn = ?, Dia_CheckOut = ? WHERE ID = ?";
        return executarOperacao(sql, reservaModel.getQuarto().getID(), reservaModel.getCliente().getID(), reservaModel.getQtdPessoas(), reservaModel.getValorEntrada(), reservaModel.getDataReserva(), reservaModel.getDiaCheckIn(), reservaModel.getDiaCheckOut(), ID);
    }
}