package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.PromocaoProduto;

public class PromocaoProdutoDaoJPA implements PromocaoProdutoDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public PromocaoProdutoDaoJPA() {
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(PromocaoProduto x){
		if(x.getCodPromocaoProduto() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(PromocaoProduto x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public PromocaoProduto buscar(int cod) {
		return em.find(PromocaoProduto.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<PromocaoProduto> buscarTodos() {
		String s = "SELECT x FROM PromocaoProduto x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
}
