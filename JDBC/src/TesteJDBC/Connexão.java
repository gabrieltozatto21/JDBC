package TesteJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexão {

	public static void main(String[] args) throws SQLException {
		
	        String URL   = "jdbc:mysql://localhost:3306";
	        String USER  = "root";
	        String PASS  = "larissa";
	        
	        Connection conexao = DriverManager.getConnection(URL, USER, PASS);
	        
	        System.out.println("Coneão efetuada com sucesso!");
	        conexao.close();
	}

}
