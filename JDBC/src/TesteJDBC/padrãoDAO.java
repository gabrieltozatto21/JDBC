package TesteJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class padrãoDAO {

	private Connection conexao;

	public  padrãoDAO() {
		FabricaConexao conectar = new FabricaConexao();
		this.conexao = conectar.getConectar();
	}

	public int cadastrarPessoa(String nome) {
		try {
			String Sql = "INSERT INTO pessoas (nome) VALUES ( ? )";
			PreparedStatement stmt = conexao.prepareStatement(Sql);

			stmt.setString(1, nome);
			int aux = stmt.executeUpdate();

			stmt.close();
			return aux;
	

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<pessoas> consultaCondicional(String nome) {
		try {
			String sql = "SELECT*FROM pessoas WHERE nome LIKE ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

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
			consulta.close();
			stmt.close();

			return pes;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<pessoas> consultaCod(pessoas pessoa) {
		try {
			String sql = "SELECT*FROM pessoas WHERE codigo = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, pessoa.getCodigo());
			stmt.execute();


			ResultSet consulta = stmt.executeQuery();

			List<pessoas> pes = new ArrayList<pessoas>();

			while(consulta.next()) {
				pessoas p = new pessoas();
				p.setCodigo(consulta.getInt("codigo"));
				p.setNome(consulta.getString("nome"));

				pes.add(p);
			}
			consulta.close();
			stmt.close();

			return pes;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int excluirDados(int codigo) {
		try {
			String sqlExclui = "DELETE FROM pessoas WHERE codigo = ?";
			PreparedStatement stmt = conexao.prepareStatement(sqlExclui);
			stmt.setInt(1, codigo);

			//executeUpdate -> retorna o numero de linhas afetadas pelo comando no banco de dados
			int aux = stmt.executeUpdate();
			stmt.close();
			return aux;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizaDado(int codigo, String novoNome) {
		try {
			String sqlAltera = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
			PreparedStatement stmt = conexao.prepareStatement(sqlAltera);;

			stmt.setString(1, novoNome);
			stmt.setInt(2, codigo);
			stmt.execute();

			stmt.close();

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<pessoas> exibirPessoas(){
		try {
			String sql = "SELECT*FROM pessoas";
			Statement stmt = conexao.createStatement();
			ResultSet consulta = stmt.executeQuery(sql);

			List<pessoas> pessoas = new ArrayList<pessoas>(); 

			while(consulta.next()) {
				pessoas p = new pessoas();
				p.setCodigo(consulta.getInt("codigo"));
				p.setNome(consulta.getString("nome"));

				pessoas.add(p);

			}
			stmt.close();

			return pessoas;

		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void close() {
		try {
			this.conexao.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro: "+ex);
		}
	}
}
