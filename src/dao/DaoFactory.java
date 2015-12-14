package dao;

public class DaoFactory {
	
	
	public static AtendenteDao criarAtendenteDao() {
		return new AtendenteDaoJPA();
	}
	
	public static ClienteDao criarClienteDao () {
		return new ClienteDaoJPA();
	}
	
	public static EnderecoDao criarEnderecoDao () {
		return new EnderecoDaoJPA();
	}
	
	public static ItemDao criarItemDao () {
		return new ItemDaoJPA();
	}
	
	public static PedidoDao criarPedidoDao () {
		return new PedidoDaoJPA();
	}
	
	public static ProdutoDao criarProdutoDao () {
		return new ProdutoDaoJPA();
	}
	
	public static PromocaoDao criarProdPromocaoDao () {
		return new PromocaoDaoJPA();
	}

	public static PromocaoProdutoDao criarPromocaoProdutoDao () {
		return new PromocaoProdutoDaoJPA();
	}
	
	public static RegiaoDao criarRegiaoDao () {
		return new RegiaoDaoJPA();
	}

}
