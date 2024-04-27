package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Hotelaria;encrypt=true;trustServerCertificate=true", "Funcionario", "123456");
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return conexao;
    }
}