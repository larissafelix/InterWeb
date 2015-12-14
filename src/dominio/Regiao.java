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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_regiao")
public class Regiao  implements Serializable{
	private static final long serialVersionUID= 1L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codRegiao;
	private String nomeRegiao;
	private BigDecimal valor;
	
	@OneToMany(mappedBy="regiao")
	private List<Pedido> pedidos;
	
	public Regiao(){
		this.pedidos = new ArrayList<Pedido>();
	}

	public Regiao(Integer codRegiao, String nomeRegiao, BigDecimal valor) {
		super();
		this.codRegiao = codRegiao;
		this.nomeRegiao = nomeRegiao;
		this.valor = valor;
		this.pedidos = new ArrayList<Pedido>();
	}

	public Integer getCodRegiao() {
		return codRegiao;
	}

	public void setCodRegiao(Integer codRegiao) {
		this.codRegiao = codRegiao;
	}

	public String getNomeRegiao() {
		return nomeRegiao;
	}

	public void setNomeRegiao(String nomeRegiao) {
		this.nomeRegiao = nomeRegiao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Regiao [codRegiao=" + codRegiao + ", nomeRegiao=" + nomeRegiao + ", valor=" + valor + "]";
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
		result = prime * result + ((codRegiao == null) ? 0 : codRegiao.hashCode());
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
		Regiao other = (Regiao) obj;
		if (codRegiao == null) {
			if (other.codRegiao != null)
				return false;
		} else if (!codRegiao.equals(other.codRegiao))
			return false;
		return true;
	}
	
	//METODO PEDIDOS POR PERIODO - PASSANDO AS DATAS COMO PARAMETRO
	
	public List<Pedido> pedidosPorPeriodo(Date dataInicial, Date dataFinal){
		List<Pedido> aux = new ArrayList<Pedido>();
			
		for(int i =0; i<this.pedidos.size(); i++){
			if(this.pedidos.get(i).getData().getTime()>=dataInicial.getTime() && this.pedidos.get(i).getData().getTime()<=dataFinal.getTime()){
				aux.add(this.pedidos.get(i));
			}
		}
		return aux;
	}
}
