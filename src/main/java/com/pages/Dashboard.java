package com.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.thoughtworks.selenium.webdriven.Windows;

public class Dashboard extends BaseClass {

	@FindBy(xpath = "//span[@aria-label='Close']")
	WebElement alertPopup;

	@FindBy(name = "LOAN_AMOUNT")
	WebElement loanAmountField;

	@FindBy(name = "INTEREST_RATE")
	WebElement interestRateField;

	@FindBy(name = "TERM")
	WebElement termsDropdown;

	@FindBy(xpath = "//div[text()='Monthly payment is $1,073.64']")
	WebElement monthlyPaymentText;

	@FindBy(xpath = "//h2[contains(text(),'Total Payments $386,513')]")
	WebElement totalPaymentText;

	@FindBy(xpath = "//div[contains(text(),'Total Interest $186,513')]")
	WebElement totalInterestText;

	public void enterValueInField() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("./PropertyFile/userData.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties pObj = new Properties();
		try {
			pObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loanAmountField.clear();
		loanAmountField.sendKeys(pObj.getProperty("Loan_Amount"));
		interestRateField.clear();
		interestRateField.sendKeys(pObj.getProperty("Interest_rate"));

	}

	public void userClickOnAlertPopup() {
		alertPopup.click();
	}

	public void userSelectValueFromTermDropdown() {
		WebDriverCommomLib common = new WebDriverCommomLib();
		common.selectValueFromDropDown(termsDropdown, "Term_in_years");
	}

	public void userValidatetheResults() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("./PropertyFile/userData.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties pObj = new Properties();
		try {
			pObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String monthlyPaymentActual = monthlyPaymentText.getText().substring(19, 28);
		Assert.assertEquals(monthlyPaymentActual, pObj.getProperty("Monthly_Payment"));
		Reporter.log("Monthly Payment" + monthlyPaymentActual);

		String totalPaymentActual = totalPaymentText.getText().substring(15, 23);
		Assert.assertEquals(totalPaymentActual, pObj.getProperty("Total_Payments"));
		Reporter.log("Total Payment" + totalPaymentActual);

		String totalInterestActual = totalInterestText.getText().substring(15, 23);
		Assert.assertEquals(totalInterestActual, pObj.getProperty("Total_Interests"));
		Reporter.log("Total Payment" + totalInterestActual);

	}

}
