package dominio;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="tb_atendente")
public class Atendente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAtendente;
	private String nome;
	
	@OneToMany(mappedBy="atendente")
	private List<Pedido> pedidos;
	
	public Atendente(){
		this.pedidos = new ArrayList<Pedido>();
	}

	
	public Atendente(Integer codAtendente, String nome) {
		super();
		this.codAtendente = codAtendente;
		this.nome = nome;
		this.pedidos = new ArrayList<Pedido>();
	}


	public Integer getCodAtendente() {
		return codAtendente;
	}

	public void setCodAtendente(Integer codAtendente) {
		this.codAtendente = codAtendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}


	@Override
	public String toString() {
		return "Atendente [codAtendente=" + codAtendente + ", nome=" + nome
				+ "]";
	}
	
	public void addPedido(Pedido x){
		this.pedidos.add(x);
	}
		
	public void removePedido(Pedido x){
		this.pedidos.remove(x);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codAtendente == null) ? 0 : codAtendente.hashCode());
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
		Atendente other = (Atendente) obj;
		if (codAtendente == null) {
			if (other.codAtendente != null)
				return false;
		} else if (!codAtendente.equals(other.codAtendente))
			return false;
		return true;
	}

	//METODO VENDAS POR PERIODO DE UM DETERMINADO ATENDENTE - PASSANDO AS DATAS COMO PARAMETRO

	 public BigDecimal vendasPorPeriodo(Date dataInicial, Date dataFinal){
	 
		 BigDecimal vendas = new BigDecimal ("0.00");
	 
	 	for(int i=0; i< this.pedidos.size();i++){
	 		
	   	 	if(this.pedidos.get(i).getData().getTime()>=dataInicial.getTime() && this.pedidos.get(i).getData().getTime()<=dataFinal.getTime()){
				vendas = vendas.add(this.pedidos.get(i).valorTotal());
			}
	 	}
	 	return vendas;
	   }



}
