package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    protected String consultarCampo(String sql, String email) {
        try (Connection conexao = ConexaoDAO.conectar(); 
                PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);
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
}