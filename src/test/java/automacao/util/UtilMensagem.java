/**
 * 
 */
package automacao.util;

import automacao.enuns.EnumLabel;
import automacao.enuns.EnumLog;
import automacao.enuns.EnumMensagens;

public class UtilMensagem {

	public static String getMsgCampoComMapeamentoIncorreto(String campo){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				EnumLabel.CAMPO.value() +
				" : " +
				campo;
	}
	
	public static String getMsgImagemComMapeamentoIncorreto(){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				"A imagem esperada não foi encontrada na tela.";
	}

	public static String getMsgBotaoComMapeamentoIncorreto(String botao){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				EnumLabel.BOTAO.value() + 
				botao;
	}

	public String obterMsgComparacao(String campo, String valor1, String valor2){
		return EnumMensagens.MSG_CAMPO_COMPARACAO.get() +
				campo +
				valor1 + 
				valor2;
	}

	public static String getMensagemMapeamentoIncorreto(String mensagem){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				mensagem;
	}

	public static String getMsgLabelComMapeamentoIncorreto(String campo){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				EnumLabel.LABEL.value() +
				" : " +
				campo;
	}

	public static String getMsgComboComMapeamentoIncorreto(String campo){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				EnumLabel.COMBO.value() +
				" : " +
				campo;
	}
	
	public static String getMsgAbaComMapeamentoIncorreto(String campo){
		return EnumLog.MSG_MAPEAMENTO_INCORRETO.value() + 
				EnumLabel.ABA.value() +
				" : " +
				campo;
	}
	
}
