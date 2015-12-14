package servicoImpl;

import java.util.List;
import java.util.Scanner;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Cliente;
import servico.ClienteServico;
import servico.ServicoException;

public class ClienteServicoImpl implements ClienteServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Cliente servi�o vai ter dependencia para a classe Cliente Dao
	private ClienteDao dao;
	
	//m�todo construtor
	public ClienteServicoImpl() {
		dao = DaoFactory.criarClienteDao();
	}

	@Override
	public void inserirCliente(Cliente x) throws ServicoException{
	
		Scanner sc = new Scanner(System.in);
		//PERCORRER A LISTA DE CLIENTES E CONSULTAR CADASTRADO
		
		List<Cliente> cliente = dao.buscarTodos();
		
		//contar o tamanho da lista
		int tamList = cliente.size();
		int cont = 0;
				
		
		for(Cliente cli: cliente){
			
			cont ++;
			//Validar se o cliente j� est� cadastrado com o telefone
			if(cli.getNome().equals(x.getNome()) && cli.getTelefone().equals(x.getTelefone())){
				throw new ServicoException("Telefone j� cadastrado para esse cliente", 1);
			}
			//Validar se o telefone j� est� cadastrado
			else if(cli.getTelefone().equals(x.getTelefone())){
				System.out.println("O telefone j� est� cadastrado para o cliente " + cli.getNome() + 
				"\ndeseja subistiuir para o nome " + x.getNome() + "?\nDigite 1-Sim ou 2-N�o");
				int opcli = Integer.parseInt(sc.nextLine());
				//tratar vazio
				while(opcli != 1 && opcli != 2){
					System.out.println("Op��o Inv�lida, digite 1-Sim ou 2-N�o");
					opcli = Integer.parseInt(sc.nextLine());
				}
				if(opcli == 2){
					throw new ServicoException("O telefone n�o foi alterado.", 1);	
				}else if(opcli == 1){
					//Criei novo m�todo com envio de duas variaveis
					this.atualizar(x, cli);
					break;
				}
			}//Se cont for do tamanho da lista e n�o houver nenhuma das condi��es acima.
			else if(cont == tamList){
				EM.getLocalEm().getTransaction().begin();
				dao.inserirAtualizar(x);
				EM.getLocalEm().getTransaction().commit();
			}
		}
	}

	@Override
	public void deletar(Cliente x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Cliente buscar(int cod) {
		
		return dao.buscar(cod);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return dao.buscarTodos();
	}
	
	//M�todo criado para atualizar o cliente caso o telefone for igual ao do banco.
	public void atualizar(Cliente x, Cliente y){
		
		Cliente client = null;
		client = new Cliente(y.getCodCliente(), x.getNome(), y.getTelefone(), null);
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(client);
		EM.getLocalEm().getTransaction().commit();
	}
	
	
	//M�todo que busca o cliente por telefone
	public Cliente buscarCliTelefone(String fone)throws ServicoException{

		Cliente cliente = dao.buscarClienteFone(fone);
		if(cliente == null){
			throw new ServicoException("O cliente n�o foi encontrado.", 1);
		}
		return cliente;
		
	}
}
