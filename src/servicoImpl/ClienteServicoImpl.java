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
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Cliente serviço vai ter dependencia para a classe Cliente Dao
	private ClienteDao dao;
	
	//método construtor
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
			//Validar se o cliente já está cadastrado com o telefone
			if(cli.getNome().equals(x.getNome()) && cli.getTelefone().equals(x.getTelefone())){
				throw new ServicoException("Telefone já cadastrado para esse cliente", 1);
			}
			//Validar se o telefone já está cadastrado
			else if(cli.getTelefone().equals(x.getTelefone())){
				System.out.println("O telefone já está cadastrado para o cliente " + cli.getNome() + 
				"\ndeseja subistiuir para o nome " + x.getNome() + "?\nDigite 1-Sim ou 2-Não");
				int opcli = Integer.parseInt(sc.nextLine());
				//tratar vazio
				while(opcli != 1 && opcli != 2){
					System.out.println("Opção Inválida, digite 1-Sim ou 2-Não");
					opcli = Integer.parseInt(sc.nextLine());
				}
				if(opcli == 2){
					throw new ServicoException("O telefone não foi alterado.", 1);	
				}else if(opcli == 1){
					//Criei novo método com envio de duas variaveis
					this.atualizar(x, cli);
					break;
				}
			}//Se cont for do tamanho da lista e não houver nenhuma das condições acima.
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
	
	//Método criado para atualizar o cliente caso o telefone for igual ao do banco.
	public void atualizar(Cliente x, Cliente y){
		
		Cliente client = null;
		client = new Cliente(y.getCodCliente(), x.getNome(), y.getTelefone(), null);
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(client);
		EM.getLocalEm().getTransaction().commit();
	}
	
	
	//Método que busca o cliente por telefone
	public Cliente buscarCliTelefone(String fone)throws ServicoException{

		Cliente cliente = dao.buscarClienteFone(fone);
		if(cliente == null){
			throw new ServicoException("O cliente não foi encontrado.", 1);
		}
		return cliente;
		
	}
}
