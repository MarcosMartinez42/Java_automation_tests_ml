package automation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ml_automation {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\usuario\\eclipse\\java-2023-06\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		// Aca abajo configuro el driver para que abra en la web deseada
		driver.get("https://www.mercadolibre.com.uy/");
		//Aqui busco 'camisetas' en el searchbox de ML
		WebElement searchBox = driver.findElement(By.id("cb1-edit"));
		searchBox.sendKeys("camisetas");
		searchBox.submit();
		//Aqui listo los elementos para luego obtener de ellos los datos requeridos
		List<WebElement> listaCamisetas = driver.findElements(By.className("ui-search-result"));
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("ml_datos_camisetas.txt");
			for (int i = 0; i < listaCamisetas.size(); i++) {
				WebElement product = listaCamisetas.get(i);
				String nombre = product.findElement(By.cssSelector(".shops__item-title")).getText();
				String precio = product.findElement(By.cssSelector(".andes-money-amount__fraction")).getText();
				String link = product.findElement(By.cssSelector(".ui-search-link")).getAttribute("href");
				fileWriter.write("Producto: " + nombre + "\n");
				fileWriter.write("Precio: $" + precio + "\n");
				fileWriter.write("Link: " + link + "\n\n");
			}
       //Una vez obtenidos y guardados en un archivo txt los datos podemos cerrar fileWriter y el navegador
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
