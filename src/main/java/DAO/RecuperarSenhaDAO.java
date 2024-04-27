package DAO;

public class RecuperarSenhaDAO extends PadraoDAO {

    public boolean recuperarSenha(String email, String senhaAntiga, String senhaNova){
        conexao = ConexaoDAO.conectar();
        String sql = "UPDATE Usuarios SET senha = ? WHERE email = ? and senha = ?";
        return executarAtualizacao(sql, senhaNova, email, senhaAntiga);
    }
}