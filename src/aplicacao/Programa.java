package aplicacao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.engine.jdbc.ClobImplementer;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;

import dominio.Atendente;
import dominio.Cliente;
import dominio.Endereco;
import dominio.Item;
import dominio.Pedido;
import dominio.Produto;
import dominio.Promocao;
import dominio.Regiao;
import servico.AtendenteServico;
import servico.ClienteServico;
import servico.EnderecoServico;
import servico.ItemServico;
import servico.PedidoServico;
import servico.ProdutoServico;
import servico.PromocaoServico;
import servico.RegiaoServico;
import servico.ServicoException;
import servico.ServicoFactory;

public class Programa {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//variaveis e chamada da camada de serviço
		Cliente cliente=null;
		ClienteServico clienteServico = ServicoFactory.criarClienteServico();
		Regiao regiao=null;
		Produto produto = null;
		ProdutoServico produtosServico = ServicoFactory.criarProdutoServico();
		RegiaoServico regiaoServico = ServicoFactory.criarRegiaoServico();
		Promocao promocao = null;
		PromocaoServico promocaoServico = ServicoFactory.criarPromocaoServico();
		Endereco endereco = null;
		EnderecoServico enderecoServico = ServicoFactory.criarEnderecoServico();
		Pedido pedido = null;
		PedidoServico pedidoServico = ServicoFactory.criarPedidoServico();
		Atendente atendente = null;
		AtendenteServico atendenteServico = ServicoFactory.criarAtendenteServico();
		Item item = null;
		ItemServico itemServico = ServicoFactory.criarItemServico();
		
		
		int opcao = 0;
		
		// Opções
		
