package OlympicsMedals;



import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
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

public class TestCase2 {

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
	 * ===========max gold medals Country method=================================
	 * Method takes two lists with info, key for countries and value for number of
	 * medals Then added everything to map , use Collections.max to find max value
	 * and then iterate through map with Entry method and return country that has
	 * max gold medals!
	 * 
	 */
	public String maxMedalsCountry(List<String> key, List<Integer> value) {
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < key.size(); i++) {
			m.put(key.get(i), value.get(i));
		}

		int maxValueInMap = (Collections.max(m.values())); // This will return max value in the map

		for (Entry<String, Integer> entry : m.entrySet()) { // Iterate through map
			if (entry.getValue() == maxValueInMap) {

				return entry.getKey();
			}
		}

		return null;
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
	 * ==========test 2===========================================================
	 * In this test I call method that counts max gold medal country and print it
	 * Also I found WebElements and get text from them and convert to List!
	 */
	@Test(priority = 1)
	public void goldMedals() {
		// Write a method that returns the name of the country with the most number
		// of gold medals.
		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathGoldMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]";
		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> goldMedals = getListIntegerFromWebElement(xpathGoldMedals);

		// call method maxGoldCountries and print that country
		System.out.println("Country with gold max medals: " + maxMedalsCountry(countries, goldMedals));
		//check actual and expected
		String expectedMaxGoldMedalsCountry="United States";
		assertEquals(maxMedalsCountry(countries, goldMedals), expectedMaxGoldMedalsCountry,"Country with max gold medals is not correct!");
	}

	/*
	 * ==========test 3===========================================================
	 * In this test I call method that counts max silver medal country and print it.
	 * Also I found WebElements and get text from them and convert to List!
	 */
	@Test(priority = 2)
	public void silverMedals() {
		// Write a method that returns the name of the country with the most number
		// of silver medals.
		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathSilverMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]";

		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> silverMedals = getListIntegerFromWebElement(xpathSilverMedals);

		// call method maxGoldCountries and print that country
		System.out.println("Country with silver max medals: " + maxMedalsCountry(countries, silverMedals));
		//check actual and expected
		String expectedMaxSilverMedalsCountry="United States";
		assertEquals(maxMedalsCountry(countries, silverMedals), expectedMaxSilverMedalsCountry,"Country with max silver medals is not correct!");
	}

	/*
	 * ==========test 4============================================================
	 * In this test I call method that counts max bronze medal country and print it.
	 * Also I found WebElements and get text from them and convert to List!
	 */
	@Test(priority = 3)
	public void bronzeMedals() {
		// Write a method that returns the name of the country with the most number
		// of silver medals.
		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathBronzeMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]";

		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> bronzeMedals = getListIntegerFromWebElement(xpathBronzeMedals);

		// call method maxGoldCountries and print that country
		System.out.println("Country with bronze max medals: " + maxMedalsCountry(countries, bronzeMedals));
		//check actual and expected
				String expectedMaxBronzeMedalsCountry="United States";
				assertEquals(maxMedalsCountry(countries, bronzeMedals), expectedMaxBronzeMedalsCountry,"Country with max bronze medals is not correct!");
	}

	/*
	 * ==========test 5============================================================
	 * In this test I call method that counts max total medal country and print it.
	 * Also I found WebElements and get text from them and convert to List!
	 */
	@Test(priority = 4)
	public void maxTotalMedals() {
		// Write a method that returns the name of the country with the most number of
		// medals.

		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathTotalMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]";

		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> totalMedals = getListIntegerFromWebElement(xpathTotalMedals);

		// call method maxGoldCountries and print that country
		System.out.println("Country with total max medals: " + maxMedalsCountry(countries, totalMedals));
		//check actual and expected
		String expectedMaxTotalMedalsCountry="United States";
		assertEquals(maxMedalsCountry(countries, totalMedals), expectedMaxTotalMedalsCountry,"Max medals country is not correct!");
	}

	@AfterClass
	public void finish() throws InterruptedException {
		Thread.sleep(1000);
		d.quit();
	}

}
