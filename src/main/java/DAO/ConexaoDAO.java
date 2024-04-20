package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=master;integratedSecurity=true";
    //private static final String USUARIO = "sa";
    //private static final String SENHA = "123456";

    public static Connection obterConexao() {
        try {
            Connection conexao = DriverManager.getConnection(URL);
            //Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar-se ao banco de dados: " + ex.getMessage());
            return null;
        }
    }
}
