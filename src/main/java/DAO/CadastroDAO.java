package DAO;

import Models.CadastroModel;

public class CadastroDAO extends PadraoDAO {

    public boolean inserirUsuario(CadastroModel usuario) {
        String sql = "INSERT INTO Usuarios (nome_completo, email, cpf, data_nascimento, senha) VALUES (?, ?, ?, ?, ?)";
        return executarAtualizacao(sql, usuario.getNomeCompleto(), usuario.getEmail(), usuario.getCPF(), usuario.getDataNascimento(), usuario.getSenha());
    }

    public boolean emailOuCpfExiste(String email, String cpf) {
        String sql = "SELECT * FROM Usuarios WHERE email = ? OR cpf = ?";
        return executarConsulta(sql, email, cpf);
    }
}