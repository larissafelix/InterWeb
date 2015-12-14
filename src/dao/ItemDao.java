package dao;

import java.util.List;

import dominio.Item;
import dominio.Pedido;

public interface ItemDao {
	
	public void inserirAtualizar(Item x);
	public void deletar(Item x);
	public Item buscar(int cod);
	public List<Item> buscarTodos();
}
