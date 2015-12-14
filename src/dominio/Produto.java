package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codProduto;
	private String nomeProduto;
	private BigDecimal preco; 
	
	@OneToMany(mappedBy="produto")
	private List<Item> itens;
	
	@OneToMany(mappedBy="produto")
	private List<PromocaoProduto> promocaoProdutos;
	
	public Produto(){
		this.itens = new ArrayList<Item>();
		this.promocaoProdutos = new ArrayList<PromocaoProduto>();
		
	}

	public Produto(Integer codProduto, String nomeProduto, BigDecimal preco) {
		super();
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.itens = new ArrayList<Item>();
		this.promocaoProdutos = new ArrayList<PromocaoProduto>();
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<PromocaoProduto> getPromocaoProdutos() {
		return promocaoProdutos;
	}

	public void setPromProdutos(List<PromocaoProduto> promocaoProdutos) {
		this.promocaoProdutos = promocaoProdutos;
	}
	
	public void addItem(Item x){
		this.itens.add(x);
	}
		
	public void removeItem(Item x){
		this.itens.remove(x);
	}
	
	public void addPromocaoProduto(PromocaoProduto x){
		this.promocaoProdutos.add(x);
	}
		
	public void removePromocaoProduto(PromocaoProduto x){
		this.promocaoProdutos.remove(x);
	}

	@Override
	public String toString() {
		return "Produto [codProduto=" + codProduto + ", nomeProduto="
				+ nomeProduto + ", preco=" + preco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codProduto == null) ? 0 : codProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codProduto == null) {
			if (other.codProduto != null)
				return false;
		} else if (!codProduto.equals(other.codProduto))
			return false;
		return true;
	}
	
	public BigDecimal precoComDesconto(){
		
		Date dataAtual = new Date(System.currentTimeMillis());
		BigDecimal precoDesconto = new BigDecimal("0.00");
		BigDecimal precoDescontot = new BigDecimal("0.00");
		
		for(int i=0; i < this.promocaoProdutos.size(); i++){
			
			if(dataAtual.getTime()>=(this.promocaoProdutos.get(i).getPromocao().getDataInicio().getTime()) && 
			   dataAtual.getTime()<=(this.promocaoProdutos.get(i).getPromocao().getDataFim().getTime())){
				
				precoDesconto = this.preco.subtract(promocaoProdutos.get(i).getDesconto());
			}
		}
		if (precoDesconto.equals(precoDescontot)){
			precoDesconto = this.preco;
		}
		return precoDesconto;
	}
}