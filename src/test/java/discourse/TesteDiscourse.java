package discourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;

public class TesteDiscourse {
	
private static ChromeDriver driver;
	
	@BeforeClass
    public static void setUpTest(){
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
		ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
		
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.get("https://www.discourse.org");
    }

	@Test
	public void testeNavegacaoDiscourse(){
		WebElement botaoDemo = driver.findElement(By.linkText("Demo"));
		botaoDemo.click();
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy(0,document.body.scrollHeight)", "");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jse.executeScript("scrollBy(0,document.body.scrollHeight)", "");
		
		WebElement tbody = driver.findElement(By.tagName("tbody"));
		List<WebElement> itens = tbody.findElements(By.tagName("tr"));
		List<String> titulosFechado = new ArrayList<String>();

		Map<String, Integer> tabelaCategorias = new HashMap<String, Integer>();
		
		float qtdMaiorViews = 0;
		String tituloComMaiorViews = "";
		
		for(WebElement tr : itens){
			List<WebElement> tds = tr.findElements(By.tagName("td"));
			boolean vezesMil = false;
			if(tds.get(3).getText().contains("k")){
				vezesMil = true;
			}
			float views = Float.parseFloat(tds.get(3).getText().replace("k", ""));
			
			if(vezesMil){
				views = views*1000;
			}
		
			if(views>qtdMaiorViews){
				qtdMaiorViews = views;
				tituloComMaiorViews = tr.findElement(By.className("link-top-line")).getText();
			}
			
			boolean exists = tr.findElements(By.tagName("svg")).size()!=0;
			if(exists){
				if(tr.findElements(By.cssSelector("svg[class='fa d-icon d-icon-lock svg-icon locked svg-string']")).size()!=0){
					titulosFechado.add(tr.findElement(By.className("link-top-line")).getText());
				}
			}
			
			String nomeCategoria ="Sem Categoria";
			if(tr.findElements(By.className("category-name")).size()!=0){
				nomeCategoria = tr.findElement(By.className("category-name")).getText();
			}
			
			if(!tabelaCategorias.containsKey(nomeCategoria)){
				tabelaCategorias.put(nomeCategoria, 1);
			}else{
				Integer qtd = tabelaCategorias.get(nomeCategoria);
				tabelaCategorias.put(nomeCategoria, qtd+1);
			}
			
			
		}
		
		System.out.println("*** Topicos Fechados ***");
		for(String titulo:titulosFechado){
			System.out.println(titulo);
		}
		System.out.println("");
		
		System.out.println("*** Quantidade de Itens por categoria ***");
		for (Map.Entry<String, Integer> entry : tabelaCategorias.entrySet()) {
			System.out.println("Categoria : " + entry.getKey().toUpperCase() + ", Quantidade de Itens : " + entry.getValue());
		}
		
		System.out.println("");
		System.out.println("*** titulo com maior numero de views ***");
		System.out.println(tituloComMaiorViews);
		
		
	}

}
