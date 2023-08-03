package org.dssproject;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Argument;

public class BaseClass {

	static WebDriver driver;

	public static WebDriver browserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	public static void urlLaunch(String url) {
		driver.get(url);	}

	public static void implicityWait(long sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static void simpleAlert() {
		driver.switchTo().alert().accept();
	}
	
	public static void confirmAlertAccept() {
		driver.switchTo().alert().accept();
	}

	public static void confirmAlertDismiss() {
		driver.switchTo().alert().dismiss();
	}
	
	public static void promptAlert(String sendValue) {
		Alert Al = driver.switchTo().alert();
		Al.sendKeys(sendValue);
		Al.accept();
	}
	
	public static void movetoElement(WebElement To ) {
		Actions Ac = new	Actions(driver);
		Ac.moveToElement(To).perform();
	}
	
	public static void dragandDrop(WebElement From, WebElement To ) {
		Actions Ac = new	Actions(driver);
		Ac.dragAndDrop(From, To).perform();
	}
	
	public static void ClickandHoldRelease(WebElement holdpoint, WebElement releasepoint) {
		Actions Ac = new	Actions(driver);
		Ac.clickAndHold(holdpoint).release(releasepoint).perform();
	}
	
	public static void SelectbyIndex(String drpidname, int indexvalue) {
		WebElement selectdrp = driver.findElement(By.id(drpidname));
		Select Sc = new Select(selectdrp);
		Sc.selectByIndex(indexvalue);
	}
	
	public static void SelectbyValue(String drpidname, String value) {
		WebElement selectdrp = driver.findElement(By.id(drpidname));
		Select Sc = new Select(selectdrp);
		Sc.selectByValue(value);
	}
	
	public static void SelectbyVisibleText(String drpidname, String Text) {
		WebElement selectdrp = driver.findElement(By.id(drpidname));
		Select Sc = new Select(selectdrp);
		Sc.selectByVisibleText(Text);
	}
	public static void Selectgetoptios(String drpidname) {
		WebElement selectdrp = driver.findElement(By.id(drpidname));
		Select Sc = new Select(selectdrp);
		List<WebElement> options = Sc.getOptions();
		for(int i=0;i<options.size();i=i+1) {
		WebElement Op = options.get(i);
		String St = Op.getText();
		System.out.println(St);}
	}
	
	public static  void userName(String mailId) {
		WebElement username = driver.findElement(By.id("email"));
		username.sendKeys(mailId);
	}
	public static  void passwordd(String pass) {
		WebElement username = driver.findElement(By.id("password"));
		username.sendKeys(pass);
	}
	
	public static void windowhandlesToChild1() {
		Set<String> wdwHandles = driver.getWindowHandles();
		Iterator<String> it = wdwHandles.iterator();
		String parentid = it.next();
		String Childid = it.next();
		driver.switchTo().window(Childid);
	}
	
	public static void screenShot() throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to = new File("C:\\Users\\dell\\Documents\\selenium screenshots\\image1.png");
		FileUtils.copyDirectory(from, to);

	}
	public static void enterRobot() throws AWTException {
	Robot r = new Robot();
	r.keyPress(KeyEvent.VK_ENTER);
	r.keyRelease(KeyEvent.VK_ENTER);
}
	public static void robotScreenshot() throws AWTException {
		Robot r =new Robot();

		r.keyPress(KeyEvent.VK_WINDOWS);
		r.keyPress(KeyEvent.VK_PRINTSCREEN);
		r.keyRelease(KeyEvent.VK_PRINTSCREEN);
		r.keyRelease(KeyEvent.VK_WINDOWS);
	}
	public static void Enterjs(WebElement point) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", point);

	}
	
	
	
	
}
