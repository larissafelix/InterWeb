package servicoImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import dao.AtendenteDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Atendente;
import servico.AtendenteServico;
import servico.ServicoException;

public class AtendenteServicoImpl implements AtendenteServico {
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Atendente serviço vai ter dependencia para a classe Atendente Dao
	private AtendenteDao dao;
	
	//método construtor
	public AtendenteServicoImpl() {
		dao = DaoFactory.criarAtendenteDao();
	}

	@Override
	public void inserirAtendente(Atendente x) throws ServicoException {
		
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO, A REGRA DE NEGÓCIO FICARIA AQUI.
		
		AtendenteDao atendenteDao = DaoFactory.criarAtendenteDao();
		List<Atendente> atendente = atendenteDao.buscarTodos();
		
		for(Atendente atend: atendente){
			if(atend.getNome().equals(x.getNome())){
				throw new ServicoException("Nome de Atendente já existente", 1);
			}
		}
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public void deletar(Atendente x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Atendente buscarAtendente(int cod) throws ServicoException {
		
		if (dao.buscar(cod)==null){
			throw new ServicoException("Atendente não encontrado", 1);
		}
		return dao.buscar(cod);
	}

	@Override
	public List<Atendente> buscarTodos() {
		return dao.buscarTodos();
	}
	public BigDecimal vendasPorPeriodo(Integer codAtend, Date dataInicial, Date dataFinal){
		return dao.vendasPorPeriodo(codAtend,dataInicial, dataFinal);
	}
}
