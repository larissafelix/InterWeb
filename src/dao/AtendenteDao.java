package dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import dominio.Atendente;

public interface AtendenteDao {
	
	public void inserirAtualizar(Atendente x);
	public void deletar(Atendente x);
	public Atendente buscar(int cod);
	public List<Atendente> buscarTodos();
	public BigDecimal vendasPorPeriodo(Integer codAtend, Date dataInicial, Date dataFinal);
}
