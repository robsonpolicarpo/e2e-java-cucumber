package automacao.enuns;

public enum EnumLog {
	
	MSG_MAXIMIZAR_NAVEGADOR	("Maximizou o Navegador."),
	MSG_INICIALIZAR_NAVEGADOR ("Inicializou e maximizou o navegador: "),
	MSG_INFORMAR_URL ("Informou a URL: "),
	MSG_INFORMAR_USUARIO ("Informou o usuário: "),
	MSG_INFORMAR_SENHA ("Informou a senha: "),
	MSG_CLICK_MODULO ("Clicou no Módulo:"),
	MSG_CLICK_MENU ("Clicou no Menu: "),
	MSG_CLICK_BOTAO ("Clicou no botão: "),
	MSG_CLICK_LUPA ("Clicou na lupa."),
	MSG_CLICK_LETRA ("Clicou na letra: "),
	MSG_CLICK_LINK ("Clicou no link: "),
	MSG_CLICK_BOTAO_CONFIRMACAO_EXCLUSAO ("Clicou no botão:  exclusão do registro."),
	MSG_BOTAO_NAO_ENCONTRADO ("O botão de  informado na massa de dados não foi encontrado"),
	MSG_COMPARACAO_CAMPO ("Verificado no campo "),
	MSG_COMPARACAO_COMBO ("Comparou o valor da combo , "),
	MSG_COMPARACAO_LABEL ("Comparação de label. Esperado: "),
	MSG_INFORMAR_CAMPO ("Informou o campo ."),
	MSG_LOG_INFORMAR_CAMPO ("Informou o campo: "),
	MSG_LIMPAR_CAMPOS ("Limpou o campo: "),
	MSG_MENSAGEM_VALIDACAO ("Realizou a verificação da mensagem esperada."),
	MSG_LOG_MENSAGEM_VALIDACAO ("Realizou a verificação da mensagem esperada: "),
	MSG_LOG_URL_VALIDACAO ("Realizou a verificação da url esperada: "),
	MSG_LOG_IMAGEM_VALIDACAO ("Verificou se a imagem esperada foi apresentada na tela."),
	MSG_MENSAGEM_VERIFICACAO ("Realizou a verificação da existência de ocorrências."),
	MSG_VERIFICA_ITEM_LISTA ("Verificou se o item foi Excluído."),
	MSG_VERIFICA_ITEM_INCLUIDO_LISTA ("O registro foi Incluído."),
	MSG_VERIFICA_EXISTENCIA_ITEM_LISTA ("Verificou a existência do registro:"),
	MSG_VERIFICA_ITEM_EXCLUIDO_LISTA ("O registro foi Excluído."),
	MSG_VERIFICA_ITEM_ALTERADO_LISTA ("O registro foi Alterado."),
	MSG_VERIFICA_ITEM_NAO_INCLUIDO_LISTA ("O registro não foi incluído."),
	MSG_MAPEAMENTO ("O mapeamento do  não esta correto"),
	MSG_MAPEAMENTO_INCORRETO ("Mapeamento incorreto! "),
	MSG_MAPEAMENTO_LABEL ("O mapeamento da label:  não esta correto."),
	MSG_ORDENACAO ("Ordenou a lista pela coluna: "),
	MSG_PAGINACAO ("Clicou no componente de paginação: "),
	MSG_QUANTIDADE_DE_REGISTROS ("Selecionou o valor:  na combo de quantidade de registros por página."),
	MSG_SELECIONAR_COMBO ("Selecionou no campo "),
	MSG_SELECIONAR_RADIO ("Selecionou o RadioBox "),
	MSG_MARCOU_CHECK ("Marcou o CheckBox: "),
	MSG_DESMARCOU_CHECK ("Desmarcou o CheckBox: "),
	MSG_CHECK_MARCADO ("Marcou o CheckBox: "),
	MSG_CHECK_DESMARCADO ("Desmarcou o CheckBox: "),
	MSG_MARCOU_CHECK_ESPECIFICO ("Marcou o CheckBox:  do  {2}"),
	MSG_INFORMAR_CAMPO_ESPECIFICO ("Informou o campo:  do ."),
	MSG_CHECK_NAO_ENCONTRADO ("O check não foi encontrado."),
	MSG_ACESSO_TELA ("Tela de: "),
	MSG_CLICK_ABA ("Clicou na aba: "),
	MSG_DOWNLOAD ("Realizou o download do arquivo: ");
	
	private String valor;
	
	public String value(){
		return this.valor;
	}
	
	private EnumLog(String valor){
		this.valor = valor;
	}

}
