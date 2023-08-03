package org.dssproject;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DkSite extends BaseClass{
	public static void main(String[] args) throws InterruptedException {
		
	
	
	browserLaunch();
	urlLaunch("https://consultdss.authr.it/login.cfm");
	implicityWait(60);
	WebElement username = driver.findElement(By.xpath("//input[@id='usernameID']"));
	username.sendKeys("Swati.Soni");


	WebElement password = driver.findElement(By.xpath("//input[@id='passwordID']"));
	password.sendKeys("Hurix$123");
	driver.findElement(By.xpath("(//input[contains(@type,'submit')])[1]")).click();// 
	
	windowhandlesToChild1();
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
 	WebElement browseBtn = driver.findElement(By.xpath("//div[contains(text(),'Browse Projects')]"));
	Enterjs(browseBtn);
	WebElement browse = driver.findElement(By.xpath("(//div[contains(text(),'Browse')])[7]"));
	Enterjs(browse);
	WebElement teamCreationsowse = driver.findElement(By.xpath("(//div[contains(text(),'Team Creations')])[1]"));
	Enterjs(teamCreationsowse);
	WebElement searchBar = driver.findElement(By.xpath("(//input[@class='search_input'])[5]"));
	searchBar.sendKeys("PFT010HMLENG0000");
	driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	WebElement firstResult = driver.findElement(By.xpath("(//div[@class='top-label'])[1]"));
	Enterjs(firstResult);
	
	
	
}}
