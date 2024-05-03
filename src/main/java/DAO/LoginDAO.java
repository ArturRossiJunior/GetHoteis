package DAO;

public class LoginDAO extends PadraoDAO {

    public boolean login(String email, String senha) {
        conexao = ConexaoDAO.conectar();
        String query = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";
        return executarConsulta(query, email, senha);
    }
}