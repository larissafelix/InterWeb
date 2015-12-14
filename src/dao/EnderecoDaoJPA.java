package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import dao.jpa.EM;
import dominio.Endereco;




public class EnderecoDaoJPA implements EnderecoDao {
	
	// Toda operação JPA precisa de um EntityManager, entao precisamos
	// declarar um EntityManager "interno" nesta implementacao do DAO
	private EntityManager em;
	
	public EnderecoDaoJPA() {

		this.em = EM.getLocalEm();
	}
	
	public void inserirAtualizar(Endereco x){
		if(x.getCodEndereco() != null){
			x = em.merge(x);
		}
		em.persist(x);
	}
	
	public void deletar(Endereco x) {
		x = em.merge(x);
		em.remove(x);
	}
	
	public Endereco buscar(int cod) {
		return em.find(Endereco.class, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() {
		String s = "SELECT x FROM Endereco x";
		Query query = em.createQuery(s);
		return query.getResultList();
	}

}
