package dao.jpa;

import javax.persistence.EntityManager;

public class EM {
	
	private static ThreadLocal<EntityManager> localEm = new
			ThreadLocal<EntityManager>();
	
	// se ja existir uma EntityManager instanciada, retorna-a. Se nao existir
	// ainda, instancia a EntityManager armazenando-a na thread local e a retorna.
	
	public static synchronized EntityManager getLocalEm() {
		EntityManager em = localEm.get();
		if(em == null){
			em = EMF.get().createEntityManager();
			localEm.set(em);
		}
		return em;
	}
			
	// Fecha a EntityManager da thread local e define a mesma como null
	public static void closeLocalEm() {
		EntityManager em = localEm.get();
		if (em != null) {
			localEm.get().close();
			localEm.set(null);
		}
	}
}
