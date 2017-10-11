package automacao.page;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.driver.GWebDriver;
import automacao.util.UtilData;
import automacao.util.UtilMensagem;
import automacao.util.UtilParametros;

public class GenericPage extends GWebDriver {
	
	public GenericPage(WebDriver driver) {
		super(driver);
	}

	public void acessarMenu(String listaMenu){		
		String[] menusString = listaMenu.split(">");
		try {
			try {
				aguardarElemento(getDriver()
						.findElement(By.className("sidebar-nav"))
						.findElement(By.className("nav")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			WebElement menu = getDriver()
					.findElement(By.className("sidebar-nav"))
					.findElement(By.className("nav"));
			List<WebElement> menus = menu.findElements(By.tagName("a"));
			int cont = menusString.length;
			while(cont > 0){
				WebElement itemMenu = getItemMenu(menus, menusString[menusString.length - cont].trim());
				if(itemMenu != null){
					destacarElemento(itemMenu);
					itemMenu.click();					
				}	
				cont--;
			}
			delay(1000);
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException("Menu \"" + listaMenu
					+ "\" nao encontrado ou o Frame central nao foi apresentado.");
		}
	}
	
	public void delay(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean naox(String valorString){
		if(valorString.equalsIgnoreCase("x"))
			return false;
		else
			return true;            
	}

	public void aguardarElemento(WebElement elemento) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(elemento));
		} catch (Exception e) {
			throw new NoSuchElementException(UtilMensagem
					.getMsgCampoComMapeamentoIncorreto("aguardarTelaHome \n" + e.getMessage()));
		}
	}

	private WebElement getItemMenu(List<WebElement> menus, String nomeMenu){
		for (WebElement webElement : menus) {			
			if(webElement.getAttribute("text").toString().trim().equals(nomeMenu)){
				return webElement;				
			}
		}
		return null;		
	}

	public WebElement getTabelaPorTitulo(String tituloTabela) {
		List<WebElement> tabelasComTitulo = getDriver().findElements(By.tagName("caption"));
		for (WebElement tab : tabelasComTitulo) {
			if(tab.getText().trim().equals(tituloTabela.trim())){
				WebElement parent = tab.findElement(By.xpath(".."));
				if(parent.getTagName().equals("table")){
					return parent;
				}
			}
		}
		return null;
	}

	public List<WebElement> getItensTabela(String tituloTabela){
		WebElement tab = getTabelaPorTitulo(tituloTabela);
		if(tab != null){
			return tab.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		}
		return null;
	}

	public WebElement getDialogAberto() {
		List<WebElement> teste = getDriver().findElements(By.className("ui-dialog"));
		for (WebElement dialog : teste) {
			if(dialog.getAttribute("style").contains("display: block")){
				return dialog;
			}
		}
		return null;
	}

	public void destacarElemento(WebElement webElement){
		if(!UtilParametros.ÈParaDestacarElemento()) {
			return;
		}
		String sBordaVermelha = "arguments[0].style.border='3px solid red'";

		String sBordaOriginal = "var elem = arguments[0];" +
				"var style = document.defaultView.getComputedStyle(elem);" +
				"var border = style.getPropertyValue('border-top-width')" +
				" + ' ' + style.getPropertyValue('border-top-style')" +
				" + ' ' + style.getPropertyValue('border-top-color')" +
				" + ';' + style.getPropertyValue('border-right-width')" +
				" + ' ' + style.getPropertyValue('border-right-style')" +
				" + ' ' + style.getPropertyValue('border-right-color')" +
				" + ';' + style.getPropertyValue('border-bottom-width')" +
				" + ' ' + style.getPropertyValue('border-bottom-style')" +
				" + ' ' + style.getPropertyValue('border-bottom-color')" +
				" + ';' + style.getPropertyValue('border-left-width')" +
				" + ' ' + style.getPropertyValue('border-left-style')" +
				" + ' ' + style.getPropertyValue('border-left-color');" +
				"return border;";

		String sRetornarBorda = "var elem = arguments[0];" +
				"var borders = arguments[1].split(';');" +
				"elem.style.borderTop = borders[0];" +
				"elem.style.borderRight = borders[1];" +
				"elem.style.borderBottom = borders[2];" +
				"elem.style.borderLeft = borders[3];";

		try {
			JavascriptExecutor js = (JavascriptExecutor)getDriver();
			setFoco(webElement);
			String bordaOriginal = (String) (js.executeScript(sBordaOriginal, webElement));

			js.executeScript(sBordaVermelha, webElement);
			delay(350);
			js.executeScript(sRetornarBorda, webElement, bordaOriginal);
			delay(150);

		} catch (Exception e) {
			System.err.println("Erro ao destacar elemento! \n" + e.getMessage());
		}
	}

	public void setFoco(WebElement elemento){
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("arguments[0].focus();", elemento);
	}

	protected void mouseOver(String idElemento) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		String script =
				"var elem = document.getElementById('" + idElemento + "');" + "if( document.createEvent) {"
						+ "var evObj = document.createEvent('MouseEvents');"
						+ "evObj.initEvent( 'mouseover', true, false );" + "elem.dispatchEvent(evObj);"
						+ "} else if( document.createEventObject ) {" + "elem.fireEvent('onmouseover');" + "}";
		js.executeScript(script);
	}


	protected List<String> getListaJanelas() {
		String[] janelas = getDriver().getWindowHandles()
				.toArray(new String[0]);
		List<String> listaJanelas = new ArrayList<String>();
		for (String janela : janelas) {
			listaJanelas.add(janela);
		}
		return listaJanelas;
	}

