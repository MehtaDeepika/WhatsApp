package com.deepika.pageobjects;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RCStatusHomePage {
	
	private WebDriver driver;
	private Properties configProps;
	WebDriverWait wait ;
By homeButton =  By.xpath(".//*[@id='form_rcdl']/div[1]/div[1]/div/div/ul/li[1]");
By formNameLabel= By.xpath(".//*[contains(text(),'Know your RC Status')]");
By registrationNo1InputBox = By.id("form_rcdl:tf_reg_no1");
By registrationNo2InputBox = By.id("form_rcdl:tf_reg_no2");
By checkStatusButton = By.cssSelector("button[id=form_rcdl:j_idt33]");
By resetButton = By.cssSelector("button[id=form_rcdl:j_idt33]");
By vechicleDetailTable = By.xpath(".//*[@id='form_rcdl:j_idt61']/child::table");
	
public RCStatusHomePage(WebDriver driver, Properties configProps)  {
			this.driver = driver;
			this.configProps = configProps;
	}
	
  public boolean verifyRCStatusHomePage() {
	  			
	  			wait = new WebDriverWait(driver,20);
	  			WebElement homeButtoneElement  = wait.until(ExpectedConditions.visibilityOfElementLocated(homeButton));
	  			WebElement formNameLabelElement  = wait.until(ExpectedConditions.visibilityOfElementLocated(formNameLabel));
	  			System.out.println(homeButtoneElement.isDisplayed());
	  			System.out.println(formNameLabelElement.isDisplayed());
 				return homeButtoneElement.isDisplayed() && formNameLabelElement.isDisplayed();
	}
  
  public void  enterRegistrationNo(String RegistrationNo1, String RegistrationNo2) {
	      driver.findElement(registrationNo1InputBox).clear();
		  driver.findElement(registrationNo1InputBox).sendKeys(RegistrationNo1);
		  System.out.println("Enter RegistrationNo1 :"+ RegistrationNo1);
		  driver.findElement(registrationNo2InputBox).clear();
		  driver.findElement(registrationNo2InputBox).sendKeys(RegistrationNo2);
		  System.out.println("Enter RegistrationNo2 :"+ RegistrationNo2);
}

  public void  clickCheckStatus() {
	  driver.findElement(checkStatusButton).click();
	  System.out.println("Click checkStatusButton");
}
  
  public void  clickReset() {
	  driver.findElement(resetButton).click();
}
  
  public boolean displayVehicleDetailTable() {
	  
	 Alert alert = driver.switchTo().alert();
	 String alertMessage= driver.switchTo().alert().getText();
	 if(alertMessage.equalsIgnoreCase("Registration No. does not exist!!! Please check the number."))
	 {
		 System.out.println("Incorrect Registration number : "+ alertMessage);
		 
		 alert.accept();
		 return false;
	 }
	 
	 WebElement tab = driver.findElement(vechicleDetailTable);
	 
	  List<WebElement> trows = tab.findElements(By.tagName("tr"));
	  int RowIndex=1;
	  for(WebElement trow:trows)
		{
		      List<WebElement> cols=trow.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      for(WebElement col:cols)
		      {
		           System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data "+col.getText());
		           ColumnIndex=ColumnIndex+1;
		       }
		      RowIndex=RowIndex+1;
		 }
	  return true;
  }
	  
}