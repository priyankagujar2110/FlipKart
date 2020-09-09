package com.FlipKartSearch;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartSearch {
	WebDriver driver;
	String url= "https://www.flipkart.com/"; 
	String CSVFile = "C:\\Users\\rahul\\eclipse-workspace\\com.FlipKart\\CSVFile\\Flipkart.csv"; // path of csv file


	@BeforeTest
	public void setUp() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(); // Initializing chrome driver
		driver.manage().window().maximize(); // maximize the window
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url); // navigating to url
	}

	@Test (priority=1)
	public void verifyResults() throws CsvValidationException, IOException {
		// read csv file
		
		CSVReader reader = new CSVReader(new FileReader (CSVFile));
		String [] cell;

		while ((cell= reader.readNext())!=null) {
			String keyword1 = cell[0];
			String keyword2 = cell[1];
			String keyword3 = cell[2];
			WebElement search1 = driver.findElement(By.name("q"));
			search1.clear();
			search1.sendKeys(keyword1);
			search1.sendKeys(Keys.ENTER);

			WebElement search2 = driver.findElement(By.name("q"));
			search2.clear();
			search2.sendKeys(keyword2);
			search2.sendKeys(Keys.ENTER);

			WebElement search3 = driver.findElement(By.name("q"));
			search3.clear();
			search3.sendKeys(keyword3);
			search3.sendKeys(Keys.ENTER);
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit(); // close the browser
	}
}
