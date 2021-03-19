package TesteJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class consultaTodos {

	public static void main(String[] args) throws SQLException {
		Connection conectar = FabricaConexao.getConectar();

		String sql = "SELECT*FROM pessoas";
		Statement stmt = conectar.createStatement();
		ResultSet consulta = stmt.executeQuery(sql);
		
		List<pessoas> pessoas = new ArrayList<pessoas>(); 
		
		while(consulta.next()) {
			pessoas p = new pessoas();
			p.setCodigo(consulta.getInt("codigo"));
			p.setNome(consulta.getString("nome"));
			
			pessoas.add(p);
			
		}
		System.out.println("Código \t Nome \n");
		for(pessoas p: pessoas) {
			System.out.println("  "+p.getCodigo()+"\t"+ p.getNome()+"\n");
		}
		
		stmt.close();
		conectar.close();
		
	}

}
