package servicoImpl;

import java.util.List;
import dao.ProdutoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Produto;
import servico.ProdutoServico;
import servico.ServicoException;

public class ProdutoServicoImpl implements ProdutoServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Produto servi�o vai ter dependencia para a classe Produto Dao
	private ProdutoDao dao;
	
	//m�todo construtor
	public ProdutoServicoImpl() {
		dao = DaoFactory.criarProdutoDao();
	}

	@Override
	public void inserirProduto(Produto x)throws ServicoException {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		ProdutoDao ProdutoDao = DaoFactory.criarProdutoDao();
		List<Produto> produto = ProdutoDao.buscarTodos();
		
		for(Produto reg: produto){
			if(x.getNomeProduto().equals(reg.getNomeProduto())){
				throw new ServicoException("Produto j� cadasatrado", 1);
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
			throw new ServicoException("O produto n�o foi encontrado.", 1);
		}
		return prod;
	}
}
