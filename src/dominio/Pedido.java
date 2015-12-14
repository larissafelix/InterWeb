package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_pedido")
public class Pedido implements Serializable , Comparable<Pedido>{
	private static final long serialVersionUID = 1L;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codPedido;
	private Date data;
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy="pedido")
	private List<Item> itens;
	
	@ManyToOne
	@JoinColumn(name="atendente")
	private Atendente atendente;
	
	@ManyToOne
	@JoinColumn(name="regiao")
	private Regiao regiao;
	
	public Pedido(){
		this.itens = new ArrayList<Item>();
	}

	public Pedido(Integer codPedido, Date data, String observacao,
			Cliente cliente, Atendente atendente, Regiao regiao) {
		super();
		this.codPedido = codPedido;
		this.data = data;
		this.observacao = observacao;
		this.cliente = cliente;
		this.atendente = atendente;
		this.regiao = regiao;
		this.itens = new ArrayList<Item>();
	}

	public Integer getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Integer codPedido) {
		this.codPedido = codPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public void addItem(Item x){
		this.itens.add(x);
	}
		
	public void removeItem(Item x){
		this.itens.remove(x);
	}


	@Override
	public String toString() {
		return "Pedido [codPedido=" + codPedido + ", data=" + sdf.format(data)
				+ ", observacao=" + observacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codPedido == null) ? 0 : codPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (codPedido == null) {
			if (other.codPedido != null)
				return false;
		} else if (!codPedido.equals(other.codPedido))
			return false;
		return true;
	}
	
	 public int compareTo(Pedido pedido) {
	        if(this.atendente.getCodAtendente() > pedido.getAtendente().getCodAtendente()){
	            return -1;
	        }
	        else if(this.atendente.getCodAtendente() < pedido.getAtendente().getCodAtendente()){
	            return 1;
	        }
	        return this.atendente.getCodAtendente().compareTo(pedido.getAtendente().getCodAtendente());
	    }

	public BigDecimal valorTotal() {
		// TODO Auto-generated method stub
		BigDecimal valorTotal = new BigDecimal ("0.00");
		for(Item i: this.itens){
			valorTotal = valorTotal.add(i.subTotal());
		}
		return valorTotal;

	}
	
}
