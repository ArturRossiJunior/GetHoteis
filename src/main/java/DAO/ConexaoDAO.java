package DAO;

import java.sql.*;

public class ConexaoDAO {

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(
                "jdbc:sqlserver://gethoteis.database.windows.net:1433;databaseName=Hotelaria;encrypt=true;trustServerCertificate=false",
                "azureuser",
                "Gethoteis@1"
            );
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return conexao;
    }
}