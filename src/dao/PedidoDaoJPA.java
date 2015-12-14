package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Atendente;
import dominio.Pedido;




public class PedidoDaoJPA implements PedidoDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public PedidoDaoJPA() {
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Pedido x){
		if(x.getCodPedido() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Pedido x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Pedido buscar(int cod) {
		return em.find(Pedido.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> buscarTodos() {
		String s = "SELECT x FROM Pedido x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> buscarPedidoPeriodo(java.util.Date pInicio, java.util.Date pFim){
		// select d from Dog d where d.dateOfBirth between :startDate and :endDate
		String s = "SELECT x FROM Pedido x WHERE data >= :inicio and data <= :fim";
        Query query = em.createQuery(s);
        query.setParameter("inicio", pInicio);
        query.setParameter("fim", pFim);
		return query.getResultList();
	}
}
