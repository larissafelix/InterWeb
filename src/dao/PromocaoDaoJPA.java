package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Pedido;
import dominio.Promocao;


public class PromocaoDaoJPA implements PromocaoDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public PromocaoDaoJPA() {
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Promocao x){
		if(x.getCodPromocao() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Promocao x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Promocao buscar(int cod) {
		return em.find(Promocao.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> buscarTodos() {
		String s = "SELECT x FROM Promocao x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Promocao> totalDescontoAplicado(java.util.Date pInicio, java.util.Date pFim){
		// select d from Dog d where d.dateOfBirth between :startDate and :endDate
		String s = "SELECT x FROM Promocao x WHERE dataInicio >= :inicio and dataFim <= :fim";
        Query query = em.createQuery(s);
        query.setParameter("inicio", pInicio);
        query.setParameter("fim", pFim);
		return query.getResultList();
	}

}
