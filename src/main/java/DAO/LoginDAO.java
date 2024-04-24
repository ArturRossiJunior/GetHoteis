package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean login(String usuario, String senha) {
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        
        try (Connection connection = ConexaoDAO.conectar();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            return resultSet.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Aqui você pode adicionar outros métodos DAO, como para inserir, atualizar ou excluir usuários
}
