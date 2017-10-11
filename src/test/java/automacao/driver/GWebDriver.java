package automacao.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import automacao.util.UtilParametros;


public abstract class GWebDriver {

	private static WebDriver driver;
	private final Long ESPERA_ELEMENTO_IMPLICITA = 15L;
	private final Long ESPERA_PAGINA_CARREGAR = 20L;

	public GWebDriver(WebDriver wdriver) {
		driver = wdriver;
	}

	public WebDriver getDriver() {
		if (driver == null) {
			configIniciais();
		}
		return driver;
	}
	
	public static WebDriver getStaticDriver() {
		return driver;
	}

	private WebDriver criarWebDriver(String browser) {
		try {
			switch (browser.toUpperCase()) {
			case "IE":
				System.setProperty("webdriver.ie.driver", "./src/test/resources/automacao/IEDriver32.exe");
				return new InternetExplorerDriver();

			case "CHROME":
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/automacao/chromedriver.exe");
				return new ChromeDriver();

			default:
				System.setProperty("webdriver.gecko.driver", "./src/test/resources/automacao/geckodriver.exe");
				return new FirefoxDriver();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/automacao/geckodriver.exe");
		return new FirefoxDriver();
	}
	
	private void configIniciais() {
		driver = criarWebDriver(UtilParametros.getBrowser());
		String urlAtual = getDriver().getCurrentUrl();
		if( urlAtual.equals("")  
				|| urlAtual.equals("about:blank")
				|| urlAtual.equals("data:,")
				|| urlAtual.contains("localhost") ) {

			maximizarBrowser();
			setEsperaElementoImplicita(ESPERA_ELEMENTO_IMPLICITA);
			setEsperaPaginaCarregar(ESPERA_PAGINA_CARREGAR);
			navigateTo(UtilParametros.getUrlBase());
		}
	}

	public void navigateTo(String url) {
		getDriver().navigate().to(url);
	}

	public void maximizarBrowser() {
		getDriver().manage().window().maximize();
	}

	public void setEsperaElementoImplicita(Long segundos) {
		getDriver().manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}
	
	public void setEsperaPaginaCarregar(Long segundos) {
		getDriver().manage().timeouts().pageLoadTimeout(segundos, TimeUnit.SECONDS);
	}
	
	public static void fecharBrowser() {
		if (getStaticDriver() != null) {
			getStaticDriver().quit();
		}
	}

}
