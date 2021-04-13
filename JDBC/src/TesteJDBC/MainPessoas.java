package TesteJDBC;

import java.util.List;

import javax.swing.JOptionPane;

public class MainPessoas {

	public static void main(String[] args) {
		padrãoDAO DAO = new padrãoDAO();
		char opcao=' ';

		do{
			opcao = JOptionPane.showInputDialog("Escolha uma opcao:\n"
					+ "1. Inserir pessoa\n"
					+ "2. Pesquisar pessoa\n"
					+ "3. Alterar pessoa\n"
					+ "4. Remover pessoa\n"
					+ "5. Mostrar pessoas\n"
					+ "6. Sair do programa").charAt(0);
			switch(opcao) {
			case '1':
				inserir(DAO);
				break;
			case '2':
				pesquisar(DAO);
				break;
			case '3':
				alterar(DAO);
				break;
			case '4': 
				remover(DAO);
				break;
			case '5': 
				exibirPessoas(DAO);				
				break;
			case '6': 
				JOptionPane.showMessageDialog(null,"Tchau, até mais!");
				break;
			default:
				JOptionPane.showMessageDialog(null, "OpÃ§Ã£o InvÃ¡lida!");
			}

		}while(opcao !='6');

	}

	static void inserir(padrãoDAO dao) {
		String nome;
		nome = JOptionPane.showInputDialog("Digite o nome:");
		if(dao.cadastrarPessoa(nome)> 0) {
			JOptionPane.showMessageDialog(null, "Pessoa adicionada");
		}
	}

	static void pesquisar(padrãoDAO dao) {
		String consulta = JOptionPane.showInputDialog("Digite o nome:");
		List<pessoas> lista = dao.consultaCondicional(consulta);

		if(!lista.isEmpty()) {
			String aux="";
			for(pessoas p: lista) {
				aux += "     "+p.getCodigo()+"           |     "+ p.getNome()+"\n";
			}
			JOptionPane.showMessageDialog(null,"Código     |       Nome \n" + aux);

		}else {
			System.out.println("Não encontrado");
		}
	}

	static void alterar(padrãoDAO dao) {
		pessoas pessoa = new pessoas();
		int codigo, opcao=0;
		String novoNome;
		pesquisar(dao);
		pessoa.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo da pessoa que deseja alterar")));
		codigo = pessoa.getCodigo(); 
		List<pessoas> pes = dao.consultaCod(pessoa);

		for(int i=0; i<pes.size();i++) {
			if(pes.get(i).getCodigo()==codigo) {
				codigo = pes.get(i).getCodigo();
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja alterar a pessoa "+pes.get(i).getNome()));
			}
		}
		if(opcao==1) {
			novoNome = JOptionPane.showInputDialog("Digite o novo nome:");
			dao.atualizaDado(codigo, novoNome);
			JOptionPane.showMessageDialog(null, "Alterado!");
		}else {
			JOptionPane.showMessageDialog(null, "Não alterado!");
		}
	}

	static void remover(padrãoDAO dao) {
		int codigo=0, opcao=0;
		String nome="" ;
		pesquisar(dao);
		codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da pessoa que deseja exlcuir!"));
		List<pessoas> pes = dao.consultaCondicional(nome);

		for(int i=0; i<pes.size();i++) {
			if(pes.get(i).getCodigo() == codigo)  {
				codigo = pes.get(i).getCodigo();
				opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja remover a pessoa "+pes.get(i).getNome()));

			}
		}
		if(opcao==1) {
			dao.excluirDados(codigo);
			JOptionPane.showMessageDialog(null, "removido com sucesso");

		}else {
			JOptionPane.showMessageDialog(null, "Não removido");	
		}

	}

	static void exibirPessoas(padrãoDAO dao) {
		List<pessoas> lista = dao.exibirPessoas();
		if(!lista.isEmpty()) {
			String aux="";
			for(pessoas p: lista) {
				aux += "     "+p.getCodigo()+"           |     "+ p.getNome()+"\n";
			}
			JOptionPane.showMessageDialog(null,"Código     |       Nome \n" + aux);

		}else {
			System.out.println("Não encontrado");
		}
	}

}
