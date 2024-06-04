package DAO;

import java.sql.*;
import java.util.*;
import Models.*;

public abstract class PadraoDAO {

    private void setParametros(PreparedStatement stmt, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }
    }

    protected boolean executarOperacao(String sql, Object... parametros) {
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            if (sql.trim().toLowerCase().startsWith("select")) {
                try (ResultSet resultSet = stmt.executeQuery()) {
                    return resultSet.next();
                }
            } else {
                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar operação: " + e.getMessage());
            return false;
        }
    }

    protected String consultarCampo(String sql, String campo) {
        try (Connection conexao = ConexaoDAO.conectar(); 
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, campo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1); 
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected List<TipoQuartoModel> montaListaModelTipoQuarto(String sql, Object... parametros) {
        List<TipoQuartoModel> tiposDeQuarto = new ArrayList<>();
        try (Connection conexao = ConexaoDAO.conectar(); 
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoQuartoModel tipoQuartoModel = new TipoQuartoModel(
                    rs.getString("Nome"),
                    rs.getInt("Quantidade_Camas"),
                    rs.getDouble("Valor_Diaria"),
                    rs.getString("Descricao")
                );
                tipoQuartoModel.setID(rs.getInt("ID"));
                tiposDeQuarto.add(tipoQuartoModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDeQuarto;
    }

    protected List<QuartoModel> montaListaModelQuartos(String sql, Object... parametros) {
        List<QuartoModel> quartos = new ArrayList<>();
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoQuartoModel tipoQuarto = new TipoQuartoModel(
                        rs.getString("Nome"),
                        rs.getInt("Quantidade_Camas"),
                        rs.getDouble("Valor_Diaria"),
                        rs.getString("Descricao")
                );
                QuartoModel quarto = new QuartoModel(
                        rs.getInt("Numero_Quarto"),
                        tipoQuarto
                );
                tipoQuarto.setID(rs.getInt("ID"));
                quarto.setID(rs.getInt("QuartoID"));
                quartos.add(quarto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quartos;
    }

    protected List<ClienteModel> montaListaModelCliente(String sql, Object... parametros) {
        List<ClienteModel> clientes = new ArrayList<>();
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteModel clienteModel = new ClienteModel(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Data_Nascimento"),
                    rs.getString("Endereco")
                );
                clienteModel.setID(rs.getInt("ID"));
                clientes.add(clienteModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    protected List<UsuarioModel> montaListaModelUsuario(String sql, Object... parametros){
        List<UsuarioModel> usuarios = new ArrayList<>();
        try (Connection conexao = ConexaoDAO.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UsuarioModel usuarioModel = new UsuarioModel(
                    rs.getString("CPF"), 
                    rs.getString("Nome_Completo"), 
                    rs.getString("Data_Nascimento"), 
                    rs.getString("Email"), 
                    rs.getString("Senha"),
                    rs.getString("Pergunta_Seguranca"),
                    rs.getString("Resposta")
                    );
                usuarioModel.setID(rs.getInt("ID"));
                usuarios.add(usuarioModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    protected List<ReservaModel> montaListaModelReserva(String sql, Object... parametros) {
        List<ReservaModel> reservas = new ArrayList<>();
        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            setParametros(stmt, parametros);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoQuartoModel tipoQuarto = new TipoQuartoModel(
                    rs.getString("TipoQuartoNome"),
                    rs.getInt("Quantidade_Camas"),
                    rs.getDouble("Valor_Diaria"),
                    rs.getString("Descricao")
                );
                tipoQuarto.setID(rs.getInt("TipoQuartoID"));
                QuartoModel quarto = new QuartoModel(
                    rs.getInt("Numero_Quarto"),
                    tipoQuarto
                );
                quarto.setID(rs.getInt("QuartoID"));
                ClienteModel cliente = new ClienteModel(
                    rs.getString("CPF"),
                    rs.getString("Nome"),
                    rs.getString("Data_Nascimento"),
                    rs.getString("Endereco")
                );
                cliente.setID(rs.getInt("ClienteID"));
                ReservaModel reserva = new ReservaModel(
                    quarto,
                    cliente,
                    rs.getInt("Qtd_Pessoas"),
                    rs.getDouble("Valor_Entrada"),
                    rs.getString("Data_Reserva"),
                    rs.getString("Dia_CheckIn"),
                    rs.getString("Dia_CheckOut")
                );
                reserva.setID(rs.getInt("ReservaID"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

protected Map<String, Integer> consultarReservasPorMes(String sql) {
        Map<String, Integer> reservasPorMes = new HashMap<>();
        try (Connection conexao = ConexaoDAO.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String mes = rs.getString("mes");
                int quantidade = rs.getInt("quantidade");
                reservasPorMes.put(mes, quantidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservasPorMes;
    }    
}