package mensagem;

public enum MensagemErro {


	ME001 ("Informe pelo menos um dos campos da consulta."),
	ME002 ("Nenhum registro encontrado."),
	ME003 ("Campo obrigat�rio"),
	ME004 ("Usu�rio j� cadastrado."),
	ME005 ("As senhas n�o s�o iguais"),
	ME006 ("Email inv�lido"),
	ME007 ("Usu�rio ou senha inv�lida!"),
	ME008 ("Usu�rio e Senha n�o informados."),
	
	
	;
	
	private String msg;

	MensagemErro(String msg) {
		this.msg = msg;
	}

	public String getValue() {
		return msg;
	}

}
