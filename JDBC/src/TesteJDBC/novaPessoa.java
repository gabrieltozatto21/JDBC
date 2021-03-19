package TesteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class novaPessoa {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		Connection conexao = FabricaConexao.getConectar();
		
		String nome;
		
		System.out.print("Insira o nome: ");
		nome = scan.nextLine();
		 
		String Sql = "INSERT INTO pessoas (nome) VALUES ( ? )";
		PreparedStatement stmt = conexao.prepareStatement(Sql);
		
		stmt.setString(1, nome);
		
		
		stmt.execute();		
		
		System.out.println("Pessoas adicionada com sucesso!");
	}

}
