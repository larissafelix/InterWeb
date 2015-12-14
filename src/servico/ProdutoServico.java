package servico;

import java.util.List;

import dominio.Produto;

public interface ProdutoServico {

	public void inserirProduto(Produto x)throws ServicoException;
	public void deletar(Produto x);
	public Produto buscar(int cod);
	public List<Produto> buscarTodos();
	public Produto buscarProdutoNome(String nome)throws ServicoException;
	
}
