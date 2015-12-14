package servico;

public class ServicoException extends Exception {

	private static final long serialVersionUID = 1L;
	// camada personalizada para quando acontecer um erro de negócio.
	private Integer codigo;
	
	public ServicoException(String msg, Integer codigo)	{
		super(msg);
		this.codigo = codigo;
	}
	
	public Integer getCodigo(){
		return codigo;
	}

}
