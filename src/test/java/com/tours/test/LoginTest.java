package com.tours.test;

import com.tours.pages.FindFlightPage;
import com.tours.pages.FlightConfirmation;
import com.tours.pages.LoginPage;
import com.tours.pages.SelectFlightPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class LoginTest {

	public WebDriver driver;


	@DataProvider(name = "users")
	public Object[][] usersList() {

		Object[][] data = new Object[2][2];

		data[0][0] = "mercury";
		data[0][1] = "mercury";
		data[1][0] = "username2";
		data[1][1] = "password2";
		return data;
	};

	@BeforeTest
	public void launchBrowser() {
		driver = new ChromeDriver();

			Reporter.log("Browser Opened", true); // setting to true will print in console too
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://newtours.demoaut.com");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
	}

	/**
	 * Test valida logeo de la pagina
	 */
	@Test(priority = 1, enabled = true, description = "Description: Testing if user is abole to login to Mecrcury Tours")
	public void loginToMercuryTours() {

		LoginPage loginPage = new LoginPage(driver);


		SelectFlightPage selectFlight = new SelectFlightPage(driver);
		FlightConfirmation flightConfirmation = new FlightConfirmation(driver);

		String currentTitle = loginPage.loginToDemoaut("mercury","mercury");
		String pageSource = driver.getPageSource();

	 	//##comprobando si hubo algún error##

		if (pageSource.contains("Whitelabel Error")) {

			System.out.println("Login Unsuccessful. Fatal Error");
			driver.quit();

		} else {
			Assert.assertEquals(currentTitle, "Find a Flight: Mercury Tours:");
		}
	}

	/**
	 * Test depende del test loginToMercuryTours,
	 */
	@Test(dependsOnMethods = { "loginToMercuryTours" }, priority = 2, invocationCount = 1)
	public void findFlightTest() {

		FindFlightPage findFlightPage = new FindFlightPage(driver);
		findFlightPage.findFlight(); //clic

		String title = findFlightPage.verifySelectFlightTitle();
		Assert.assertEquals(title, "Select a Flight: Mercury Tours"); //assert
	}

	@Test(priority = 3)
	public void selectFlightTest() {

		SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
		selectFlightPage.selectFlights();

		String title = selectFlightPage.verifyBookFlightTitle();
		Assert.assertEquals(title, "Book a Flight: Mercury Tours");
	}

	@AfterTest
	public void tearDown() {
		driver.close();

	}
}
