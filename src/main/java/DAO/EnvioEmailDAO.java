package DAO;

public class EnvioEmailDAO extends PadraoDAO{

    public boolean existeEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE Email = ?";
        return executarOperacao(sql, email);
    }
}