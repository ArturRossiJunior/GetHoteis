package DAO;

import java.sql.*;

public class ConexaoDAO {

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //casa
            conexao = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Hotelaria;encrypt=true;trustServerCertificate=true", "Funcionario", "123456");
            //faculdade
            //conexao = DriverManager.getConnection("jdbc:sqlserver://HPFS-CE-LAB6410;databaseName=Hotelaria;encrypt=true;trustServerCertificate=true", "sa", "123456");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return conexao;
    }
}