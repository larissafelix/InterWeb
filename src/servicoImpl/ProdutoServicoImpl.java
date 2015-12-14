package servicoImpl;

import java.util.List;
import dao.ProdutoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Produto;
import servico.ProdutoServico;
import servico.ServicoException;

public class ProdutoServicoImpl implements ProdutoServico {
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Produto serviço vai ter dependencia para a classe Produto Dao
	private ProdutoDao dao;
	
	//método construtor
	public ProdutoServicoImpl() {
		dao = DaoFactory.criarProdutoDao();
	}

	@Override
	public void inserirProduto(Produto x)throws ServicoException {
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO
		
		ProdutoDao ProdutoDao = DaoFactory.criarProdutoDao();
		List<Produto> produto = ProdutoDao.buscarTodos();
		
		for(Produto reg: produto){
			if(x.getNomeProduto().equals(reg.getNomeProduto())){
				throw new ServicoException("Produto já cadasatrado", 1);
			}
		}
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Produto x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Produto buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<Produto> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public Produto buscarProdutoNome(String nome)throws ServicoException{
		Produto prod = dao.buscarProdNome(nome);
		if(prod == null){
			throw new ServicoException("O produto não foi encontrado.", 1);
		}
		return prod;
	}
}
