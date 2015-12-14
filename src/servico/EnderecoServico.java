package servico;

import java.util.List;

import dominio.Endereco;

public interface EnderecoServico {

	public void inserirAtualizar(Endereco x);
	public void deletar(Endereco x);
	public Endereco buscar(int cod);
	public List<Endereco> buscarTodos();
	
}
