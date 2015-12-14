package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import dao.jpa.EM;
import dominio.Pedido;
import dominio.Regiao;

public class RegiaoDaoJPA implements RegiaoDao {


	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public RegiaoDaoJPA() {
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Regiao x){
		if(x.getCodRegiao() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Regiao x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Regiao buscar(int cod) {
		return em.find(Regiao.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Regiao> buscarTodos() {
		String s = "SELECT x FROM Regiao x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
}
