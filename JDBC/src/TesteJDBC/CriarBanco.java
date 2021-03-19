package TesteJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {

	public static void main(String[] args) throws SQLException {
		/**String URL   = "jdbc:mysql://localhost:3306";
        String USER  = "root";
        String PASS  = "larissa";
        
        Connection conexao = DriverManager.getConnection(URL, USER, PASS);
        **/
		Connection conexao = FabricaConexao.getConectar();
		
        System.out.println("Coneão efetuada com sucesso!");
        
        Statement stmt = conexao.createStatement();
        stmt.execute("Create database testeConexão ");
        
        conexao.close();

	}

}
