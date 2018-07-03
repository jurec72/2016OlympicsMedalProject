package OlympicsMedals;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase3 {
	WebDriver d;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		d.manage().window().fullscreen();
		String url = "https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table";
		d.get(url);
	}

	/*
	 * Method that find List of countries with 18 medals
	 */
	public List<String> countMedalsCountry(List<String> key, List<Integer> value) {
		List<String> countries18 = new ArrayList<>();
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < key.size(); i++) {
			m.put(key.get(i), value.get(i));
		}

		for (Entry<String, Integer> entry : m.entrySet()) { // Iterate through map
			if (entry.getValue() == 18) {

				countries18.add(entry.getKey());
			}
		}

		return countries18;
	}

	/*
	 * Method helps return list of String from WebElement, in parameters just path
	 */
	public List<String> getListStringFromWebElement(String xpath) {
		List<String> countries = new ArrayList<>();
		List<WebElement> elemCountries = d.findElements(By.xpath(xpath));
		for (WebElement w : elemCountries) {
			countries.add(w.getText());
		}
		return countries;
	}
	/*
	 * ==========close=============================================================
	 * 
	 */

	/*
	 * Method helps return list of Integer from WebElement, in parameters just path
	 */
	public List<Integer> getListIntegerFromWebElement(String xpath) {
		List<Integer> medals = new ArrayList<>();
		List<WebElement> elemSilverMedals = d.findElements(By.xpath(xpath));
		for (WebElement w : elemSilverMedals) {
			medals.add(Integer.parseInt(w.getText()));
		}
		return medals;
	}

	/*
	 * ==========test 2============================================================
	 * Calls 3 methods, 1st added to the list countries, 2nd added to the list all
	 * medals, 3rd find countries with 18 medals
	 */

	@Test(priority = 1)
	public void eighteenMedals() {
		// Write a method that returns a list of countries whose silver medal count is
		// equal to 18.
		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathSilverMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]";

		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> silverMedals = getListIntegerFromWebElement(xpathSilverMedals);
		// call method that find countries with 18 medals and print them
		System.out.println("Countris that have 18 medals: " + countMedalsCountry(countries, silverMedals));
		// check actual and expected
		List<String> expectedCountries18SilverMedals = new ArrayList<>();
		expectedCountries18SilverMedals.add("China");
		expectedCountries18SilverMedals.add("France");

		assertEquals(countMedalsCountry(countries, silverMedals), expectedCountries18SilverMedals,"Countries with 18 medals are not correct!");
	}

	@AfterClass
	public void finish() throws InterruptedException {
		Thread.sleep(1000);
		d.quit();
	}
}
