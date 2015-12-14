package servico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dominio.Atendente;

public interface AtendenteServico {
	
	//Ao invés de repassar inserir atualizar do serviço para o Inserir atualizar do Dao eu vou 
	//inserir o Album qki mesmo
	
	//public void inserirAtualizar(Atendente x);
	
	// Na regra eu não posso inserir um Atendente com o mesmo nome.
	public void inserirAtendente(Atendente x) throws ServicoException;
	public void deletar(Atendente x);
	public Atendente buscarAtendente(int cod)throws ServicoException;
	public List<Atendente> buscarTodos();
	public BigDecimal vendasPorPeriodo(Integer codAtend, Date dataInicial, Date dataFinal);
}

//
