package com.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverCommomLib {

	public void mouseMouse(WebElement elemnet) {
		Actions act = new Actions(BaseClass.driver);
		act.moveToElement(elemnet).build().perform();
	}

	public void waitForElemnet(WebElement element) {
		WebDriverWait wait = new WebDriverWait(BaseClass.driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void selectValueFromDropDown(WebElement element,String value){
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
		Select sel=new Select(element);
		sel.selectByValue(pObj.getProperty(value));
	}
}
