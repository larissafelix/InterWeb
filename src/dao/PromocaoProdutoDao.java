package dao;

import java.util.List;
import dominio.PromocaoProduto;

public interface PromocaoProdutoDao {
	
	public void inserirAtualizar(PromocaoProduto x);
	public void deletar(PromocaoProduto x);
	public PromocaoProduto buscar(int cod);
	public List<PromocaoProduto> buscarTodos();

}
