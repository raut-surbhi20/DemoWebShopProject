package com.cjc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Register 
{

	static Logger log=Logger.getLogger(Register.class.getName());
	
    public WebDriver driver;
	
	Properties pro=new Properties();
	
	@BeforeSuite
	public void openBrowser() throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\my\\eclipse-workspace\\SelTask-DemoWeBshop\\src\\com\\cjc\\registerdata.properties");
		pro.load(fis);
		
        log.debug("Open Browser");
		
		log.debug("URL=="+pro.getProperty("url"));
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\my\\Desktop\\Testing\\SeleniumFiles for WebDriver\\chrome exe for 92\\chromedriver.exe");
	
		driver=new ChromeDriver();
		
		log.info("Broswer open successfully");
		
	}
	
	@BeforeClass
	public void enterurl()
	{
        System.out.println("Enter url");
		
		driver.get(pro.getProperty("url"));
	}
	
	@BeforeTest
	public void maximisrbro()
	{
		log.info("This is before class");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void msg()
	{
		log.info("Cookies....msg");
	}
	
	
  @Test
  public void register() throws InterruptedException 
  {
      driver.findElement(By.xpath("//*[@id=\"gender-female\"]")).click();
	  
	  driver.findElement(By.name("FirstName")).sendKeys(pro.getProperty("fname"));
	  driver.findElement(By.name("LastName")).sendKeys(pro.getProperty("lname"));
	  driver.findElement(By.name("Email")).sendKeys(pro.getProperty("email"));
	  driver.findElement(By.name("Password")).sendKeys(pro.getProperty("pass"));
	  driver.findElement(By.name("ConfirmPassword")).sendKeys(pro.getProperty("confirmpass"));
	  driver.findElement(By.name("register-button")).click();
	  
	  Thread.sleep(3000);
  }
  
  
  @AfterMethod
	public void capturescreenshot() throws IOException
	{
       File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
       FileUtils.copyFileToDirectory(src, new File("C:\\Users\\my\\Desktop\\Testing\\takescreenshots--testing---file\\log4jwithtestng"));
	
		System.out.println("Screenshot taken successfully....");
		
	}

	@AfterClass
	public void deletecookie()
	{
		System.out.println("deleted cookies");
	}
	@AfterTest
	public void conclose()
	{
		System.out.println("close...");
		driver.close();
	}
	

	@AfterSuite
	public void closedriver()
	{
		System.out.println("Login Success");
		driver.quit();
	}
	
  
  
}
