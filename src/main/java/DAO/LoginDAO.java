package DAO;

public class LoginDAO extends PadraoDAO {

    public boolean login(String usuario, String senha) {
        String query = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";
        return executarConsulta(query, usuario, senha);
    }
}