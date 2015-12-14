package servicoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.PromocaoDao;
import dao.jpa.EM;
import dominio.Cliente;
import dominio.Promocao;
import dominio.PromocaoProduto;
import servico.PromocaoServico;
import servico.ServicoException;

public class PromocaoServicoImpl implements PromocaoServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Promocao servi�o vai ter dependencia para a classe Promocao Dao
	private PromocaoDao dao;
	
	//m�todo construtor
	public PromocaoServicoImpl() {
		dao = DaoFactory.criarProdPromocaoDao();
	}

	@Override
	public void inserirPromocao(Promocao x)throws ServicoException {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		
		//PERCORRER A LISTA DE CLIENTES E CONSULTAR CADASTRADO
		PromocaoDao promocaoDao = DaoFactory.criarProdPromocaoDao();
		List<Promocao> promocao = promocaoDao.buscarTodos();
		
		for(Promocao pro: promocao){
			if(pro.getNomePromocao().equals(x.getNomePromocao())){
				throw new ServicoException("Promo��o j� cadastrada", 1);
			}
		}
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Promocao x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Promocao buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<Promocao> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public BigDecimal totalDescontoAplicado(Date promocaoInicio, Date promocaoFinal){
		BigDecimal soma = new BigDecimal("0.00");
		List<Promocao> promocao= new ArrayList<>();
		promocao = dao.totalDescontoAplicado(promocaoInicio, promocaoFinal);
		for(Promocao x: promocao){
			soma = soma.add(x.totalDescontoAplicado());
		}
		return soma;
	}
}
