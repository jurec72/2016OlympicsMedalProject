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

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase4 {
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
	 * Method that takes country name and return column and row number in table!
	 * 
	 */
	public Map<Integer, Integer> findRowAndColumnInTable(String countryName) {
		Map<Integer, Integer> rowAndColumn = new HashMap<>();

		int columnTable = d
				.findElements(
						By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/thead//th"))
				.size();
		int rowTable = d
				.findElements(By
						.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th/a"))
				.size();

		for (int i = 1; i < columnTable; i++) {
			for (int j = 1; j < rowTable; j++) {
				if (d.findElement(
						By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead/tr/th["
								+ i + "]"))
						.getText().equals("NOC")) {
					String rows = d.findElement(
							By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["
									+ j + "]/th/a"))
							.getText();
					if (rows.equals(countryName)) {

						rowAndColumn.put(i, j);
						return rowAndColumn;
					}

				}

			}
		}

		return null;
	}

	/*
	 * ==========test 2============================================================
	 * 
	 */

	@Test(priority = 1)
	public void rowAndColumn() {
		// Write a method that takes country name and returns the row and column
		// number. You decide the datatype of the return.
		List<Integer> actualResult = new ArrayList<>();
		List<Integer> expectedResult = new ArrayList<>();
		expectedResult.add(2);
		expectedResult.add(6);

		String countryName = "Japan";
		Map<Integer, Integer> rowAndColumn = findRowAndColumnInTable(countryName);
		for (Entry<Integer, Integer> entry : rowAndColumn.entrySet()) { // Iterate through map
			System.out.println(
					"Country is " + countryName + ", column is " + entry.getKey() + ", row is " + entry.getValue());
			actualResult.add(entry.getKey());
			actualResult.add(entry.getValue());

		}

		assertEquals(actualResult, expectedResult,"Row and Column are not correct!");

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
