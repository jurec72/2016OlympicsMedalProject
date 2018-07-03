package OlympicsMedals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase1 {
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
	 * ==========test 2==========
	 */
	@Test(priority = 1)
	public void verifyRankRow() {

		ArrayList<Integer> unsortedRankRow = new ArrayList<>();
		ArrayList<Integer> sortedRankRow = new ArrayList<>();
		List<WebElement> listRankRow = d.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody//tr/td[1]"));
int sizeRankRow=d.findElements(
		By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody//tr/td[1]")).size();
		int rankText = 0;

		for (int i = 0; i < sizeRankRow-1; i++) {
			rankText = Integer.parseInt(listRankRow.get(i).getText());
			sortedRankRow.add(rankText);
			unsortedRankRow.add(rankText);
			System.out.println(rankText);
		}

		Collections.sort(sortedRankRow);

		assertEquals(unsortedRankRow, sortedRankRow, "Rank row not correct!");

	}

	/*
	 * ==========test 3, 4==========
	 */
	@Test(priority = 2)
	public void verifyNoc() {
		// click on NOC
		WebElement clickNoc = d.findElement(By.xpath("//th[@scope='col'][contains(text(),'NOC')]"));
		clickNoc.click();
		// verify NOC
		List<WebElement> countryNames = d.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody//th/a"));
		List<String> unsortedCountries = new ArrayList<>();
		SortedSet<String> sortedCountries = new TreeSet<>();

		for (WebElement str1 : countryNames) {
			unsortedCountries.add(str1.getText());
			sortedCountries.add(str1.getText());
		}
		
		
		
		assertEquals(unsortedCountries, sortedCountries, "Countries not sorted in Alphabetic order!");
	}

	/*
	 * ==========test 5==========
	 */
	@Test(priority = 3)
	public void notAscendingRankRowVerufy() {
		ArrayList<Integer> unsortedRankRow = new ArrayList<>();
		ArrayList<Integer> sortedRankRow = new ArrayList<>();
		List<WebElement> listRankRow = d.findElements(
				By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody//tr/td[1]"));
int sizeRankRow=d.findElements(
		By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody//tr/td[1]")).size();
		int rankText = 0;

		for (int i = 0; i < sizeRankRow-1; i++) {
			rankText = Integer.parseInt(listRankRow.get(i).getText());
			sortedRankRow.add(rankText);
			unsortedRankRow.add(rankText);
			System.out.println(rankText);
		}
		Collections.sort(sortedRankRow);

		assertNotEquals(unsortedRankRow, sortedRankRow, "Rank row not correct!");
	}

	@AfterClass
	public void finish() throws InterruptedException {
		Thread.sleep(1000);
		d.quit();
	}

}
