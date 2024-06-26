package DAO;

import java.util.List;

import Models.*;

public class UsuarioDAO extends PadraoDAO {

    public boolean inserirUsuario(UsuarioModel usuario) {
        String sql = "INSERT INTO Usuario (CPF, Nome_Completo, Data_Nascimento, Email, Senha, Pergunta_Seguranca, Resposta) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return executarOperacao(sql, usuario.getCPF(), usuario.getNomeCompleto(), usuario.getDataNascimento(), usuario.getEmail(), usuario.getSenha(), usuario.getPerguntaSeguranca(), usuario.getResposta());
    }

    public boolean existeEmailouCPF(String email, String cpf) {
        String sql = "SELECT * FROM Usuario WHERE Email = ? OR CPF = ?";
        return executarOperacao(sql, email, cpf);
    }

    public List<UsuarioModel> listaUsuarios() {
        String sql = "SELECT * FROM Usuario";
        return montaListaModelUsuario(sql);
    }
}