package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_Promocao")
public class Promocao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codPromocao;
	private String nomePromocao;
	private Date dataInicio;
	private Date dataFim;

	@OneToMany(mappedBy="promocao")
	private List<PromocaoProduto> promocaoProdutos;
	
	public Promocao(){
		this.promocaoProdutos = new ArrayList<PromocaoProduto>();		
	}

	public Promocao(Integer codPromocao, String nomePromocao, Date dataInicio,
			Date dataFim) {
		super();
		this.codPromocao = codPromocao;
		this.nomePromocao = nomePromocao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.promocaoProdutos = new ArrayList<PromocaoProduto>();	
	}


	public Integer getCodPromocao() {
		return codPromocao;
	}

	public void setCodPromocao(Integer codPromocao) {
		this.codPromocao = codPromocao;
	}

	public String getNomePromocao() {
		return nomePromocao;
	}

	public void setNomePromocao(String nomePromocao) {
		this.nomePromocao = nomePromocao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<PromocaoProduto> getPromocaoProdutos() {
		return promocaoProdutos;
	}

	public void setPromocaoProdutos(List<PromocaoProduto> promocaoProdutos) {
		this.promocaoProdutos = promocaoProdutos;
	}

	public void addPromocaoProduto(PromocaoProduto x){
		this.promocaoProdutos.add(x);
	}
		
	public void removePromocaoProduto(PromocaoProduto x){
		this.promocaoProdutos.remove(x);
	}

	@Override
	public String toString() {
		return "Promocao [codPromocao=" + codPromocao + ", nomePromocao="
				+ nomePromocao + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codPromocao == null) ? 0 : codPromocao.hashCode());
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
		Promocao other = (Promocao) obj;
		if (codPromocao == null) {
			if (other.codPromocao != null)
				return false;
		} else if (!codPromocao.equals(other.codPromocao))
			return false;
		return true;
	}
	
	//Total desconto applicado - Este para 
	
	public BigDecimal totalDescontoAplicado(){
		
		BigDecimal somaDesconto = new BigDecimal("0.00");
		
		for(int i = 0; i< this.promocaoProdutos.size(); i++){
			somaDesconto = somaDesconto.add(this.promocaoProdutos.get(i).getDesconto().multiply(
			BigDecimal.valueOf(promocaoProdutos.get(i).getProduto().getItens().get(i).getQtd().longValue())));
		}
		return somaDesconto;
	}
}
