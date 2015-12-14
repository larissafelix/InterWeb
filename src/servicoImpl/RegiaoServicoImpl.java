package servicoImpl;

import java.util.Date;
import java.util.List;

import dao.RegiaoDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Pedido;
import dominio.Regiao;
import servico.RegiaoServico;
import servico.ServicoException;

public class RegiaoServicoImpl implements RegiaoServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Regiao servi�o vai ter dependencia para a classe Regiao Dao
	private RegiaoDao dao;
	
	//m�todo construtor
	public RegiaoServicoImpl() {
		dao = DaoFactory.criarRegiaoDao();
	}

	@Override
	public void inserirRegiao(Regiao x)throws ServicoException {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		
		
		RegiaoDao regiaoDao = DaoFactory.criarRegiaoDao();
		List<Regiao> regiao = regiaoDao.buscarTodos();
		
		for(Regiao reg: regiao){
			if(x.getNomeRegiao().equals(reg.getNomeRegiao())){
				throw new ServicoException("Regiao j� cadasatrada", 2);
			}
		}
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Regiao x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Regiao buscar(int cod) {
		
		if(dao.buscar(cod).equals(cod)){
			System.out.println("Encontrado");
		}
		return dao.buscar(cod);
	}

	@Override
	public List<Regiao> buscarTodos() {
		return dao.buscarTodos();
	}
}
