package com.tours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookFlightPage {

	private WebDriver driver;

	@FindBy(name = "passFirst0")
	private WebElement firstName;
	
	@FindBy(name = "passLast0")
	private WebElement lastName;
	
	@FindBy(name = "creditnumber")
	private WebElement creditCardNum;
	
	@FindBy(name = "cc_exp_dt_mn")
	private WebElement expiryMonth;
	
	@FindBy(name = "cc_exp_dt_yr")
	private WebElement expiryYear;
	
	@FindBy(xpath="//input[@name='buyFlights']")
	private WebElement purchaseButton;
	
	public BookFlightPage(WebDriver driver) {

		driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void fillBillingDetails() {
		
		firstName.sendKeys("Rohan");
		lastName.sendKeys("Swayambhatla");
		creditCardNum.sendKeys("44556677");

		Select monthSel= new Select(expiryMonth);
		monthSel.selectByVisibleText("10");

		Select yearSel = new Select(expiryYear);
		yearSel.selectByVisibleText("2010");
		purchaseButton.click();
	}
	
}
