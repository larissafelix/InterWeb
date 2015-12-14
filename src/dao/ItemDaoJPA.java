package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Item;
import dominio.Pedido;

public class ItemDaoJPA implements ItemDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	private ProdutoDao dao;
	
	public ItemDaoJPA() {

		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Item x){
		if(x.getCodItem() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Item x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Item buscar(int cod) {
		return em.find(Item.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> buscarTodos() {
		String s = "SELECT x FROM Item x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
}
