package dominio;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_promocaoProduto")
public class PromocaoProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codPromocaoProduto;
	private BigDecimal desconto;
	
	@ManyToOne
	@JoinColumn(name="produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="promocao")
	private Promocao promocao;
	
	public PromocaoProduto(){
		
	}

	public PromocaoProduto(Integer codPromocaoProduto, BigDecimal desconto,
			Produto produto, Promocao promocao) {
		super();
		this.codPromocaoProduto = codPromocaoProduto;
		this.desconto = desconto;
		this.produto = produto;
		this.promocao = promocao;
	}

	public Integer getCodPromocaoProduto() {
		return codPromocaoProduto;
	}

	public void setCodPromocaoProduto(Integer codPromocaoProduto) {
		this.codPromocaoProduto = codPromocaoProduto;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	@Override
	public String toString() {
		return "PromocaoProduto [codPromocaoProduto=" + codPromocaoProduto
				+ ", desconto=" + desconto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codPromocaoProduto == null) ? 0 : codPromocaoProduto
						.hashCode());
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
		PromocaoProduto other = (PromocaoProduto) obj;
		if (codPromocaoProduto == null) {
			if (other.codPromocaoProduto != null)
				return false;
		} else if (!codPromocaoProduto.equals(other.codPromocaoProduto))
			return false;
		return true;
	}
	
	
	
}
