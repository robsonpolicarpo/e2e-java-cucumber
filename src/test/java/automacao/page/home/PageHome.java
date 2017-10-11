package automacao.page.home;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.enuns.EnumLabel;
import automacao.page.GenericPage;
import automacao.util.UtilMensagem;

public class PageHome extends GenericPage{

	private PageHome instancia = null;

	public PageHome(WebDriver driver) {
		super(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public PageHome instancia() {
		synchronized (PageHome.class) {
			if (instancia == null) {
				instancia = new PageHome(getDriver());
			}
		}
		return instancia;
	}
	
	@FindBy(css = "a.nav-link.dropdown-toggle")
	private WebElement dropDownUsuario;

	@FindBy(partialLinkText = "Sair")
	private WebElement linkSair;

	@FindBy(xpath = "//*[@id='signInErrorDiv']")
	private WebElement msgErro;
	
	public void logout() throws Exception {
		clickPerfilUsuario();
		clickSair();
	}
	
	public void clickPerfilUsuario() throws Exception {
		try {
			aguardarElemento(instancia().dropDownUsuario);
			destacarElemento(instancia().dropDownUsuario);
			instancia().dropDownUsuario.click();
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(UtilMensagem
					.getMsgCampoComMapeamentoIncorreto("clickPerfilUsuario"));
		}
	}
	
	public void aguardarTelaHome() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(instancia().dropDownUsuario));
		} catch (Exception e) {
			throw new NoSuchElementException(UtilMensagem
					.getMsgCampoComMapeamentoIncorreto("aguardarTelaHome \n" + e.getMessage()));
		}
	}

	public void clickSair() throws Exception {
		try {			
			destacarElemento(instancia().linkSair);
			instancia().linkSair.click();
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(
					UtilMensagem.getMsgCampoComMapeamentoIncorreto("clickSair"));
		}
	}

	public void verificarMsg(String msg) throws Exception {
		try{
			if( !msg.equals("") ){
				Assert.assertEquals(msg, instancia().msgErro.getText().trim());
			}
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException(
					UtilMensagem.getMensagemMapeamentoIncorreto(EnumLabel.MSG_VALIDACAO.value()));
		}
	}
	
	public void verificarURL(String url) throws Exception {
		verificaUrl(url);
	}

}
