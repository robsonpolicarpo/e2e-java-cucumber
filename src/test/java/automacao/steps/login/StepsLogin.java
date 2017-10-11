package automacao.steps.login;

import automacao.driver.GWebDriver;
import automacao.page.home.PageHome;
import automacao.page.login.PageLogin;
import automacao.util.UtilParametros;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import mensagem.MensagemErro;

public class StepsLogin {

	PageLogin pageLogin;

	public StepsLogin() {
		if(pageLogin == null){
			pageLogin = new PageLogin(GWebDriver.getStaticDriver());
		}
	}
	
	@Dado("^que acesso a aplicacao sem estar autenticado$")
	public void queAcessoAAplicacaoSemEstarAutenticado() throws Throwable {
	}

	@Dado("^estou na tela de Login$")
	public void estouNaTelaDeLogin() throws Throwable {
		String urlAtual = pageLogin.getDriver().getCurrentUrl();
		if (!urlAtual.equals(UtilParametros.getUrlBase() + "#/login")) {
			 new PageHome(pageLogin.getDriver()).logout();
			 pageLogin.navigateTo(UtilParametros.getUrlBase() + "#/login");
		}
		String url = UtilParametros.getUrlBase() + "#/login";
		pageLogin.verificarURL(url);
	}
	
	@Quando("^informar os dados do login \"([^\"]*)\" \"([^\"]*)\"$")
	public void informarOsDadosDoLogin(String usuario, String senha) throws Throwable {
		pageLogin.informarUsuario(usuario);
		pageLogin.informarSenha(senha);
	}

	@Quando("^clicar na opcao Entrar$")
	public void clicarNaOpcaoEntrar() throws Throwable {
		pageLogin.btnLogin();
	}
	
	@Entao("^o sistema deve autenticar$")
	public void oSistemaDeveAutenticar() throws Throwable {}


	@Entao("^apresentar a tela Home$")
	public void apresentarATelaHome() throws Throwable {
		PageHome home = new PageHome(pageLogin.getDriver());
		home.aguardarTelaHome();
		String url = UtilParametros.getUrlBase() + "#/dashboard";
		pageLogin.verificarURL(url);
	}
	
	@Entao("^o sistema nao autentica$")
	public void oSistemaNaoAutentica() throws Throwable { 
	}

	@Entao("^apresenta mensagem de erro \"([^\"]*)\"$")
	public void apresentaMensagemDeErro(String msgEsperada) throws Throwable {
		pageLogin.verificarMsg(MensagemErro.valueOf(msgEsperada).getValue());
	}

}
