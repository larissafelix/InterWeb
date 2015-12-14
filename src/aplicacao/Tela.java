package aplicacao;

import java.util.Scanner;

public class Tela {
	
	
	public static int menu(Scanner sc){
		
		System.out.println("\nDigite o número da opção desejada: ");
		System.out.println("1 - CADASTRAR CLIENTE : ");
		System.out.println("2 - CADASTRAR REGIÃO : ");
		System.out.println("3 - CADASTRAR PRODUTO : ");
		System.out.println("4 - CADASTRAR PROMOCAO : ");
		System.out.println("5 - CADASTRAR ATENDENTE : ");;
		System.out.println("6 - FAZER PEDIDO : ");
		System.out.println("7 - RELATORIO DE PEDIDOS POR PERÍODO : ");
		System.out.println("8 - RELATORIO VALOR TOTAL DE VENDAS : ");
		System.out.println("9 - RELATORIO PIZZAS MAIS VENDIDAS : ");
		System.out.println("10 - RELATÓRIO PEDIDOS POR REGIÃO : ");
		System.out.println("11 - RELATORIO DE VENDAS POR ATENDENTE : ");
		System.out.println("12 - COMPARATIVO DE VENDAS - BALCAO x ENTREGA : ");
		System.out.println("13 - RELATORIO DE DESCONTO POR PERÍODO : ");
		System.out.println("14 - VISUALIZAR CATÁLOGO ONLINE : ");
		System.out.println("15 - PESQUISAR CLIENTE : ");
		System.out.println("16 - PESQUISAR PRODUTO : ");
		System.out.println("17 - EDITAR CLIENTE : ");
		System.out.println("18 - EDITAR PRODUTO : ");
		System.out.println("20 - Sair: \n");
		//Capturar dados do teclado
		return Integer.parseInt(sc.nextLine());
		}

}
