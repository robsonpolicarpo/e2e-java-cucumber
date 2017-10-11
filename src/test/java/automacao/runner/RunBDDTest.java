package automacao.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import automacao.driver.GWebDriver;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "funcionalidades", 
		glue = "automacao/steps", 
		tags = {"@login", "@aceitacao"},
		plugin = {"pretty", "json:target/cucumber.json"},
		snippets= SnippetType.CAMELCASE, monochrome = true
		)
public class RunBDDTest {
	
	@AfterClass
	public static void tearDown() {
		GWebDriver.fecharBrowser();
	}
	
}
