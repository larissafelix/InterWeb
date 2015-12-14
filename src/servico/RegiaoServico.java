package servico;

import java.util.Date;
import java.util.List;

import dominio.Pedido;
import dominio.Regiao;

public interface RegiaoServico {

	public void inserirRegiao(Regiao x)throws ServicoException;
	public void deletar(Regiao x);
	public Regiao buscar(int cod);
	public List<Regiao> buscarTodos();
}
