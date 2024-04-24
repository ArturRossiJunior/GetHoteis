package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Hotelaria;encrypt=true;trustServerCertificate=true";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conexao = DriverManager.getConnection(URL, "Funcionario", "123456");

            System.out.println("Conexão estabelecida com sucesso.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }    
}
