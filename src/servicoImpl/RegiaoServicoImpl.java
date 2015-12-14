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
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Regiao serviço vai ter dependencia para a classe Regiao Dao
	private RegiaoDao dao;
	
	//método construtor
	public RegiaoServicoImpl() {
		dao = DaoFactory.criarRegiaoDao();
	}

	@Override
	public void inserirRegiao(Regiao x)throws ServicoException {
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO
		
		
		
		RegiaoDao regiaoDao = DaoFactory.criarRegiaoDao();
		List<Regiao> regiao = regiaoDao.buscarTodos();
		
		for(Regiao reg: regiao){
			if(x.getNomeRegiao().equals(reg.getNomeRegiao())){
				throw new ServicoException("Regiao já cadasatrada", 2);
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
