package com.zup.teste.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import com.zup.teste.utils.Navegador;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Americanas {

	private Navegador navegador;

	@Before
	public void abrirBrowser() {
		navegador = new Navegador("c:\\webdriver\\chromedriver.exe");
		navegador.abrirBrowser("https://www.americanas.com.br");
	}

	@Test
	public void validarProduto() {
		// Procura pelo produto
		navegador.digitar("h_search-input", "iphone x");
		navegador.click("h_search-btn");
		navegador.aguardarElemento("//div[@data-tracker='productgrid-main']", 30);
		assertTrue(navegador.existeNaPagina("iPhone X Cinza Espacial"));

		// Adiciona ao carrinho
		WebElement produtoListado = navegador.procurarElementoPorTexto("iPhone X Cinza Espacial 64GB Tela 5.8\" IOS 11", 30);
		navegador.click(produtoListado);
		
		// Armazena o valor na variável para poder verificar ao final se é o mesmo
		String valorProduto = navegador.pegarTexto("//span[@class='price__SalesPrice-ej7lo8-2 kjGSBk TextUI-sc-12tokcy-0 bLZSPZ']", 60);
		
		WebElement botaoComprar = navegador.procurarElementoPorId("btn-buy", 30);
		navegador.click(botaoComprar);
		
		
		WebElement botaoContinuar = navegador.procurarElementoPorTexto("Continuar", 30);
		navegador.click(botaoContinuar);
				
		String valorProdutoFinal = navegador.pegarTexto("//li[@class='summary-detail']//span[2]", 60);
		
		assertTrue(navegador.existeNaPagina("Iphone X 64gb Cinza-espacial"));
		assertEquals(valorProdutoFinal, valorProduto);
	}

	@After
	public void fecharBrowser() {
		navegador.fecharBrowser();
	}

}
