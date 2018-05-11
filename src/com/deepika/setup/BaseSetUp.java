package com.deepika.setup;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseSetUp {
	
	private WebDriver driver ;
	private Properties dataConfigProps ;
		
	/* Get user directory*/
	public String getUserDir() {
		return System.getProperty("user.dir"); // C:\Users\deepmeht\workspace1\WhatsApp
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public Properties getDataConfigProps() {
		return dataConfigProps;
	}

	private void setDataConfigProps(Properties dataConfigProps) {
		
		//Logic to read data and configurations from properties file
		
		this.dataConfigProps = dataConfigProps;
	}

	private void setDriver(WebDriver driver) {
		
		//Logic to initialize driver as per device
	
		this.driver = driver;
	}
	
	public void getScreenshot(String screenShotName) {
		
		String testName = ""; // Need to see how to Fetch this value
		
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
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