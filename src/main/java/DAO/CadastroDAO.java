package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Models.CadastroModel;

public class CadastroDAO {

    private Connection conexao;

    public CadastroDAO() {
        this.conexao = ConexaoDAO.conectar();
    }

    public boolean inserirUsuario(CadastroModel usuario) {
        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";
        
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
            return false;
        }
    }
}