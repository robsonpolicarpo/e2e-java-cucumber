package mensagem;

public enum MensagemAlerta {


	MA001 ("Deseja realmente excluir o Registro?"),
	
		
	
	
	
	
	
	
	;
	
	private String msg;

	MensagemAlerta(String msg) {
		this.msg = msg;
	}

	public String getValue() {
		return msg;
	}

}
