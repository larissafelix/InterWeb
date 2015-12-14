package servico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import dominio.Pedido;

public interface PedidoServico {

	public void inserirAtualizar(Pedido x);
	public void deletar(Pedido x);
	public Pedido buscar(int cod);
	public List<Pedido> buscarTodos();
	public Pedido buscarPedido(int codCli);
	public List<Pedido> buscarPedidoPeriodo(java.util.Date pInicio, java.util.Date pFim);
	public BigDecimal TotalVendasPeriodo(java.util.Date pInicioTot, java.util.Date pFimTot );
	public void pizzaMaisVendida (Date pInicioPizza, Date pFimPizza);
	public List<Pedido> buscarPedidoCodPeriodo(Integer cod, java.util.Date inicio, java.util.Date fim);
	
}
