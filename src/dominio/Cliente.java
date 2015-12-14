package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codCliente;
	private String nome;
	private String telefone;
	private Integer contPizza;

	@OneToMany(mappedBy="cliente")
	private List<Endereco> enderecos;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos;
	
	public Cliente(){
		this.enderecos = new ArrayList<Endereco>();
		this.pedidos = new ArrayList<Pedido>();
		
	}

	public Cliente(Integer codCliente, String nome, String telefone, Integer contPizza) {
		super();
		this.codCliente = codCliente;
		this.nome = nome;
		this.telefone = telefone;
		this.contPizza = contPizza;
		this.enderecos = new ArrayList<Endereco>();
		this.pedidos = new ArrayList<Pedido>();
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getContPizza() {
		return contPizza;
	}

	public void setContPizza(Integer contPizza) {
		this.contPizza = contPizza;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	public void addEndereco(Endereco x){
		this.enderecos.add(x);
	}
		
	public void removeEndereco(Endereco x){
		this.enderecos.remove(x);
	}
	
	public void addPedido(Pedido x){
		this.pedidos.add(x);
	}
		
	public void removePedido(Pedido x){
		this.pedidos.remove(x);
	}

	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", nome=" + nome
				+ ", telefone=" + telefone + ", contPizza=" + contPizza + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codCliente == null) ? 0 : codCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codCliente == null) {
			if (other.codCliente != null)
				return false;
		} else if (!codCliente.equals(other.codCliente))
			return false;
		return true;
	}
	
	
}
