package com.deepika.com.deepika.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.deepika.pageobjects.*;
import com.deepika.setup.BaseSetUp;;


public class ValidateDisplayOfRCDetailsForAVehicleTest  extends BaseSetUp{

	private WebDriver driver;
	private Properties configProps ;
	private RCStatusHomePage rcStatusHomePage ;
	
	@BeforeClass
	public void setUp() {
		setDriver("Mozilla");
		setConfigProps();
		driver=getDriver(); 
		configProps = getConfigProps();
	}
		
	@Test(priority=1,groups = {"regression","smoke"})
	public void ValidateDisplayOfRCDetails() {
	
		String RegistrationNo1 = configProps.getProperty("RegistrationNo1");
		String RegistrationNo2 = configProps.getProperty("RegistrationNo2");
		//Navigate to URL
		driver.get(configProps.getProperty("URL"));
		rcStatusHomePage = new RCStatusHomePage(driver,configProps );
		rcStatusHomePage.verifyRCStatusHomePage();
		rcStatusHomePage.enterRegistrationNo(RegistrationNo1, RegistrationNo2);
		rcStatusHomePage.clickCheckStatus();
		rcStatusHomePage.displayVehicleDetailTable();
	}
	
	@AfterClass
	public void tear() {
		quit(driver);
	}

}
