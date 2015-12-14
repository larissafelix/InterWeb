package servicoImpl;

import java.util.List;

import dao.PromocaoProdutoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.PromocaoProduto;
import servico.PromocaoProdutoServico;

public class PromocaoProdutoServicoImpl implements PromocaoProdutoServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//PromocaoProduto servi�o vai ter dependencia para a classe PromocaoProduto Dao
	private PromocaoProdutoDao dao;
	
	//m�todo construtor
	public PromocaoProdutoServicoImpl() {
		dao = DaoFactory.criarPromocaoProdutoDao();
	}

	@Override
	public void inserirAtualizar(PromocaoProduto x) {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(PromocaoProduto x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public PromocaoProduto buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<PromocaoProduto> buscarTodos() {
		return dao.buscarTodos();
	}

}
