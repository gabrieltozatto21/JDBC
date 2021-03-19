package TesteJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela {

	public static void main(String[] args) throws SQLException {
		Connection conexao = FabricaConexao.getConectar();
		
		String Sql = "CREATE TABLE IF NOT EXISTS pessoas ("
				+ "codigo INT AUTO_INCREMENT PRIMARY KEY,"
				+ "nome VARCHAR(80) NOT NULL"
				+ ")";
		
		Statement stmt = conexao.createStatement();
		stmt.execute(Sql);
	}

}
