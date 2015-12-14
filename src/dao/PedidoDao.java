package dao;

import java.math.BigDecimal;
import java.util.List;

import dominio.Pedido;

public interface PedidoDao {
	
	public void inserirAtualizar(Pedido x);
	public void deletar(Pedido x);
	public Pedido buscar(int cod);
	public List<Pedido> buscarTodos();
	public List<Pedido> buscarPedidoPeriodo(java.util.Date pInicio, java.util.Date pFim	);
}
