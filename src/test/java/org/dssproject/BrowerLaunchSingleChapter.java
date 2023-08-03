package org.dssproject;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowerLaunchSingleChapter extends BaseClass {

	public static void main(String[] args) throws AWTException, InterruptedException, IOException {

		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("PLEASE ENTER THE ISBN");
		String isbn = sc.nextLine();
		System.out.println("PLEASE ENTER THE TITLE NAME");
		String title = sc.nextLine();
		
		browserLaunch();
		urlLaunch("https://app.cloud.scorm.com/sc/guest/SignInForm");
		implicityWait(150);
		userName("onlinepurchase@hurix.com");
		passwordd("Hur!X@123456");
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();// ------Login
		driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-remove cookie_info_close']")).click();// ------close
																												// //
																												// cookies
		driver.findElement(By.xpath("//b[text()='Add Content']")).click();// -----addContentClick
		WebElement importbtn = driver.findElement(By.xpath("//a[text()='Import a SCORM, AICC, xAPI or cmi5 package']"));// -----importDropdown
		Enterjs(importbtn);
		driver.findElement(By.xpath("//label[text()='Choose file']"));
		enterRobot();
		String filePath = "D:\\DSS\\"+isbn+".zip";// -------change 1
				
		WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
		fileInput.sendKeys(filePath);
		driver.findElement(By.xpath("//button[@id='startImportButton']")).click();// ------importpackage
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='glyphicon glyphicon-edit link editTitle']")));
		WebElement outsideTitle = driver.findElement(By.xpath("(//div[@class='col-md-10'])[1]"));

		String outsideTitleName = outsideTitle.getText();
		System.out.println(outsideTitleName);
		
		TakesScreenshot ts = (TakesScreenshot) driver;//----------Screenshot
		File From = ts.getScreenshotAs(OutputType.FILE);
		String baseDr = "D:\\DSS\\Screenshots\\";
		String titleName = isbn + "\\";
		File to = new File(baseDr + titleName+"outside title name.png");
		FileUtils.copyFile(From, to);

		String inputTitleName = title+" ("+isbn+")";// -------change 2

		if (inputTitleName.equals(outsideTitleName)) {
			System.out.println("outside title matched with given title");
		} else {
			System.out.println("outside title does'nt matched with given title");
		}
		driver.findElement(By.xpath("//div[@class='btn btn-success btn-lg clickable right']")).click();// -------launchButton
		driver.switchTo().frame("ScormContent");
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> wdwHandles = driver.getWindowHandles();
		Iterator<String> it = wdwHandles.iterator();
		String parentid = it.next();
		String Childid = it.next();
		driver.switchTo().window(Childid);

		driver.switchTo().frame("scormdriver_content");
		WebElement insideTitle = driver.findElement(By.id("navbar_course_title"));
		String insideTitleName = insideTitle.getText();

		if (inputTitleName.equals(insideTitleName)) {
			System.out.println("inside title matched with given title");
		} else {
			System.out.println("inside title does'nt matched with given title");
		}
		Thread.sleep(5000);
		driver.manage().window().maximize();
		TakesScreenshot ts1 = (TakesScreenshot) driver;//----------Screenshot
		File From1 = ts1.getScreenshotAs(OutputType.FILE);
		File to1 = new File(baseDr + titleName+"inside title name.png");
		FileUtils.copyFile(From1, to1);
		
		
		WebElement startBtn = driver.findElement(By.xpath("(//a[@tabindex=\"1\"])[23]"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",startBtn);
		
		TakesScreenshot ts1_1 = (TakesScreenshot) driver;//----------Screenshot
		File From1_1 = ts1_1.getScreenshotAs(OutputType.FILE);
		File to1_1 = new File(baseDr + titleName+"Cover image.png");
		FileUtils.copyFile(From1_1, to1_1);
		
		js.executeScript("arguments[0].click()",startBtn);
		driver.findElement(By.xpath("(//i[@class='fa'])[2]")).click();
		driver.findElement(By.xpath("(//div[contains(text(),'CONTINUE')])[2]")).click();
		driver.findElement(By.xpath("(//div[contains(text(),'TEST')])[2]")).click();

		for (int i = 2; i >= 0; i--) {
			driver.findElement(By.xpath("(//button[@tabindex='1'])[2]")).click();

			Alert al = driver.switchTo().alert();
			al.accept();
			Thread.sleep(3000);
			System.out.println("-----------------\n");
			
		
			TakesScreenshot ts2 = (TakesScreenshot) driver;//----------Screenshot
			File From2 = ts2.getScreenshotAs(OutputType.FILE);
			File to2 = new File(baseDr + titleName+i+ " attempt.png");
			FileUtils.copyFile(From2, to2);

			if (i >= 1) {

				WebElement finalText1 = driver.findElement(By.xpath("(//span[@style='color:#da2128;'])[2]"));
				WebElement finalText2 = driver.findElement(By.xpath("(//span[@style='color:#da2128;'])[3]"));
				WebElement finalText3 = driver.findElement(By.xpath("(//span[@style='color:#da2128;'])[4]"));
				WebElement finalText4 = driver.findElement(By.xpath("(//span[@style='color:#da2128;'])[5]"));
				System.out.println(
						finalText1.getText() + finalText2.getText() + finalText3.getText() + finalText4.getText());
				driver.findElement(By.xpath("(//span[@class='dki-inline-variable'])[11]")).click();
			} else {
				WebElement finalText5 = driver.findElement(By.xpath("(//div[@class='dki-element-text'])[8]"));
				System.out.println(finalText5.getText());
				System.out.println("-----------------\n");
				WebElement exitBtn = driver.findElement(By.xpath("(//span[contains(text(),'Exit')])[2]"));
				Enterjs(exitBtn);
			}
		}

		driver.switchTo().window(parentid);
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h2")));
		driver.findElement(By.xpath("//div[@class='btn btn-success btn-lg clickable right']")).click();// -----again
																										// launch
		driver.switchTo().frame("ScormContent");
		driver.switchTo().defaultContent();

		Set<String> wdwHandless = driver.getWindowHandles();
		Iterator<String> itt = wdwHandless.iterator();
		String parentid1 = itt.next();
		String Childid1 = itt.next();
		driver.switchTo().window(Childid1);

		Thread.sleep(5000);

		driver.switchTo().frame("scormdriver_content");
		String defaultExhaustmsg = "You have exhausted all of the attempts to pass the course." + "\n"
				+ "Select Exit and contact your administrator to reset the course.";
		String displayedExhaustmsg = driver.findElement(By.xpath("(//div[@class='dki-element-text'])[2]")).getText();

		TakesScreenshot ts3 = (TakesScreenshot) driver;//----------Screenshot
		File From3 = ts3.getScreenshotAs(OutputType.FILE);
		File to3 = new File(baseDr + titleName+"exhausted all attempt.png");
		FileUtils.copyFile(From3, to3);
		
		if (defaultExhaustmsg.equals(displayedExhaustmsg)) {
			System.out.println("Exhaustmsg matched with given title");
		} else {
			System.out.println("Exhaustmsg does'nt matched with given title");
		}
		driver.findElement(By.xpath("(//span[@class='dki-inline-variable'])[1]")).click();

	}
}
