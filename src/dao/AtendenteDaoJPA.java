package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Atendente;;


public class AtendenteDaoJPA implements AtendenteDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public AtendenteDaoJPA() {
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Atendente x){
		if(x.getCodAtendente() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Atendente x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Atendente buscar(int cod) {
		return em.find(Atendente.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendente> buscarTodos() {
		String s = "SELECT x FROM Atendente x ORDER BY nome";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
	

	public BigDecimal vendasPorPeriodo(Integer codAtend, Date dataInicial, Date dataFinal){
		Atendente atendente = buscar(codAtend);
		BigDecimal totVendas = new BigDecimal("0.00");
		totVendas = atendente.vendasPorPeriodo(dataInicial, dataFinal);
	 	return totVendas;
	   }

}

	


