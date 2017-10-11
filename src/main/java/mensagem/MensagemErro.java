package mensagem;

public enum MensagemErro {


	ME001 ("Informe pelo menos um dos campos da consulta."),
	ME002 ("Nenhum registro encontrado."),
	ME003 ("Campo obrigatório"),
	ME004 ("Usuário já cadastrado."),
	ME005 ("As senhas não são iguais"),
	ME006 ("Email inválido"),
	ME007 ("Usuário ou senha inválida!"),
	ME008 ("Usuário e Senha não informados."),
	
	
	;
	
	private String msg;

	MensagemErro(String msg) {
		this.msg = msg;
	}

	public String getValue() {
		return msg;
	}

}
