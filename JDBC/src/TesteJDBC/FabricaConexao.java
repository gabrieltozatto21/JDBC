package TesteJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {

	public static Connection getConectar() {
		try {
			String URL   = "jdbc:mysql://localhost:3306/testeconexão";
			String USER  = "root";
			String PASS  = "larissa";

			return DriverManager.getConnection(URL, USER, PASS);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
