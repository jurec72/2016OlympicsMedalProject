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

public class TestCase5 {
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
 * Method that returns List of String countries that together has sum of 18 bronze medals!
 */
	public List<String> sumOfBronzeCountries18(List<String> countries, List<Integer> bronzeMedals) {
		List<String> listCountries = new ArrayList<>();
		Map<String, Integer> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		for (int i = 1; i < countries.size(); i++) {
			map1.put(countries.get(i), bronzeMedals.get(i));
			map2.put(countries.get(i), bronzeMedals.get(i));
		}
		for (Entry<String, Integer> ent : map1.entrySet()) {
			for (Entry<String, Integer> ent1 : map2.entrySet()) {
				if (!ent.getKey().equals(ent1.getKey()) && ent.getValue() + ent1.getValue() == 18) {
					listCountries.add(ent.getKey());
					listCountries.add(ent1.getKey());
					return listCountries;

				}

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
	 * ==========test 2============================================================
	 * 
	 */

	@Test(priority = 1)
	public void sumBronzeTwoCountries() {
		// Write a method that returns a list of two countries whose sum of bronze
		// medals is 18

		String xpathCountries = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a";
		String xpathBronzeMedals = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[4]";

		List<String> countries = getListStringFromWebElement(xpathCountries);
		List<Integer> bronzeMedals = getListIntegerFromWebElement(xpathBronzeMedals);
		
		List<String> sumBronze18 = sumOfBronzeCountries18(countries, bronzeMedals);
		for (String str : sumBronze18) {
			System.out.println(str);
		}
		//check actual and expected result
		List<String> expectedSumOfBronzeCountries18=new ArrayList<>();
		expectedSumOfBronzeCountries18.add("Italy");
		expectedSumOfBronzeCountries18.add("Australia");
		
		assertEquals(sumOfBronzeCountries18(countries, bronzeMedals), expectedSumOfBronzeCountries18,"Not correct countries with sum of 18 bronze Medals");
		
	}
	/*
	 * ==========close=============================================================
	 * 
	 */

	@AfterClass
	public void finish() throws InterruptedException {
		Thread.sleep(1000);
		d.quit();
	}
}
