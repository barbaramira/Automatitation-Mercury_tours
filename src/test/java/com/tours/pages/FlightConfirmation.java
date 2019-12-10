package com.tours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmation {

	private WebDriver flightConfirmationDriver;
	
	public FlightConfirmation(WebDriver driver) {
		flightConfirmationDriver=driver;
		PageFactory.initElements(flightConfirmationDriver, this);
	}
	
}
