package mensagem;

public enum MensagemNegocio {


	MN001 ("Inclusão realizada com sucesso."),
	MN002 ("Alteração realizada com sucesso."),
	MN003 ("Exclusão realizada com sucesso."),
	
		
	
	
	
	
	
	
	;
	
	private String msg;

	MensagemNegocio(String msg) {
		this.msg = msg;
	}

	public String getValue() {
		return msg;
	}

}
