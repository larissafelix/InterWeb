package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dao.jpa.EM;
import dominio.Produto;



public class ProdutoDaoJPA implements ProdutoDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public ProdutoDaoJPA() {		
		
		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Produto x){
		if(x.getCodProduto() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Produto x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Produto buscar(int cod) {
		return em.find(Produto.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> buscarTodos() {
		String s = "SELECT x FROM Produto x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}
	//Buscar produto pelo nome
	@SuppressWarnings("unchecked")
	public Produto buscarProdNome(String nome) {
		String s = "SELECT x FROM Produto x where nomeProduto = :p1";
		Query query = em.createQuery(s);
		 query.setParameter("p1", nome);
		try{
			return (Produto) query.getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
	}
}
