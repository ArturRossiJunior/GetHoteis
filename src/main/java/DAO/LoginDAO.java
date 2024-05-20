package DAO;

public class LoginDAO extends PadraoDAO {

    public boolean login(String email, String senha) {
        String query = "SELECT * FROM Usuario WHERE Email = ? AND Senha = ?";
        return executarOperacao(query, email, senha);
    }
}