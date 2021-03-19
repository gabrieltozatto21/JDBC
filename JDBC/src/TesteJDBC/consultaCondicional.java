package TesteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;

public class consultaCondicional {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		Connection conectar = FabricaConexao.getConectar();
		String nome;
		
		System.out.print("Nome: ");
		nome = in.nextLine();
		
		 String sql = "SELECT*FROM pessoas WHERE nome LIKE ?";
		 PreparedStatement stmt = conectar.prepareStatement(sql);
		 
		 stmt.setString(1, "%"+nome+"%");
		 stmt.execute();
		 
		 ResultSet consulta = stmt.executeQuery();
		 
		 List<pessoas> pes = new ArrayList<pessoas>();
		 
		 while(consulta.next()) {
			 pessoas p = new pessoas();
			 p.setCodigo(consulta.getInt("codigo"));
			 p.setNome(consulta.getString("nome"));
			 
			 pes.add(p);
		 }
		 
		 System.out.println("Código \t Nome \n");
			for(pessoas p: pes) {
				System.out.println("  "+p.getCodigo()+"\t"+ p.getNome()+"\n");
			}
			
			stmt.close();
			conectar.close();

	}

}
