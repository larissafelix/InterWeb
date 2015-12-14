package servicoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import servico.PedidoServico;
import dao.DaoFactory;
import dao.PedidoDao;
import dao.jpa.EM;
import dominio.Item;
import dominio.Pedido;

public class PedidoServicoImpl implements PedidoServico {
	
	// como a camada de serviço usa a camada de acesso a dados Dao, então a classe
	//Pedido serviço vai ter dependencia para a classe Pedido Dao
	private PedidoDao dao;
	
	//método construtor
	public PedidoServicoImpl() {
		dao = DaoFactory.criarPedidoDao();
	}

	@Override
	public void inserirAtualizar(Pedido x) {
		//EM CASO DE EXCEÇÃO DE NEGÓCIO SERIA AQUI REALIZADA A EXCEÇÃO
		
		EM.getLocalEm().getTransaction().begin();
		dao.inserirAtualizar(x);
		EM.getLocalEm().getTransaction().commit();
		
	}

	@Override
	public void deletar(Pedido x) {
		EM.getLocalEm().getTransaction().begin();
		dao.deletar(x);
		EM.getLocalEm().getTransaction().commit();
	}

	@Override
	public Pedido buscar(int cod) {
		return dao.buscar(cod);
	}

	@Override
	public List<Pedido> buscarTodos() {
		return dao.buscarTodos();
	}
	
	public Pedido buscarPedido(int codCli){
		Date dataAtual = new Date(System.currentTimeMillis());
		List<Pedido> listPed = dao.buscarTodos();
		Pedido pedido = null;
		
		for(Pedido x: listPed){
			if(x.getData().equals(dataAtual) && x.getCliente().getCodCliente().equals(codCli)){
				System.out.println("\nFoi realizado o pedido abaixo na data de hoje");
				System.out.println(x);
				pedido = x;
			}
		}
		System.out.println(pedido.getData());
		return pedido;
		
	}
	
	// Busca pedido por peíodo
	public List<Pedido> buscarPedidoPeriodo(Date pInicio, Date pFim){
		List <Pedido> ped = new ArrayList<>();
		ped = dao.buscarPedidoPeriodo(pInicio, pFim);
		
		return ped;
	}
	
	//método que retorna o total de vendas
	public BigDecimal TotalVendasPeriodo(Date pInicioTot, Date pFimTot ){
		BigDecimal totalVenda = new BigDecimal("0.00");
		List <Pedido>pedVendas = this.buscarPedidoPeriodo(pInicioTot, pFimTot);
		
		for(Pedido x: pedVendas){
			List<Item> itemVenda = x.getItens();
			for(Item z: itemVenda){
				totalVenda = totalVenda.add(z.getPreco().multiply(BigDecimal.valueOf(z.getQtd().longValue())));
			}
		}
		return totalVenda;
	}

	//Relatório de Pizza mais vendidas.
	public void pizzaMaisVendida (Date pInicioPizza, Date pFimPizza){

		List<Item> pizzasM = new ArrayList<>();
		List<Pedido>pedPizzas = this.buscarPedidoPeriodo(pInicioPizza, pFimPizza);
		
		for(Pedido p: pedPizzas){
			List<Item> itemPizza = p.getItens();
			for(Item q: itemPizza){
				String palavra = q.getProduto().getNomeProduto().substring(0, 5).toUpperCase();
				if(palavra.equals("PIZZA")){
					pizzasM.add(q);
				}
			}
		}
		//ordena e inverte
		Collections.sort(pizzasM);
		
		for(int i=0; i<pizzasM.size();i++){
			System.out.println(pizzasM.get(i).getProduto().getNomeProduto()
				+", Quantidade: " + pizzasM.get(i).getQtd());
		}
	}
	
	public List<Pedido> buscarPedidoCodPeriodo(Integer cod, Date inicio, Date fim){
		
		
		List<Pedido> listPed = dao.buscarTodos();
		List<Pedido> aux = new ArrayList<>();
		Pedido pedido = null;
		
		for(Pedido x: listPed){
			
			if(x.getData().getTime()>=inicio.getTime() && x.getData().getTime()<=fim.getTime()&& 
					x.getRegiao().getCodRegiao().equals(cod)){
				aux.add(x);
				
			}
		}
		
		return aux;
		
	}
}
