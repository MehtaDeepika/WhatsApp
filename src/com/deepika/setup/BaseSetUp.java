package com.deepika.setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseSetUp 
{
	private WebDriver driver ;
	private Properties configProps ;
	int screenshotNum = 0;
	
	/* Get user directory*/
	public String getUserDir() {
		return System.getProperty("user.dir"); // C:\Users\deepmeht\workspace1\WhatsApp
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public Properties getConfigProps() {
		return configProps;
	}

	protected void setConfigProps() 	{
		//Logic to read data and configurations from properties file
		 try {
			 
			FileReader reader=new FileReader(getUserDir()+"/data/config.props");
		    configProps=new Properties();  
			configProps.load(reader);
		   System.out.println(configProps.getProperty("URL")); 
		   
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	protected void setDriver(String Type) {
		//Initialize driver as per browser
		
		String proxyAddress = "www-proxy.us.oracle.com:80";
		
		Proxy setProxy = new Proxy();
		setProxy.setHttpProxy(proxyAddress);
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, setProxy);
		
		if(Type=="Chrome") {
			System.setProperty("webdriver.chrome.driver", getUserDir()+"/lib/"+"chromedriver.exe");
			driver = new ChromeDriver();
	    }
		else if(Type=="Mozilla") {
			System.out.println(getUserDir()+"/lib/"+"geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", getUserDir()+"/lib/"+"geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}
	
	public void getScreenshot(String testName, String screenShotName) {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		if(screenShotName.equalsIgnoreCase(null))
				screenShotName = ( screenshotNum++ ) +"";
		else 
			screenShotName = screenShotName +( screenshotNum++);
		
		String targetFilePath = getUserDir()+"\\results\\"+testName+"\\"+screenShotName+".jpg";
		try {
			FileUtils.copyFile(srcFile, new File(targetFilePath));
		} catch (IOException e) {
			System.out.println("Exception in getScreenshot method");
			e.printStackTrace();
		}
	}
	
	public void quit(WebDriver driver) {
		driver.close();
		driver.quit();
	}
	
}