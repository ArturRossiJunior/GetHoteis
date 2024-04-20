import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    private static final String URL = "jdbc:mysql://localhost:1433/Hotelaria";
    private static final String USUARIO = "sa";
    private static final String SENHA = "123456";

    public static Connection obterConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }
}
