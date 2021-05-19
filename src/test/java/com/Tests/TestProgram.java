package com.Tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.pages.Dashboard;
import com.pages.WebDriverCommomLib;


public class TestProgram extends com.pages.BaseClass {
	@Test(enabled=true)
	public void userVerifyLoanAmount (){
		Dashboard dash=PageFactory.initElements(driver, Dashboard.class);
		WebDriverCommomLib common=PageFactory.initElements(driver, WebDriverCommomLib.class);
		dash.userClickOnAlertPopup();
		dash.enterValueInField();
		dash.userSelectValueFromTermDropdown();
		dash.userValidatetheResults();
	}
	
}
