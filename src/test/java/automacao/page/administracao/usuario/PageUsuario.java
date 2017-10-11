package automacao.page.administracao.usuario;

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

public class PageUsuario extends GenericPage{

	private PageUsuario instancia = null;

	public PageUsuario(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public PageUsuario instancia() {
		synchronized (PageUsuario.class) {
			if (instancia == null) {
				instancia = new PageUsuario(getDriver());
			}
		}
		return instancia;
	}

	@FindBy(xpath = "//button[contains(text(),'Novo usuário')]")
	private WebElement btnNovoUsuario;
	
	@FindBy(xpath = "//*[@id='login']")
	private WebElement campoLogin;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement campoEmail;
	
	@FindBy(xpath = "//*[@id='perfil']")
	private WebElement multiComboPerfil;

	@FindBy(xpath = "//*[@id='autoPassword']")
	private WebElement checkSenhaAutomatica;

	@FindBy(xpath = "//*[@id='senha']")
	private WebElement campoSenha;
	
	@FindBy(xpath = "//*[@id='confirmSenha']")
	private WebElement campoConfirmarSenha;
	
	@FindBy(xpath = "//button[contains(text(),'Salvar')]")
	private WebElement btnSalvar;
	
	@FindBy(xpath = "//button[contains(text(),'Limpar')]")
	private WebElement btnLimpar;
	
	@FindBy(tagName = "app-alert")
	private WebElement msgErro;

	public WebElement getBtnNovoUsuario() {
		return instancia().btnNovoUsuario;
	}

	public WebElement getCampoLogin() {
		return instancia().campoLogin;
	}

	public WebElement getCampoEmail() {
		return instancia().campoEmail;
	}

	public WebElement getMultiComboPerfil() {
		return instancia().multiComboPerfil;
	}

	public WebElement getCheckSenhaAutomatica() {
		return instancia().checkSenhaAutomatica;
	}

	public WebElement getCampoSenha() {
		return instancia().campoSenha;
	}

	public WebElement getCampoConfirmarSenha() {
		return instancia().campoConfirmarSenha;
	}

	public WebElement getBtnSalvar() {
		return instancia().btnSalvar;
	}

	public WebElement getBtnLimpar() {
		return instancia().btnLimpar;
	}

	public WebElement getMsgErro() {
		return instancia().msgErro;
	}

	public void btnNovoUsuario() throws Exception {
		try {
			destacarElemento(instancia().btnNovoUsuario);
			instancia().btnNovoUsuario.click();
			aguardarElemento(instancia().campoEmail);
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgBotaoComMapeamentoIncorreto("Novo Usuario") + "\n" + nsee.getMessage());
		}
	}
	
	public void informarLogin(String login) throws Exception {
		try {
			if(naox(login)) {
				instancia().campoLogin.clear();
				destacarElemento(instancia().campoLogin);
				if(login.equals("")){
					instancia().campoLogin.sendKeys("a", "\b");
				} 
				else {
					instancia().campoLogin.sendKeys(login);
				}
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto("Login") + "\n" + nsee.getMessage());
		}
	}
	
	public void informarEmail(String email) throws Exception {
		try {
			if(naox(email)) {
				instancia().campoEmail.clear();
				destacarElemento(instancia().campoEmail);
				if(email.equals("")){
					instancia().campoEmail.sendKeys("a", "\b");
				}
				else {
					instancia().campoEmail.sendKeys(email);
				}
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto("Email") + "\n" + nsee.getMessage());
		}
	}
	
	public void selecionarPerfilAcesso(String perfil) throws Exception {
		try {			
			destacarElemento(instancia().multiComboPerfil);
			selecionarComboGenerico(instancia().multiComboPerfil, perfil);
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto("Perfil de acesso") + "\n" + nsee.getMessage());
		}
	}
	
	public void marcarSenhaAutomatica(Boolean senhaAutomatica) throws Exception {
		try {			
			destacarElemento(instancia().campoSenha);
			if( senhaAutomatica ){
			}
			else {
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto("Senha automatica") + "\n" + nsee.getMessage());
		}
	}

	public void informarSenha(String senha) throws Exception {
		try {
			if(naox(senha)) {
				instancia().campoSenha.clear();
				destacarElemento(instancia().campoSenha);
				if(senha.equals("")){
					instancia().campoSenha.sendKeys("a", "\b");
				}
				else {
					instancia().campoSenha.sendKeys(senha);
				}
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto(EnumLabel.SENHA.value()) + "\n" + nsee.getMessage());
		}
	}
	
	public void informarConfirmarSenha(String confirmarSenha) throws Exception {
		try {
			if(naox(confirmarSenha)) {
				instancia().campoConfirmarSenha.clear();
				destacarElemento(instancia().campoConfirmarSenha);
				if(confirmarSenha.equals("")){
					instancia().campoConfirmarSenha.sendKeys("b", "\b");
				}
				else {
					instancia().campoConfirmarSenha.sendKeys(confirmarSenha);
				}
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgCampoComMapeamentoIncorreto("Confirmar Senha") + "\n" + nsee.getMessage());
		}
	}
	
	public void salvar() throws Exception {
		try {
			destacarElemento(instancia().btnSalvar);
			instancia().btnSalvar.submit();
			delay(1);
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgBotaoComMapeamentoIncorreto("Salvar") + "\n" + nsee.getMessage());
		}
	}
	
	public void limpar() throws Exception {
		try {
			destacarElemento(instancia().btnLimpar);
			instancia().btnLimpar.click();
			delay(1);
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMsgBotaoComMapeamentoIncorreto("Limpar") + "\n" + nsee.getMessage());
		}
	}
	
	public void verificarMsg(String msg) throws Exception {
		try{
			if(!msg.equals("")) {
				destacarElemento(instancia().msgErro.findElement(By.tagName("div")));
				Assert.assertEquals(msg, instancia().msgErro.getText().trim());
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMensagemMapeamentoIncorreto(EnumLabel.MSG_VALIDACAO.value()) + "\n" + nsee.getMessage());
		}
	}
	
	public void verificarMsg(WebElement elemento, String msg) throws Exception {
		try{
			if(!msg.equals("")){
				String texto = elemento.findElement(By.xpath("..")).findElement(By.tagName("small")).getText();
				destacarElemento(elemento.findElement(By.xpath("..")).findElement(By.tagName("small")));
				Assert.assertEquals(msg, texto.trim());
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem.
					getMensagemMapeamentoIncorreto(EnumLabel.MSG_VALIDACAO.value()) + "\n" + nsee.getMessage());
		}
	}
	
	
	public void verificarURL(String url) throws Exception {
		verificaUrl(url);
	}

	public void verificarCamposLimpos() {
		Assert.assertEquals(instancia().campoLogin.getText(), "");
		Assert.assertEquals(instancia().campoEmail.getText(), "");
		Assert.assertEquals(instancia().campoSenha.getText(), "");
		Assert.assertEquals(instancia().campoConfirmarSenha.getText(), "");
	}
	
}
