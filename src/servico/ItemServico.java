package servico;

import java.util.List;

import dominio.Item;
import dominio.Pedido;

public interface ItemServico {

	public void inserirAtualizar(Item x);
	public void deletar(Item x);
	public Item buscar(int cod);
	public List<Item> buscarTodos();
}
