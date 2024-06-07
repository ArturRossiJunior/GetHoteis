package DAO;

import Models.*;

public class ClienteDAO extends PadraoDAO{

    public boolean inserirCliente(ClienteModel clienteModel) {
        String sql = "INSERT INTO Cliente (CPF, Nome, Data_Nascimento, Endereco) VALUES (?, ?, ?, ?)";
        return executarOperacao(sql, clienteModel.getCpf(), clienteModel.getNome(), clienteModel.getDataNascimento(), clienteModel.getEndereco());
    }

    public ClienteModel montaClienteModel(int ID){
        String sql = "SELECT * FROM Cliente WHERE ID = ?";
        return montaListaModelCliente(sql, ID).get(0);
    }

    public boolean modificarCliente(int ID, ClienteModel clienteModel){
        String sql = "UPDATE Cliente SET CPF = ?, Nome = ?, Data_Nascimento = ?, Endereco = ? WHERE ID = ?";
        return executarOperacao(sql, clienteModel.getCpf(), clienteModel.getNome(), clienteModel.getDataNascimento(), clienteModel.getEndereco(), ID);
    }

    public boolean consultaCPFCliente(String cpf){
        String sql = "SELECT CPF FROM Cliente WHERE CPF = ?";
        return executarOperacao(sql, cpf);
    }
}