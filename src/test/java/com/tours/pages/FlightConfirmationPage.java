package com.tours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmationPage {

	private WebDriver driver;

	@FindBy(xpath = "/html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[7]/td/table/tbody/tr/td[3]/a")
	private WebElement logoutbtn;
	
	public FlightConfirmationPage(WebDriver driver) {

		driver = driver;

		PageFactory.initElements(driver, this);
	}


	public void logout(){

		logoutbtn.click();
	}
	
}
