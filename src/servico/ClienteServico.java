package servico;

import java.util.List;

import dominio.Cliente;

public interface ClienteServico {

	public void inserirCliente(Cliente x) throws ServicoException;
	public void deletar(Cliente x);
	public Cliente buscar(int cod);
	public List<Cliente> buscarTodos();
	public Cliente buscarCliTelefone(String fone)throws ServicoException;
}
