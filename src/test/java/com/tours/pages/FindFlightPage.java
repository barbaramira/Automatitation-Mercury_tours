package com.tours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FindFlightPage {

	private WebDriver driver;
	
	@FindBy(css="input[value='Business']")
	@CacheLookup
	private WebElement travelClass;
	
	@FindBy(name="airline")
	@CacheLookup
	private WebElement carrier;
	
	@FindBy(xpath="//input[@name='findFlights']")
	@CacheLookup
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@value='oneway']")
	@CacheLookup
	private WebElement oneWayRadioBtn;

	@FindBy(name="fromPort")
	@CacheLookup
	private WebElement originCity;
	
	@FindBy(name="toPort")
	@CacheLookup
	private WebElement destinationCity;

	public FindFlightPage(WebDriver driver) {

		driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	/**
	 * Metodo que reune las opciones a seleccionar en
	 * la pagina de buscar vuelo
	 */
	public void findFlight(){

		oneWayRadioBtn.click();

		Select selectOrigin = new Select(originCity);//Selecciona ciudad origuen
		selectOrigin.selectByValue("New York");

		Select selectDestination = new Select(destinationCity);//Seleciona ciudad de destino
		selectDestination.selectByValue("Seattle");
		travelClass.click();
		
		Select selectCarrier= new Select(carrier);
		selectCarrier.selectByIndex(2); // Unified Airlines
		
		continueButton.click();
	}

	/**
	 * Metodod verifica el titulo de la seleccion del titulo
	 * @return String
	 */
	public String verifySelectFlightTitle() {

		return driver.getTitle();
	}
}