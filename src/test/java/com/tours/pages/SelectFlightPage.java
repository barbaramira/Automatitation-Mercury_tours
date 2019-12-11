package com.tours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Clase Buscador de vuelo
 */
public class SelectFlightPage {

	private WebDriver driver;

	@FindBy(xpath = "//input[contains(@value,'Unified')][@name='outFlight']")
	@CacheLookup
	private WebElement selectOutbound;

	@FindBy(xpath = "//input[contains(@value,'Unified')][@name='inFlight']")
	@CacheLookup
	private WebElement selectInbound;

	@FindBy(xpath = "//input[@name='reserveFlights']")
	@CacheLookup
	private WebElement continueButton;

	/**
	 * Contructor
	 * @param driver
	 */
	public SelectFlightPage(WebDriver driver) {

		driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * metodo reune las opciones seleccionadas
	 */
	public void selectFlights() {

		selectOutbound.click();
		selectInbound.click();
		continueButton.click();
	}

	/**
	 * verifica el intinerario de vuelos
	 * @return
	 */
	public String verifyBookFlightTitle() {

		return driver.getTitle();
	}
}
