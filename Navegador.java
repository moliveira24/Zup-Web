package com.zup.teste.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navegador {
	WebDriver driver;
	WebDriverWait wait;

	public Navegador(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
		this.driver = new ChromeDriver();
	}

	public void abrirBrowser(String url) {
		driver.get(url);
	}

	public void fecharBrowser() {
		driver.close();
	}

	public void click(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public String pegarTexto(String xpath, Integer timeout) {
		wait = new WebDriverWait(driver, timeout);
		String retorno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).getText();
		return retorno;
	}

	public void click(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element); 
	}

	public void digitar(String id, String texto) {
		driver.findElement(By.id(id)).sendKeys(texto);
	}

	public boolean existeNaPagina(String texto) {
		return driver.getPageSource().contains(texto);
	}

	public void aguardarCarregamento(Integer timeout) {
		new WebDriverWait(driver, timeout).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void aguardarElemento(String xpath, Integer tempo) {
		wait = new WebDriverWait(driver, tempo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void aguardarElemento(WebElement element, Integer timeout) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement procurarElementoPorTexto(String texto, Integer timeout) {
		aguardarCarregamento(60);

		wait = new WebDriverWait(driver, timeout);
		return wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'" + texto + "')]"))));
	}

	public WebElement procurarElementoPorId(String id, Integer timeout) {
		aguardarCarregamento(60);
		
		wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(id))));
	}
}
