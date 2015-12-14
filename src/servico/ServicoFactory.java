package servico;

import servicoImpl.AtendenteServicoImpl;
import servicoImpl.ClienteServicoImpl;
import servicoImpl.EnderecoServicoImpl;
import servicoImpl.ItemServicoImpl;
import servicoImpl.PedidoServicoImpl;
import servicoImpl.ProdutoServicoImpl;
import servicoImpl.PromocaoProdutoServicoImpl;
import servicoImpl.PromocaoServicoImpl;
import servicoImpl.RegiaoServicoImpl;

public class ServicoFactory {
	
	public static AtendenteServico criarAtendenteServico(){
		return new AtendenteServicoImpl();
	}
	
	public static ClienteServico criarClienteServico(){
		return new ClienteServicoImpl();
	}
	
	public static EnderecoServico criarEnderecoServico(){
		return new EnderecoServicoImpl();
	}
	
	public static ItemServico criarItemServico(){
		return new ItemServicoImpl();
	}
	
	public static PedidoServico criarPedidoServico(){
		return new PedidoServicoImpl();
	}
	
	public static ProdutoServico criarProdutoServico(){
		return new ProdutoServicoImpl();
	}

	public static PromocaoProdutoServico criarPromocaoProdutoServico(){
		return new PromocaoProdutoServicoImpl();
	}
	
	public static PromocaoServico criarPromocaoServico(){
		return new PromocaoServicoImpl();
	}

	public static RegiaoServico criarRegiaoServico(){
		return new RegiaoServicoImpl();
	}
}
