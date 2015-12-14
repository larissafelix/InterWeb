package dao;

import java.util.Date;
import java.util.List;

import dominio.Pedido;
import dominio.Regiao;

public interface RegiaoDao {
	
	public void inserirAtualizar(Regiao x);
	public void deletar(Regiao x);
	public Regiao buscar(int cod);
	public List<Regiao> buscarTodos();
}
