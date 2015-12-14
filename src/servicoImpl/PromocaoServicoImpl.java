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
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Promocao serviço vai ter dependencia para a classe Promocao Dao
	private PromocaoDao dao;
	
	//método construtor
	public PromocaoServicoImpl() {
		dao = DaoFactory.criarProdPromocaoDao();
	}

	@Override
	public void inserirPromocao(Promocao x)throws ServicoException {
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO
		
		
		//PERCORRER A LISTA DE CLIENTES E CONSULTAR CADASTRADO
		PromocaoDao promocaoDao = DaoFactory.criarProdPromocaoDao();
		List<Promocao> promocao = promocaoDao.buscarTodos();
		
		for(Promocao pro: promocao){
			if(pro.getNomePromocao().equals(x.getNomePromocao())){
				throw new ServicoException("Promoção já cadastrada", 1);
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
