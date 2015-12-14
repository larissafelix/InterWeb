package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Cliente;


public class ClienteDaoJPA implements ClienteDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;

	public ClienteDaoJPA() {
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Cliente x){
		if(x.getCodCliente() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Cliente x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Cliente buscar(int cod) {
		return em.find(Cliente.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		String s = "SELECT u FROM Cliente u";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
	//Buscar cliente pelo numero do telefone
	@SuppressWarnings("unchecked")
	public Cliente buscarClienteFone(String numero) {
		String s = "SELECT x FROM Cliente x where telefone = :c1";
		Query query = em.createQuery(s);
		 query.setParameter("c1", numero);
		try{
			return (Cliente) query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
	}
}
