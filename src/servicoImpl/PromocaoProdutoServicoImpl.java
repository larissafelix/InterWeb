package servicoImpl;

import java.util.List;

import dao.PromocaoProdutoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.PromocaoProduto;
import servico.PromocaoProdutoServico;

public class PromocaoProdutoServicoImpl implements PromocaoProdutoServico {
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//PromocaoProduto serviço vai ter dependencia para a classe PromocaoProduto Dao
	private PromocaoProdutoDao dao;
	
	//método construtor
	public PromocaoProdutoServicoImpl() {
		dao = DaoFactory.criarPromocaoProdutoDao();
	}

	@Override
	public void inserirAtualizar(PromocaoProduto x) {
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO
		
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
