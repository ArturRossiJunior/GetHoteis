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

    protected List<QuartoModel> montaListaQuartos(String sql, Object... parametros) {
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
}