		do{
			opcao = Tela.menu(sc);
		
			switch (opcao){
			
				case 1:
					
					//cadastrar cliente
					System.out.println("\nDigite o nome do cliente para cadastrar");
					String nomec = sc.nextLine();
					
					System.out.println("\nDigite o telefone para cadastrar");
					String fonec = sc.nextLine();
					
					if (nomec.equals("") || fonec.equals("")){
						System.out.println("Nome ou telefone não inserido");
					}
					else{
						//criar novo objeto
						cliente = new Cliente(null, nomec, fonec, null);
								
						try {
							clienteServico.inserirCliente(cliente);
							//se cliente for nulo é atualização, se não é inclusão.
							System.out.println("\nCliente inserido ou atualizado com sucesso! Código: " +cliente.getCodCliente());
							
							//Inserir endereço
							System.out.println("\nPrencha o endereço nos campos abaixo: ");
							System.out.println("Digite a 'Rua': \n");
							String endRua = sc.nextLine();
							System.out.println("Digite o 'Número': \n");
							Integer endNum = Integer.parseInt(sc.nextLine());
							System.out.println("Digite o 'Bairro': \n");
							String endBairro = sc.nextLine();
							System.out.println("Digite o 'Complemento': \n");
							String endComplemento = sc.nextLine();
							if(endRua.equals("") || endNum.equals("") || endBairro.equals("")){
								System.out.println("Endereço, Rua, Número ou Bairro não foi inserido");
							}
							else{
								endereco = new Endereco(null, endRua, endNum, endBairro, endComplemento, cliente);
								enderecoServico.inserirAtualizar(endereco);
								System.out.println("\nEndereço do cliente " + cliente.getNome() + " inserido ou atualizado com sucesso!");
							}
		
						} catch (ServicoException e1) {
							System.out.println("\nErro ao inserir cliente: " + e1.getMessage());
						}
					}
				break;

				case 2:
					//cadastrar região
					System.out.println("\nDigite o nome da região para cadastrar");
					String nomereg = sc.nextLine();
					System.out.println("\nDigite o valor de entrega para cadastrar");
					String valorreg = sc.nextLine();
						
					if(nomereg.equals("") || valorreg.equals("")) {
						System.out.println("Região ou valor não inserido");
					}
					else{
						BigDecimal valorre = new BigDecimal(valorreg);
						regiao = new Regiao(null, nomereg, valorre);
						
						try {
							regiaoServico.inserirRegiao(regiao);
							System.out.println("\nRegiao inserida com sucesso! Código: " +regiao.getCodRegiao());
						}
						catch (ServicoException e) {
							System.out.println("\nErro ao inserir região: " + e.getMessage());
						}
					}
					break;
				
				case 3:
					//Cadastrar Produto
					System.out.println("\nDigite o nome do produto para cadastrar");
					String nomep = sc.nextLine();
					System.out.println("\nDigite o valor do produto para cadastrar");
					String valorp = sc.nextLine();
					
					if(nomep.equals("") || valorp.equals("")){
						System.out.println("Produto ou valor não inserido");
					}
					else{
						BigDecimal valorpr = new BigDecimal(valorp);
						produto = new Produto(null, nomep, valorpr);
						try {
							produtosServico.inserirProduto(produto);
							System.out.println("\nProduto inserido com sucesso! Código: " +produto.getCodProduto());
							
						} catch (ServicoException e) {
							System.out.println("\nErro ao inserir produto: " + e.getMessage());
						}					
					}
				break;
				
				case 4:
					//Cadastrar Promoção
					System.out.println("\nDigite o nome da promocao para cadastrar");
					String nomepro = sc.nextLine();
					System.out.println("\nDigite a data inicial da promoção no formato 'dd/MM/yyyy' ");
					String dtInicioPro = sc.nextLine();
					System.out.println("\nDigite a data final da promoção no formato 'dd/MM/yyyy' ");
					String dtFimPro = sc.nextLine();
					
					//REGRA PARA EVITAR TELEFONE OU NOME VAZIO
					
					if(nomepro.equals("") || dtInicioPro.equals("") || dtFimPro.equals("")){
						System.out.println("Nome da promoção, data inicial ou data final não inserida");
					}
					else{
						promocao = new Promocao(null, nomepro, sdf.parse(dtInicioPro),sdf.parse(dtFimPro));
						
						try {
							promocaoServico.inserirPromocao(promocao);
							System.out.println("Promoção cadastrada com sucesso! Código: " +promocao.getCodPromocao());
						} catch (ServicoException e) {
							System.out.println("Erro ao inserir promoção: " + e.getMessage());
						}
					}
				break;
	
				case 5:
					
					//cadastrar atendente					
					List<Atendente> listAtendente = atendenteServico.buscarTodos();
					System.out.println("\nAtendentes cadastrados");
					
					for(Atendente x: listAtendente){
						System.out.println(x.getNome());
					}
					System.out.println("\nDigite o nome do novo Atendente");
					String nomea = sc.nextLine();
					if(nomea.equals("")){
						System.out.println("Nome do atendente não inserido");
					}
					else{
						//criar novo objeto
						atendente = new Atendente(null, nomea);
						
						//vou salvar no banco aki
						try {
							//Vou tentar inserir aqui
							atendenteServico.inserirAtendente(atendente);
							System.out.println("Atendente inserido com sucesso! Código: " +atendente.getCodAtendente());
						}
						//Se o nome já existir no banco vou exibir a mensagem exception abaixo.
						catch (ServicoException e) {
							System.out.println("Erro ao inserir atendente: " + e.getMessage());
						}
					}
					break;
				
				case 6:
					//fazer pedido
					List<Atendente> listAtendPedido = atendenteServico.buscarTodos();
					System.out.println("\nAtendentes cadastrados");
					
					for(Atendente x: listAtendPedido){
						System.out.println("Codigo: " + x.getCodAtendente()+ ", Nome: "+ x.getNome());
					}

					System.out.println("\nDigite o codigo do atendente que irá fazer o pedido");
					int coda = Integer.parseInt(sc.nextLine());
										
					Date dataAtual = new Date(System.currentTimeMillis());
					int codCli = 0;
					//Digita atendente
					
					
					
					
					// Validar aqui buscar atendente
				try {
					atendente = atendenteServico.buscarAtendente(coda);
					//Dados do cliente
					System.out.println("\nDigite o telefone para pesquisar o cliente");
					String fonecli = sc.nextLine();
					
					//Buscar o cliente por telefone
					cliente = clienteServico.buscarCliTelefone(fonecli);
					System.out.println("Cliente "+cliente.getNome()+" encontrado!!!");
					
					//verificar se existe pedido para este cliente
//					pedidoServico.buscarPedido(cliente.getCodCliente());
					
					//Mostrar regiões para escolha
					System.out.println("\nEscolha a região abaixo pelo código");
					List<Regiao> reg = regiaoServico.buscarTodos();
					int tamReg = reg.size();
					for(Regiao x: reg ){
						System.out.println(x.getCodRegiao() + ", " + x.getNomeRegiao()+ ", " + x.getValor() );
					}
					int codreg = Integer.parseInt(sc.nextLine());	
					
					while(codreg != 1 && codreg != tamReg){
						
						System.out.println("Opção inválida, escolha a opção pelo código de 1 a " + reg.size());
						codreg = Integer.parseInt(sc.nextLine());
					}
					//buscou o objeto regiao
					regiao = reg.get((codreg-1));
					System.out.println("Se necessario digite alguma observação para o pedido, senão digite ENTER");
					String obs = sc.nextLine();
					
					
					//montou o objeto pedido
					pedido = new Pedido(null, dataAtual, obs, cliente, atendente, regiao);

					//Buscar do banco uma lista de produto
					List<Produto> listPro = produtosServico.buscarTodos();
					int tamPro = listPro.size();
					//implementar ma lista nova de produto
					List<Item> auxItem = new ArrayList<>();
					//Buscar o cliente por telefone - até poderia ser um método

					System.out.println("\nEscolha o produto pelo código:\n");
					
					for(Produto x: listPro){
						System.out.println(x.getCodProduto() + ", " + x.getNomeProduto() + ", "+ x.getPreco());
					}
						
						System.out.println(tamPro + 1 +" - Finalizar pedido\n");
						int opPro = Integer.parseInt(sc.nextLine());
						int qtdPro = 0;
						BigDecimal precoItem = new BigDecimal("0.00");
						BigDecimal totalPedido = new BigDecimal("0.00");	
						while(opPro != tamPro + 1){
							if(opPro >= 1 && opPro <= tamPro){
								
								Produto prodItem = produtosServico.buscar(opPro);
								precoItem = prodItem.precoComDesconto();
								System.out.println("Digite a quanditade do produto");
								qtdPro = Integer.parseInt(sc.nextLine());
								
								auxItem.add(new Item(null, qtdPro, precoItem, pedido, prodItem));
								totalPedido = totalPedido.add(precoItem.multiply(new BigDecimal(qtdPro)));
								
								System.out.println("-----------------------------------------------------------");
								for(int i = 0; i < auxItem.size();i++){
									System.out.println("Item " + (i + 1) + " - " + auxItem.get(i).getProduto().getNomeProduto() + ", Qtd " 
								    + auxItem.get(i).getQtd()+ ", R$ " + auxItem.get(i).getPreco() + ", Total = " 
									+ auxItem.get(i).getPreco().multiply(new BigDecimal(auxItem.get(i).getQtd())));
								}
								
								System.out.println("-----------------------------------------------------------");
								System.out.println("Item " + listPro.get(opPro-1).getNomeProduto()+ 
								", R$ " + precoItem +" adicionado ao pedido");
							}
							else{  
								System.out.println("Codigo de produto inválido, digite novamente ou digite "
										+ (tamPro +1) + " para sair");
							}
							opPro = Integer.parseInt(sc.nextLine());
						}
						//gravar pedido e itens no banco
						if( auxItem.size()>0){
							System.out.println("\nDeseja confirmar o pedido realizado no valor total de R$ " + totalPedido + "?");
							System.out.println("Digite 1 para confirmar e qualquer outra tecla para rejeitar");
							Integer oped = Integer.parseInt(sc.nextLine());
							
							if(oped == 1){
								pedidoServico.inserirAtualizar(pedido);
								
								for(Item x: auxItem){
									itemServico.inserirAtualizar(x);
								}
								System.out.println("\nPedido realizado com sucesso! Código: " + pedido.getCodPedido());
							}else{
								System.out.println("\nO pedido não foi confirmado");
							}
							
							
						}else{
							System.out.println("\nO pedido não foi realizado porque nenhum produto foi escolhido.");
							
						}
			
				} catch (ServicoException e) {
					System.out.println("Erro ao buscar atendente: " + e.getMessage());
				}
				
				//Pesquisa busca cliente

					break;
				case 7:
					//RELATORIO DE PEDIDOS POR PERÍODO 
					System.out.println("Digite o período inicial");
					String pInicio = sc.nextLine();
					System.out.println("Digite o período final");
					String pFim = sc.nextLine();
					
					List<Pedido> listPed = pedidoServico.buscarPedidoPeriodo(sdf.parse(pInicio),sdf.parse(pFim));
					//imprime pedido
					for(Pedido x: listPed){
						System.out.println(x);
						//imprime Item
						List<Item> listItem = x.getItens();
						for(Item z: listItem){
							System.out.println(z + ", " + z.getProduto().getNomeProduto());
							//Imprime produto
						}
						System.out.println("---------------------------------------------------------");
					}
					break;
				case 8:
					//Total de Vendas
					
					System.out.println("Digite o período inicial");
					String pInicioTot = sc.nextLine();
					System.out.println("Digite o período final");
					String pFimTot = sc.nextLine();
					
					System.out.println("\nTotal de vendas no período de " + 
					pInicioTot + " e " + pFimTot +" R$ " + pedidoServico.TotalVendasPeriodo
					(sdf.parse(pInicioTot), sdf.parse(pFimTot)));
					break;
				case 9:
					//Relatório de Pizzas mais vendidas.
					
					System.out.println("Digite o período inicial");
					String pInicioPizza = sc.nextLine();
					System.out.println("Digite o período final");
					String pFimPizza = sc.nextLine();
					//Tratar null					
					pedidoServico.pizzaMaisVendida(sdf.parse(pInicioPizza), sdf.parse(pFimPizza));
					
				case 10:
					List<Regiao> listReg = regiaoServico.buscarTodos();
					
					System.out.println("\nEscolha pelo codigo a região abaixo");
					for(Regiao x: listReg){
						System.out.println("Codigo " + x.getCodRegiao() + ", Região: " + x.getNomeRegiao());
					}
					Integer opt = Integer.parseInt(sc.nextLine());
					regiao = regiaoServico.buscar(opt);
					
					System.out.println("Digite o período inicial");
					String pInicioReg = sc.nextLine();
					System.out.println("Digite o período final");
					String pFimReg = sc.nextLine();
					
					
					//imprime pedido
					List<Pedido> listPedReg = pedidoServico.buscarPedidoCodPeriodo(regiao.getCodRegiao(),
							sdf.parse(pInicioReg), sdf.parse(pFimReg));
					//Relatório de pedidos por Região.
					System.out.println("\nPedidos encontrados na região: " + regiao.getNomeRegiao());
					
					for(Pedido x: listPedReg){
						
						//imprime Item
						List<Item> listItem = x.getItens();
						for(Item z: listItem){
							System.out.println(z.getProduto().getNomeProduto()+", Quantidade: "+z.getQtd()+" , Preço R$ "+ z.getPreco());
							//Imprime produto
						}
						System.out.println("Valor total do pedido R$ " + x.valorTotal());
						System.out.println("---------------------------------------------------------");
					}
					break;
				case 11:
					
					//Relatório de vendas por atendente
					
					System.out.println("Digite o período inicial");
					String pInicioAtendente = sc.nextLine();
					System.out.println("Digite o período final");
					String pFimAtendente = sc.nextLine();
					
					//Trazer todos os pedidos do período
					List<Pedido> listPedAt = pedidoServico.buscarPedidoPeriodo(sdf.parse(pInicioAtendente),sdf.parse(pFimAtendente));
					Collections.sort(listPedAt);
					int cP = 1;		
					
					//Percorrer a lista de pedidos e imprimir os dados
					System.out.println("\n=================Detalhado de vendas por Atendente===================\n");
					for(Pedido p: listPedAt){
						System.out.println("\nRelatório de vendas de " + p.getAtendente().getNome());

						System.out.println("  Pedido "+cP+", Data "+ sdf.format((p.getData())));
						for(Item i: p.getItens()){
							System.out.println("    "+i.getProduto().getNomeProduto() +", Quantidade " 
									+ i.getQtd() +", R$ " + i.getPreco());
						}
						cP++;
					}
					System.out.println("\n=================Consolidado de vendas por Atendente===================\n");
					
					List<Atendente> listAtendVend = atendenteServico.buscarTodos();
					//criei um método na classe serviço de vendas por período para essa busca
					for(Atendente a: listAtendVend){
						BigDecimal totalVendas = atendenteServico.vendasPorPeriodo(a.getCodAtendente(), 
								sdf.parse(pInicioAtendente), sdf.parse(pFimAtendente));
						if(new BigDecimal("0.00").compareTo(totalVendas)<0){
							System.out.println("Total de vendas de " +a.getNome()+ " R$ " + totalVendas);
						}
					}
					System.out.println("\n========================================================================\n");
					break;
				
				case 12:
					//Comparativo de vendas no Balcão X Entrega
					System.out.println("Não foi definido Tipo de venda no Balcão ou entrega na tabela"
							+ " pedido para implementar esse caso de uso.");
				
				case 13:
					//Relatório de desconto por período

					System.out.println("Digite o período inicial");
					String pInicioDesconto = sc.nextLine();
					System.out.println("Digite o período final");
					String pFimDesconto = sc.nextLine();
					BigDecimal totDesconto = new BigDecimal("0.00");
					
					totDesconto = promocaoServico.totalDescontoAplicado(sdf.parse(pInicioDesconto),
							sdf.parse(pFimDesconto));
					System.out.println("Total de desconto aplicado no periodo de " + pInicioDesconto +
							" e " + pFimDesconto + " R$ " + totDesconto);
					break;
				case 14:
					//Visualizar catálogo online
					List<Produto> listProd = produtosServico.buscarTodos();
					System.out.println("\n========= Produtos disponíveis no Catalogo Online ============");
					for(Produto x:listProd){
						if(x.getPreco() != x.precoComDesconto()){
							System.out.println(x.getNomeProduto()+ ", Preço atual R$ " + x.getPreco() +
									", Preço com desconto " + x.precoComDesconto());
						}else{
							System.out.println(x.getNomeProduto()+ ", Preço atual R$ " + x.getPreco());
						}
					}
					
					break;
				case 15:
					//Pesquisar cliente
					System.out.println("\nDigite o telefone para pesquisar o cliente");
					String fonecliPesq = sc.nextLine();
					
					if (fonecliPesq.equals("")){
						System.out.println("Telefone não inserido");
					}
					else{
						try {
							cliente  = clienteServico.buscarCliTelefone(fonecliPesq);
							System.out.println("\n=============Cliente encontrado===============");
							System.out.println("Nome: " + cliente.getNome() +", Telefone: "+ cliente.getTelefone());;
						} catch (ServicoException e) {
							System.out.println("\nErro ao pesquisar cliente: " + e.getMessage());
						}
					}
					break;
				case 16:
					//Pesquisar produto
					System.out.println("\nDigite o nome do produto");
					String prod = sc.nextLine();
					
					if (prod.equals("")){
						System.out.println("Nome não inserido");
					}
					else{
						try {
							Produto produt = produtosServico.buscarProdutoNome(prod);
							if (produt.getPreco()!=produt.precoComDesconto()){
								System.out.println(produt.getNomeProduto()+ ", Preço atual R$ " + produt.getPreco() +
										", Preço com desconto " + produt.precoComDesconto());
							}else{
								System.out.println(produt.getNomeProduto()+ ", Preço atual R$ " + produt.getPreco());
							}
						} catch (ServicoException e) {
							
							System.out.println("\nErro ao pesquisar produto: " + e.getMessage());
						}
					}
					break;
				case 17:
					
					//Editar cliente
					System.out.println("\nDigite o telefone para pesquisar o cliente");
					String fonePesq = sc.nextLine();
					
					if (fonePesq.equals("")){
						System.out.println("Telefone não inserido");
					}
					else{
						try {
							cliente  = clienteServico.buscarCliTelefone(fonePesq);
							System.out.println("\n=============Cliente encontrado===============");
							System.out.println("Nome: " + cliente.getNome() +", Telefone: "+ cliente.getTelefone());;
							
							System.out.println("\nDigite 1 para editar: Nome ou Telefone: ");
							System.out.println("Digite 2 para editar: Endereço do Cliente: ");
							Integer opend = Integer.parseInt(sc.nextLine());
							
							if(opend != 1 && opend != 2){
								System.out.println("Opção inválida");
							}
							else{
									
								if(opend == 1){
									System.out.println("\nDigite o novo Nome do Cliente");
									String nomecli = sc.nextLine();
									System.out.println("Digite o novo telefone do cliente");
									String fonecli = sc.nextLine();
									
									if(nomecli.equals("") || fonecli.equals("")){
										System.out.println("\nNome ou Telefone não inserido");
									}else{
										
										cliente = new Cliente(cliente.getCodCliente(), nomecli, fonecli, null);
										clienteServico.inserirCliente(cliente);
										//se cliente for nulo é atualização, se não é inclusão.
										System.out.println("\nCliente atualizado com sucesso!");
									}
								}
								else if(opend ==2){
									//Inserir endereço
									
									List<Endereco>listEnd = cliente.getEnderecos();
									System.out.println("\nEscolha pelo código o endereço para editar");
	
									for(Endereco x: listEnd){
										System.out.println("Codigo: "+ x.getCodEndereco()+", Rua: "+ x.getRua()+", Número: "+ x.getNumero()
										+ ", Bairro: " + x.getBairro() + ", Complemento: " + x.getComplemento());
									}
									//Encontrar o endereço da lista para atualizar
									Integer opEndereco = Integer.parseInt(sc.nextLine());
									
									System.out.println("\nPrencha o endereço nos campos abaixo: ");
									System.out.println("Digite a 'Rua': ");
									String endRua = sc.nextLine();
									System.out.println("Digite o 'Número': ");
									Integer endNum = Integer.parseInt(sc.nextLine());
									System.out.println("Digite o 'Bairro': ");
									String endBairro = sc.nextLine();
									System.out.println("Digite o 'Complemento': ");
									String endComplemento = sc.nextLine();
									
									if(endRua.equals("") || endNum.equals("") || endBairro.equals("")){
										System.out.println("Endereço, Rua, Número ou Bairro não foi inserido");
									}
									else{
										endereco = new Endereco(opEndereco, endRua, endNum, endBairro, endComplemento, cliente);
										enderecoServico.inserirAtualizar(endereco);
										System.out.println("\nEndereço do cliente " + cliente.getNome() + " atualizado com sucesso!");
									}
								}
							}
						} catch (ServicoException e) {
							System.out.println("\nErro ao pesquisar cliente: " + e.getMessage());
						}
					}
					break;
				case 18:
					//Editar produto
					System.out.println("\nDigite o nome do produto");
					String prodEdit = sc.nextLine();
					
					if (prodEdit.equals("")){
						System.out.println("Nome não inserido");
					}
					else{
						try {
							//Buscar codigo do produto para atualizar
							Produto produt = produtosServico.buscarProdutoNome(prodEdit);
							
							System.out.println("\n=========Produto abaixo encontrado=========");
							System.out.println(produt.getNomeProduto()+ ", Preço atual R$ " + produt.getPreco());
							
							System.out.println("\nDigite os dados abaixo para Editar o produto");
							System.out.println("Digite o nome do produto: ");
							String nomePro = sc.nextLine();
							System.out.println("Digite o preço do produto: ");
							String precoPro = sc.nextLine();
							
							if(nomePro.equals("") || precoPro.equals("")){
								System.out.println("Produto ou valor não inserido");
							}
							else{
								produto = new Produto(produt.getCodProduto(), nomePro, new BigDecimal(precoPro));
								produtosServico.inserirProduto(produto);
								System.out.println("\nProduto atualizado com sucesso! Código: " +produto.getCodProduto());
							}
						}catch (ServicoException e) {
							System.out.println("\nErro ao pesquisar produto: " + e.getMessage());
						}
					}
				case 20:
					
					System.out.println("\nPrograma finalizado");
					break;
			
				default:
					System.out.println("\nOpção inválida");
			}
		}while(opcao != 20);
		
		System.out.println("______________________________________________________________________________________________________");

	}

}