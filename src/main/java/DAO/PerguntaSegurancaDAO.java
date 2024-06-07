package DAO;

public class PerguntaSegurancaDAO extends PadraoDAO {

    public boolean reuperarSenhaPerguntaSeguranca(String email, String senhaNova, String perguntaSeguranca, String resposta){
        String sql = "UPDATE Usuario SET Senha = ? WHERE Email = ? and Pergunta_Seguranca = ? and Resposta = ?";
        return executarOperacao(sql, senhaNova, email, perguntaSeguranca, resposta);
    }

    public String consultaEmail(String campo, String email){
        String sql = "SELECT " + campo + " FROM Usuario WHERE Email = ?";
        return consultarCampo(sql, email);
    }
}