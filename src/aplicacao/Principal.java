// Essa aplicação foi utilizada para cadastrar os dados do cliente no banco de dados.

package aplicacao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Atendente;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Item;
import dominio.Pedido;
import dominio.Produto;
import dominio.Promocao;
import dominio.PromocaoProduto;
import dominio.Regiao;

public class Principal {
	
	public static void main(String[] args) throws ParseException{

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Cliente c1= new Cliente(null,"João da Silva","92347869", 1);
		Cliente c2= new Cliente(null, "George Mendes", "88002211", 1);
		
		Endereco e1= new Endereco(null,"Rua do Caju", 23,"centro", null,c1);
		Endereco e2= new Endereco(null,"Rua Jardins", 237,"Martins", null,c2);
		c1.addEndereco(e1);
		c2.addEndereco(e2);
		
		
		Atendente a1= new Atendente(null,"Maria");
		Atendente a2= new Atendente(null,"Jose das Couves");

		Regiao r1= new Regiao(null,"Sul",new BigDecimal("3.00"));
		Regiao r2= new Regiao(null,"Norte",new BigDecimal("5.00"));
		
		Pedido p1 = new Pedido(null,sdf.parse("02/09/2015"),"Sem cebola",c1,a1, r1);				
		Pedido p2 = new Pedido(null,sdf.parse("05/09/2015"),"Nenhuma",c2,a2,r2);
		
		c1.addPedido(p1);
		c2.addPedido(p2);
		r1.addPedido(p1);
		r2.addPedido(p2);
		
		Produto prod1 = new Produto(null, "Pizza de calabresa", new BigDecimal("31.90"));
		Produto prod2 = new Produto(null, "Coca-Cola", new BigDecimal("6.00"));
		Produto prod3 = new Produto(null, "Pizza A Moda", new BigDecimal("45.90"));
		Produto prod4 = new Produto(null, "Pizza de quibe", new BigDecimal("39.60"));	
		
		Item i1 = new Item(null, 2, new BigDecimal("21.90"), p1, prod1);
		Item i2 = new Item(null, 1, new BigDecimal("6.00"), p1, prod2);
		Item i3 = new Item(null, 1, new BigDecimal("40.90"), p2, prod3);
		Item i4 = new Item(null, 5, new BigDecimal("32.60"), p2, prod4);
					
		prod1.addItem(i1);
		prod2.addItem(i2);
		prod3.addItem(i3);
		prod4.addItem(i4);
		
		p1.addItem(i1);
		p1.addItem(i2);
		p2.addItem(i3);
		p2.addItem(i4);
		
		Promocao prom1 = new Promocao(null, "Terça Insana",sdf.parse("12/08/2015"),sdf.parse("13/11/2015"));
	    Promocao prom2 = new Promocao(null, "De casa é a melhor", sdf.parse("01/09/2015"), sdf.parse("01/10/2015"));
		
	    PromocaoProduto promP1 = new PromocaoProduto(null, new BigDecimal("10.0"), prod1, prom1);
	   	PromocaoProduto promP3 = new PromocaoProduto(null, new BigDecimal("5.00"), prod3, prom2);
	   	PromocaoProduto promP4 = new PromocaoProduto(null, new BigDecimal("7.00"), prod4, prom1);
		
		prom1.addPromocaoProduto(promP1);
		prom1.addPromocaoProduto(promP4);
		prom2.addPromocaoProduto(promP3);
		
			
		//Inserir os produtos
			
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("meujpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(c1);
		em.persist(c2);
		em.persist(e1);
		em.persist(e2);
		em.persist(a1);
		em.persist(a2);
		em.persist(r1);
		em.persist(r2);
		em.persist(p1);
		em.persist(p2);
		em.persist(prod1);
		em.persist(prod2);
		em.persist(prod3);
		em.persist(prod4);
		em.persist(i1);
		em.persist(i2);
		em.persist(i3);
		em.persist(i4);
		em.persist(prom1);
		em.persist(prom2);
		em.persist(promP1);
		em.persist(promP3);
		em.persist(promP4);
		
		
		em.getTransaction().commit();
		
		System.out.println("Pronto!");
		em.close();
		emf.close();
		
		
		// TESTE 1: VALOR TOTAL DE UM PEDIDO	
		System.out.println("Valor Total do pedido: " +p1+":");
		
		
		System.out.println(p1.valorTotal());
		
		Date dataInicial = sdf.parse("01/09/2015");
		Date dataFinal = sdf.parse("05/09/2015");
		
		// TESTE 2: LISTA DE PEDIDOS REALIZADOS EM UM DADO INTERVALO DE DATAS
		System.out.println("Pedidos realizados na" +r1+ "entre: "+sdf.format(dataInicial)+" e "+sdf.format(dataFinal)+": ");
		System.out.println(r1.pedidosPorPeriodo(dataInicial, dataFinal));
				
		// TESTE 3: TOTAL DE DESCONTO CONCEDIDO EM UM DADO INTERVALO DE DATAS
		System.out.println("Desconto concedido entre: "+sdf.format(dataInicial)+" e "+sdf.format(dataFinal)+": ");
		//System.out.println(prom2.descontoPorPeriodo(dataInicial, dataFinal));
		
		// TESTE 4: TOTAL DE VENDAS DE UM ATENDENTE EM UM DADO INTERVALO DE DATAS
		System.out.println("O valor total de vendas do "+a1+" no período de "+sdf.format(dataInicial)+" a "+sdf.format(dataFinal)+" é: ");
		System.out.println(a1.vendasPorPeriodo(sdf.parse("01/09/2015"), sdf.parse("07/09/2015")));
	}

}
