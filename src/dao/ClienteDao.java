package dao;

import java.util.List;
import dominio.Cliente;

public interface ClienteDao {

	public void inserirAtualizar(Cliente x);
	public void deletar(Cliente x);
	public Cliente buscar(int cod);
	public List<Cliente> buscarTodos();
	public Cliente buscarClienteFone(String numero);
	
}
