package automacao.steps.administracao.usuario;


import automacao.driver.GWebDriver;
import automacao.page.administracao.usuario.PageUsuario;
import automacao.steps.login.StepsLogin;
import automacao.util.UtilParametros;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import mensagem.MensagemAlerta;
import mensagem.MensagemErro;
import mensagem.MensagemNegocio;

public class StepsUsuario {

	PageUsuario pageUsuario;
	
	public StepsUsuario() {
		if(pageUsuario == null){
			pageUsuario = new PageUsuario(GWebDriver.getStaticDriver());
		}
	}
	
	@Dado("^que estou conectado no sistema como administrador$")
	public void queEstouConectadoNoSistemaComoAdministrador() throws Throwable { 
		String urlAtual = pageUsuario.getDriver().getCurrentUrl();
		if (urlAtual.equals(UtilParametros.getUrlBase() + "#/login")) {
			StepsLogin login = new StepsLogin();
			login.informarOsDadosDoLogin("admin", "admin");
			login.clicarNaOpcaoEntrar();
			login.apresentarATelaHome();
		}
		else if (urlAtual.equals(UtilParametros.getUrlBase())) {
			pageUsuario.navigateTo(UtilParametros.getUrlBase() + "#/login");
			queEstouConectadoNoSistemaComoAdministrador();
		}
	}

	@Dado("^acesso o Menu: \"([^\"]*)\"$")
	public void acessoOMenu(String menu) throws Throwable {
	    pageUsuario.acessarMenu(menu);
	}

	@Dado("^clico na opcao Novo usuário$")
	public void clicoNaOpcaoNovoUsuário() throws Throwable {
		pageUsuario.btnNovoUsuario();
	}

	@Dado("^estou na tela de Cadastro de Usuário$")
	public void estouNaTelaDeCadastroDeUsuário() throws Throwable {
		String url = UtilParametros.getUrlBase() + "#/cadastro-usuario";
		pageUsuario.verificaUrl(url);
	}

	@Quando("^eu preencho as informacoes$")
	public void euPreenchoAsInformacoes() throws Throwable {
		pageUsuario.informarLogin("rpolicarpo13");
		pageUsuario.informarEmail("teste@tes.com");
		pageUsuario.selecionarPerfilAcesso("Usuario");
		pageUsuario.marcarSenhaAutomatica(false);
		pageUsuario.informarSenha("admin");
		pageUsuario.informarConfirmarSenha("admin");
	}

	@Quando("^clico na opcao Salvar$")
	public void clicoNaOpcaoSalvar() throws Throwable {
	    pageUsuario.salvar();
	}

	@Entao("^o sistema grava os dados na base$")
	public void oSistemaGravaOsDadosNaBase() throws Throwable {
	}

	@Entao("^o sitema apresenta mensagem \"([^\"]*)\"$")
	public void oSitemaApresentaMensagem(String msgEsperada) throws Throwable {
		if ( !msgEsperada.equals(MensagemErro.ME003.toString()) ) {
			if (msgEsperada.startsWith("MA")) {
				pageUsuario.verificarMsg(MensagemAlerta.valueOf(msgEsperada).getValue());
			}
			else if (msgEsperada.startsWith("ME")) {
				if(msgEsperada.equals(MensagemErro.ME006.toString())) {
					pageUsuario.verificarMsg(pageUsuario.getCampoEmail(), MensagemErro.ME006.getValue());
				}
				else if(msgEsperada.equals(MensagemErro.ME005.toString())) {
					pageUsuario.verificarMsg(pageUsuario.getCampoConfirmarSenha(), MensagemErro.ME005.getValue());
				}
				else {
					pageUsuario.verificarMsg(MensagemErro.valueOf(msgEsperada).getValue());
				}
			}
			else if (msgEsperada.startsWith("MN")) {
				pageUsuario.verificarMsg(MensagemNegocio.valueOf(msgEsperada).getValue());
			}
		}
	}
	
	@Quando("^clico na opcao Limpar$")
	public void clicoNaOpcaoLimpar() throws Throwable {
		pageUsuario.limpar();
	}

	@Entao("^o sistema limpa as informações inseridas nos campos$")
	public void oSistemaLimpaAsInformaçõesInseridasNosCampos() throws Throwable {
		pageUsuario.verificarCamposLimpos();
	}

	@Dado("^que após preenche todos os campos do cadastro$")
	public void queApósPreencheTodosOsCamposDoCadastro() throws Throwable {
	}

	@Quando("^eu nao informo dados nos \"([^\"]*)\" obrigatorio$")
	public void euNaoInformoDadosNosObrigatorio(String campo) throws Throwable {
		switch (campo) {
			case "Login": 
				pageUsuario.informarLogin("");
				pageUsuario.verificarMsg(pageUsuario.getCampoLogin(), MensagemErro.ME003.getValue());
				break;
			case "Email":
				pageUsuario.informarEmail("");
				pageUsuario.verificarMsg(pageUsuario.getCampoEmail(), MensagemErro.ME003.getValue());
				break;
			case "Senha": 
				pageUsuario.informarSenha(""); 
				pageUsuario.verificarMsg(pageUsuario.getCampoSenha(), MensagemErro.ME003.getValue());
				break;
			case "Confirme a senha": 
				pageUsuario.informarConfirmarSenha("");
				pageUsuario.verificarMsg(pageUsuario.getCampoConfirmarSenha(), MensagemErro.ME003.getValue());
				break;
			default:
				break;
		}
	}

	@Dado("^que já exista um usuario cadastrado$")
	public void queJáExistaUmUsuarioCadastrado() throws Throwable {
	}

	@Quando("^eu informo os mesmos dados no cadastro de novo usuario$")
	public void euInformoOsMesmosDadosNoCadastroDeNovoUsuario() throws Throwable {
		euPreenchoAsInformacoes();
	}

	@Quando("^informo um Email invalido \"([^\"]*)\"$")
	public void informoUmEmailInvalido(String email) throws Throwable {
	    pageUsuario.informarEmail(email);
	    pageUsuario.setFoco(pageUsuario.getCampoEmail());
	}

	@Quando("^eu informo os dados para a 'Senha' e 'Confirme a Senha' diferentes$")
	public void euInformoOsDadosParaASenhaEConfirmeASenhaDiferentes() throws Throwable {
		pageUsuario.informarSenha("teste");
		pageUsuario.informarConfirmarSenha("testeX");
	}

}
