package com.tours.test;

import com.tours.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TicketSale {

	public WebDriver driver;

    /**
     * provedor de datos
     * @return Object[][]
     */
	@DataProvider(name = "users")
	public Object[][] usersList() {

		Object[][] data = new Object[2][2];

		data[0][0] = "mercury";
		data[0][1] = "mercury";
		data[1][0] = "username2";
		data[1][1] = "password2";
		return data;
	};

    /**
     * metodo launchBrowser
     * lanza navegador
     */
	@BeforeTest
	public void launchBrowser() {

		driver = new ChromeDriver();
        Reporter.log("Browser Opened", true);
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
		FlightConfirmationPage flightConfirmation = new FlightConfirmationPage(driver);

		String currentTitle = loginPage.loginToDemoaut("mercury","mercury");
		String pageSource = driver.getPageSource();

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
        findFlightPage.findFlight();
    }

    @Test(priority = 3)
    public void selectFlightTest() {

        SelectFlightPage selectFlightPage = new SelectFlightPage(driver);
        selectFlightPage.selectFlights();

    }

    @Test(priority = 4)
    public void bookFlightTest() {

        BookFlightPage bookFlightPage = new BookFlightPage(driver);
        bookFlightPage.fillBillingDetails();

    }

	@Test(priority = 5)
	public void logoutFlightTest() {

		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		flightConfirmationPage.logout();

	}
    /**
     * cierra navegadores
     */
	@AfterTest
	public void tearDown() {
		driver.close();

	}
}