	public void selecionarComboGenerico(WebElement elementoWeb, String valor) {
		try {
			Select caixaDeSelecao = new Select(elementoWeb);
			caixaDeSelecao.selectByVisibleText(valor);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException( "Valor -> " +
					 valor + " <- nao encontrado, ou " + "o mapeamento da combo estao incorreto.\n");
		}
	}

	public String selecionarComboGenerico(WebElement elementCombo) {
		String valorCombo = "";
		try {
			Select combo = new Select(elementCombo);			
			List<WebElement> listaDeElementos = combo.getOptions();
			if(listaDeElementos.size() > 1){
				int posicao = getRandomInteger(1, listaDeElementos.size());		
				valorCombo = listaDeElementos.get(posicao).getText();
				combo.selectByIndex(posicao);
				return valorCombo;
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Valor -> " + valorCombo + " <- nao encontrado, ou "
					+ "o mapeamento da combo estao incorreto.\n");
		}
		return valorCombo;
	}
	
	public String selecionarComboMultiploGenerico(WebElement elementCombo, List<String> valores) {
		String valoresSelecionados = "";
		try {	
			Select combo = new Select(elementCombo);			
			for (String valor : valores) {
				combo.selectByVisibleText(valor);
			}
			
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Valor -> " + valoresSelecionados + " <- nao encontrado, ou "
					+ "o mapeamento da combo estao incorreto.\n");
		}
		return valoresSelecionados;
	}

	public String selecionarComboMultiploGenerico(WebElement elementCombo, int qtd) {
		String valoresSelecionados = "";
		try {	
			Select combo = new Select(elementCombo);			
			List<WebElement> listaDeElementos = combo.getOptions();						
			List<Integer> numerosGerados = new ArrayList<>();
			int posicao = 0;			
			if(qtd > listaDeElementos.size()){
				qtd = listaDeElementos.size();
			}
			for(int i = 0; i < qtd; i++){
				listaDeElementos = combo.getOptions();
				posicao = getNumeroRandom(0, listaDeElementos.size(), numerosGerados);
				numerosGerados.add(posicao);	

				WebElement webElement = (WebElement) listaDeElementos.get(posicao);		

				if(valoresSelecionados.equals("")){
					valoresSelecionados = webElement.getText();
				}
				else{
					valoresSelecionados = valoresSelecionados+","+webElement.getText();
				}
				combo.selectByVisibleText(webElement.getText());
				delay(500);								
			}	
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Valor -> " + valoresSelecionados + " <- nao encontrado, ou "
					+ "o mapeamento da combo estao incorreto.\n");
		}
		return valoresSelecionados;
	}

	public static String getPrimeiroElementoSelecionadoCombo(WebElement elementoWeb) {
		try {
			Select caixaDeSelecao = new Select(elementoWeb);
			WebElement option = caixaDeSelecao.getFirstSelectedOption();
			return option.getText();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("O mapeamento da combo estao incorreto.\n" + e);
		}
	}

	public static void tirarSelecaoCombo(WebElement elementoWeb) {
		try {
			Select caixaDeSelecao = new Select(elementoWeb);
			caixaDeSelecao.deselectAll();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("O mapeamento da combo estao incorreto.\n" + e);
		}
	}

	public void getItemLista(String alvo, WebElement listaCombo) throws Exception {
		try {
			List<WebElement> comboItens = listaCombo.findElements(By.tagName("li"));
			for (WebElement itemCombo : comboItens) {
				if (itemCombo.getText().equals(alvo)) {
					itemCombo.click();
					return;
				}
			}
			throw new Exception("Valor -> " + alvo + " <- nao encontrado.");
		} catch (NoSuchElementException nsee) {
			throw new NoSuchElementException("Combo nao encontrada");
		}
	}

	private BufferedImage tirarPrint() {
		try{
			return new Robot().createScreenCapture(
					new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void coletarPrint() {
		try{
			ImageIO.write(tirarPrint(), "png", new File(montarPathDaImagem()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String montarPathDaImagem() {
		StringBuffer caminhoCompleto = new StringBuffer();
		caminhoCompleto.append("./target/img");
		File file = new File(caminhoCompleto.toString());  
		file.mkdir(); 
		caminhoCompleto.append("[");
		caminhoCompleto.append("teste");
		caminhoCompleto.append("]");
		caminhoCompleto.append(" ");
		caminhoCompleto.append(UtilData.getDate(""));
		caminhoCompleto.append(" ");
		caminhoCompleto.append(".png");
		return caminhoCompleto.toString();
	}

	public void verificaUrl(String url) throws Exception{
		try {
			Assert.assertEquals(url, getDriver().getCurrentUrl());
		} catch (Exception e) {
			throw new Exception("Falha ao verificar URL. \n" + e.getMessage() );
		} 
	}

	public static int getRandomInteger(int posicaoMinima, int posicaoMaxima){		
		return ((int) (Math.random()*(posicaoMaxima - posicaoMinima))) + posicaoMinima;
	}

	private static int getNumeroRandom(int posicaoMinima, int posicaoMaxima, List<Integer> numerosGerados) {
		int valorMaximo = 1000;	
		boolean numValido = true;
		int x = 0;
		while(valorMaximo > 0){
			x = getRandomInteger(posicaoMinima, posicaoMaxima);
			for (Integer i : numerosGerados) {
				if(i == x){		
					numValido = false;
					break;
				}
			}
			if(numValido){
				return x;
			}
			valorMaximo--;
		}
		return x;		
	}

}
