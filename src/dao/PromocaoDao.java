package dao;

import java.math.BigDecimal;
import java.util.List;
import dominio.Promocao;

public interface PromocaoDao {
	
	public void inserirAtualizar(Promocao x);
	public void deletar(Promocao x);
	public Promocao buscar(int cod);
	public List<Promocao> buscarTodos();
	public List<Promocao> totalDescontoAplicado(java.util.Date pInicio, java.util.Date pFim);
}
