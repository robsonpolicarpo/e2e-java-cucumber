package mensagem;

public enum MensagemNegocio {


	MN001 ("Inclus�o realizada com sucesso."),
	MN002 ("Altera��o realizada com sucesso."),
	MN003 ("Exclus�o realizada com sucesso."),
	
		
	
	
	
	
	
	
	;
	
	private String msg;

	MensagemNegocio(String msg) {
		this.msg = msg;
	}

	public String getValue() {
		return msg;
	}

}
