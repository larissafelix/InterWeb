package servico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dominio.Promocao;

public interface PromocaoServico {

	public void inserirPromocao(Promocao x)throws ServicoException;
	public void deletar(Promocao x);
	public Promocao buscar(int cod);
	public List<Promocao> buscarTodos();
	public BigDecimal totalDescontoAplicado(Date promocaoInicio, Date promocaoFinal);
	
}
