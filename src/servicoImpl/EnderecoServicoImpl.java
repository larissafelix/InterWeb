package servicoImpl;

import java.util.List;

import dao.EnderecoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Endereco;
import servico.EnderecoServico;

public class EnderecoServicoImpl implements EnderecoServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Endereco servi�o vai ter dependencia para a classe Endereco Dao
	private EnderecoDao dao;
	
	//m�todo construtor
	public EnderecoServicoImpl() {
		dao = DaoFactory.criarEnderecoDao();
	}

	@Override
	public void inserirAtualizar(Endereco x) {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Endereco x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Endereco buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<Endereco> buscarTodos() {
		return dao.buscarTodos();
	}

}
