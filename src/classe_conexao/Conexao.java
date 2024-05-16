package classe_conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection faz_conexao() throws SQLException {
        try {
            // Corrigido o nome do driver JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Corrigido a URL de conexão para evitar problemas com versões mais recentes do MySQL
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/wordpress", "root", "pass");
            
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
