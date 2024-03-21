package webapp.contatojdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String url = "jdbc:postgres://localhost:5432/matias";
    private static final String usuario = "postgres"; //Nome do ADM do Banco
    private static final String senha = "postgres";//Senha do adm do banco

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,usuario,senha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados");
        }
    }

    public static void closeConnection (Connection connection){
        try {
            if (connection != null) {
                connection.close();                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Método para fechar a conexão e o objeto PreparedStatement

    public static void closeConnection (Connection connection, PreparedStatement stmt){
        closeConnection(connection);
        try {
            if (stmt != null) {
                stmt.close();               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    

}
