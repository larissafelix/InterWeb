package servicoImpl;

import java.util.List;

import javax.persistence.Query;

import dao.ItemDao;
import dao.DaoFactory;
import dao.jpa.EM;
import dominio.Item;
import dominio.Pedido;
import servico.ItemServico;

public class ItemServicoImpl implements ItemServico {
	
	// como a camada de servi�o usa a camada de acesso a dados Dao, ent�o a classe
	//Item servi�o vai ter dependencia para a classe Item Dao
	private ItemDao dao;
	
	//m�todo construtor
	public ItemServicoImpl() {
		dao = DaoFactory.criarItemDao();
	}

	@Override
	public void inserirAtualizar(Item x) {
		//EM CASO DE EXCE��O DE NEG�CIO SERIA AQUI REALIZADA A EXCE��O
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Item x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Item buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<Item> buscarTodos() {
		return dao.buscarTodos();
	}
	
}
