package DAO;

import Models.CadastroModel;

public class CadastroDAO extends PadraoDAO {

    public boolean inserirUsuario(CadastroModel usuario) {
        conexao = ConexaoDAO.conectar();
        String sql = "INSERT INTO Usuarios (nome_completo, email, cpf, data_nascimento, senha) VALUES (?, ?, ?, ?, ?)";
        return executarAtualizacao(sql, usuario.getNomeCompleto(), usuario.getEmail(), usuario.getCPF(), usuario.getDataNascimento(), usuario.getSenha());
    }

    public boolean consultaEmailouCPF(String email, String cpf) {
        conexao = ConexaoDAO.conectar();
        String sql = "SELECT * FROM Usuarios WHERE email = ? OR cpf = ?";
        return executarConsulta(sql, email, cpf);
    }
}