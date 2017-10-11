package automacao.enuns;

public enum EnumMensagens {
	
	MSG_REGISTRO_INCLUIDO("Registro incluído com sucesso."),
	MSG_OPERACAO_INCLUIDO("Operação realizada com sucesso."),
	MSG_PENDENCIA_INCLUIDO("Pendência cadastrada com sucesso!"),
	MSG_CAMPOS_OBRIGATORIOS("Dados obrigatórios não informados!"),
	MSG_REGISTRO_INCLUIDO_ENDERECO("Endereço incluído com sucesso à solicitação."),
	MSG_REGISTRO_ALTERADO("Registro alterado com sucesso."),
	MSG_SOLICITACAO_EXCLUIDA("Solicitação excluída com sucesso!"),
	MSG_REGISTRO_EXCLUIDO("Registro excluído com sucesso."),
	MSG_ARQUIVO_EXCLUIDO("Arquivo excluído com sucesso!"),
	MSG_ARQUIVO_ANEXADO("Arquivo anexado com sucesso!"),
	MSG_ENDERECO_EXCLUIDO("Endereço excluído com sucesso da solicitação."),
	MSG_DADOS_OBG_FALTANTES_NA_SOLICITACAO
	("Existem dados obrigatórios referentes à solicitação, favor corrigir os dados!"),
	MSG_SOLICITACAO_PRONTA_PARA_ENVIO
	("Todos os dados obrigatórios referentes à solicitação foram informados. Favor enviar a solicitação."),
	MSG_BTN_ENVIAR_SOLICITACAO_DESABILITADO
	("Botão Gerar despacho desabilitado."),
	MSG_BTN_GERAR_DESPACHO_DESABILITADO
	("Botão Enviar Solicitação desabilitado mesmo com uma mensagem!"),
	MSG_CAMPO_COMPARACAO ("Erro o campo esta divergente da massa."),
	MSG_MENSAGEM_NAO_ESPERADA ("Erro a mensagem não é a esperada: "),	
	MSG_VALOR_DIVERGENTE("Erro o valor não é o esperado: "),
	MSG_ERRO_PAGINA_INESPERADA("Erro, a pagina não é o esperado: "),
	MSG_ATIVIDADE_JA_INFORMADA("A atividade informada já foi cadastrada."),
	MSG_RESPONSAVEL_NAO_LOCALIZADO("Responsável não localizado. Proceda com o cadastramento."),
	MSG_TODOS_REGISTROS ("( todos os registros )"),
	MSG_PRIMEIRO_REGISTRO ("( primeiro da lista )"),
	MSG_LOG_EXCLUIR_REGISTRO ("de exclusão dos resgistros");
	
	private String valor;

	public String get(){
		return this.valor;
	}

	private EnumMensagens(String valor){
		this.valor = valor;
	}
}
