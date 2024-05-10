package DAO;

import Models.CadastroModel;

public class CadastroDAO extends PadraoDAO {

    public boolean inserirUsuario(CadastroModel usuario) {
        String sql = "INSERT INTO Usuario (CPF, Nome_Completo, Data_Nascimento, Email, Senha, Pergunta_Seguranca, Resposta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executarOperacao(sql, usuario.getCPF(), usuario.getNomeCompleto(), usuario.getDataNascimento(), usuario.getEmail(), usuario.getSenha(), usuario.getPerguntaSeguranca(), usuario.getResposta());
    }

    public boolean consultaEmailouCPF(String email, String cpf) {
        String sql = "SELECT * FROM Usuario WHERE Email = ? OR CPF = ?";
        return executarOperacao(sql, email, cpf);
    }
}