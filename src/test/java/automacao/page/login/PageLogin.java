package automacao.page.login;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automacao.enuns.EnumLabel;
import automacao.page.GenericPage;
import automacao.util.UtilMensagem;
import mensagem.MensagemErro;

public class PageLogin extends GenericPage {

	private PageLogin instancia = null;

	public PageLogin(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public PageLogin instancia() {
		synchronized (PageLogin.class) {
			if (instancia == null) {
				instancia = new PageLogin(getDriver());
			}
		}
		return instancia;
	}

	@FindBy(xpath = "//*[@formcontrolname='username']xx")
	private WebElement campoUsuario;

	@FindBy(xpath = "//*[@formcontrolname='password']")
	private WebElement campoSenha;

	@FindBy(tagName = "button")
	private WebElement botaoLogIn;
	
	@FindBy(tagName = "app-alert")
	private WebElement msgErro;

	public void informarUsuario(String usuario) throws Exception {
		try {
			instancia().campoUsuario.clear();
			destacarElemento(instancia().campoUsuario);
			if(!usuario.equals("")){
				instancia().campoUsuario.sendKeys(usuario);
			}
			else {
				instancia().campoUsuario.sendKeys("a", "\b");
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto(EnumLabel.USUARIO.value()) + "\n" + nsee.getMessage());
		}
	}

	public void informarSenha(String senha) throws Exception {
		try {	
			instancia().campoSenha.clear();
			destacarElemento(instancia().campoSenha);
			if( !senha.equals("") ){
				instancia().campoSenha.sendKeys(senha);
			}
			else {
				instancia().campoSenha.sendKeys("a", "\b");
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto(EnumLabel.SENHA.value()) + "\n" + nsee.getMessage());
		}
	}

	public void btnLogin() throws Exception {
		try {
			destacarElemento(instancia().botaoLogIn);
			instancia().botaoLogIn.submit();
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgBotaoComMapeamentoIncorreto(EnumLabel.LOGIN.value()) + "\n" + nsee.getMessage());
		}
	}
	
	public void verificarMsg(String msg) throws Exception {
		try{
			delay(1000);
			if(msg.equals(MensagemErro.ME007.getValue())){
				destacarElemento(instancia().msgErro);
				Assert.assertEquals(msg, instancia().msgErro.getText().trim());
			}
			else if(msg.equals(MensagemErro.ME003.getValue())){
				WebElement msgCampo = getDriver().findElement(By.tagName("small"));
				destacarElemento(msgCampo);
				Assert.assertEquals(msg, msgCampo.getText().trim());
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMensagemMapeamentoIncorreto(EnumLabel.MSG_VALIDACAO.value()) + "\n" + nsee.getMessage());
		}
	}
	
	public void verificarMsg(String locator, String msg) throws Exception {
		try{
			if( !msg.equals("") ){
				Assert.assertEquals(msg, instancia().msgErro.getText().trim());
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMensagemMapeamentoIncorreto(EnumLabel.MSG_VALIDACAO.value()) + "\n" + nsee.getMessage());
		}
	}
	
	public void verificarURL(String url) throws Exception {
		verificaUrl(url);
	}

}
