package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PadraoDAO {

    protected Connection conexao;

    public PadraoDAO() {
        this.conexao = ConexaoDAO.conectar();
    }

    private void setParametros(PreparedStatement stmt, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }
    }

    protected boolean executarAtualizacao(String sql, Object... parametros) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            setParametros(stmt, parametros);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao executar atualização: " + e.getMessage());
            return false;
        }
    }

    protected boolean executarConsulta(String sql, Object... parametros) {
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            setParametros(preparedStatement, parametros);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta: " + e.getMessage());
            return false;
        }
    }
}