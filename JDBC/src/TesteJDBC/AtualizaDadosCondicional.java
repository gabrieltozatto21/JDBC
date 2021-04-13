package TesteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AtualizaDadosCondicional {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		Connection conectar = FabricaConexao.getConectar();
		boolean verifica = false;
		int codigo=0;
		String nome, novoNome;

		System.out.print("Nome que deseja alterar: ");
		nome = in.nextLine();

		String sqlPesquisa = "SELECT*FROM pessoas WHERE nome LIKE ?";
		PreparedStatement stmt = conectar.prepareStatement(sqlPesquisa);

		stmt.setString(1, "%"+nome+"%");
		stmt.execute();

		ResultSet consulta = stmt.executeQuery();

		ArrayList<pessoas> pes = new ArrayList<pessoas>();

		while(consulta.next()) {
			pessoas p = new pessoas();
			p.setCodigo(consulta.getInt("codigo"));
			p.setNome(consulta.getString("nome"));

			pes.add(p);
		}

		//percorrendo lista com foreach
		/**for(pessoas lista : pes) {
			if(lista.getNome().equalsIgnoreCase(nome)) {
				codigo= lista.getCodigo();
				System.out.print("Novo nome: ");
				novoNome = in.nextLine();
				String sqlAltera = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
				stmt = conectar.prepareStatement(sqlAltera);

				stmt.setString(1, novoNome);
				stmt.setInt(2, codigo);
				stmt.execute();
				System.out.println("Alterado com Sucesso!");
			}
		}**/
		//percorrendo lista com for
		for(int i=0; i<pes.size();i++) {
			if(pes.get(i).getNome().equalsIgnoreCase(nome)) {
				codigo = pes.get(i).getCodigo();
				System.out.print("Novo nome: ");
				novoNome = in.nextLine();
				String sqlAltera = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
				stmt = conectar.prepareStatement(sqlAltera);

				stmt.setString(1, novoNome);
				stmt.setInt(2, codigo);
				stmt.execute();
				System.out.println("Alterado com Sucesso!");
				verifica=true;
			}else {
				verifica = false;
			}
		}
		if(!verifica) {
			System.out.println("não encontrado!");
		}

		conectar.close();



	}

}
