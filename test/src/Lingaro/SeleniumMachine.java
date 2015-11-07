
package Lingaro;

import java.util.regex.Pattern;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.MoveMouseAction;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.reporters.jq.TimesPanel;

public class SeleniumMachine {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    baseUrl = "http://www.smartclient.com/";
  }
  
  @Rule
  public TimeTestWatcher watcher = new TimeTestWatcher();
  			
  @Test
  public void Exercise_1() throws Exception {
    driver.get(baseUrl + "/smartgwt/showcase/#featured_tile_filtering");
    WebElement splitBar = findDynamicElement(By.xpath(".//*[@id='isc_4N']/table/tbody/tr/td"),10);
    splitBar.click();
    
    WebElement searchAnimalInput = driver.findElement(By.id("isc_29"));
    searchAnimalInput.clear();
    searchAnimalInput.sendKeys("a");
    
    WebElement sortSelect = driver.findElement(By.id("isc_2W"));
    sortSelect.click();
    
    WebElement lifeSpanSort = driver.findElement(By.id("isc_PickListMenu_0_row_1"));
    lifeSpanSort.click();
    
    WebElement ascendingSort = driver.findElement(By.id("isc_35"));
    ascendingSort.click();
    
    WebElement slider = driver.findElement(By.id("isc_21"));
    Actions builder = new Actions(driver);   
    builder.dragAndDropBy(slider, -25, 0).build().perform();
    
    Thread.sleep(1000);
    int elementsFind = driver.findElements(By.xpath("html/*//div[6]/div[@aria-hidden='true']")).size();
    System.out.println("After filter found: "+elementsFind);
    Assert.assertTrue(elementsFind > 13);
    
  }
  @Test
  public void Exercise_2() throws Exception {
	  driver.get(baseUrl + "/smartgwt/showcase/#featured_dropdown_grid_category");
	  WebElement dropDown = findDynamicElement(By.xpath("html//tbody[2]/tr[2]/td[2]/table/tbody/tr/td[1]/div"),10);
	  dropDown.click();
	  WebElement listDiv = findDynamicElement(By.xpath("html/body/div[5]/div/div[2]"),10);
	  
	 	  int itterator= 0;
	  while (true) {
		 Thread.sleep(75);
		 //findDynamicElement(By.xpath(".//*[@id='isc_PickListMenu_0_row_"+itterator+"']/td[1]/div"),10);
		 Boolean firstRow = isElementPresent(By.xpath(".//*[@id='isc_PickListMenu_0_row_"+itterator+"']/td[1]/div[contains(text(),'Exercise')]"));
		 Boolean secondRow =isElementPresent(By.xpath(".//*[@id='isc_PickListMenu_0_row_"+itterator+"']/td[2]/div[contains(text(),'Ea')]"));
		 
		if (firstRow&&secondRow) {
			
			WebElement thridRow = driver.findElement(By.xpath(".//*[@id='isc_PickListMenu_0_row_"+itterator+"']/td[3]/div"));
			
			 if(Float.valueOf(thridRow.getText()) > 1.20)
			 {
				 thridRow.click();
				 System.out.println("Click on the condition Element");
				 Thread.sleep(2000);
				 break;
			 }
		}
		listDiv.sendKeys(Keys.DOWN);
		  
		itterator++;
	  }
		
	}
	
  @Test
  public void Exercise_3() throws Exception {
	  driver.get(baseUrl + "/smartgwt/showcase/#featured_nested_grid");  
	  
	  WebElement splitBar = findDynamicElement(By.xpath(".//*[@id='isc_33']/table/tbody/tr/td"),10);
	    splitBar.click();
	  
	  WebElement sortList = findDynamicElement(By.xpath("html/body/div[2]/div/div/div/div/div[1]/div[2]/div/div/div[4]/div[1]/div/div/div/div[1]/div/div/div[1]/div[2]/table/tbody/tr/td"), 10);
	  sortList.click();
	  
	  WebElement firstElement = findDynamicElement(By.xpath(".//*[@id='isc_1Utable']/tbody[2]/tr[1]/td[2]/div"), 10);
	  firstElement.click();
	  
	  WebElement scrollDown = findDynamicElement(By.name("isc_38blank10"),10);
	  
	  int correctionsElements = driver.findElements(By.xpath(".//*[@id='isc_1Utable']/tbody[2]/tr/td[2]/div[contains(text(),'Correction')] ")).size();
	  int itterator = 1;
	  int insideChecker = 0;
	  while (true) {
		  
		  WebElement divFolder = findDynamicElement(By.xpath(".//*[@id='isc_1Utable']/tbody[2]/tr["+itterator+"]/td[2]/div"), 10);
		  
		  WebElement expandDivFolder = findDynamicElement(By.xpath(".//*[@id='isc_1Utable']/tbody[2]/tr["+itterator+"]/td[1]/div/span"),10);
		 
		  if (divFolder.getText().contains("Correction"))
		  {
			  insideChecker++;
			  expandDivFolder.click();
			  
			  Thread.sleep(3000);
			  
			  int elementForEdit = driver.findElements(By.xpath(".//*/tbody[2]/tr/td[3]/div")).size();
			  for (int i = 1; i <= elementForEdit; i++) {
				  
				  WebElement elementToEdit =  findDynamicElement(By.xpath(".//*/tbody[2]/tr["+i+"]/td[3]/div"),10);
				  elementToEdit.click();
				  
				  WebElement textArea = findDynamicElement(By.xpath("//tbody[2]/tr[2]/td[@class = 'formCellFocused']/textarea"),10);
				  textArea.click();
				  textArea.clear();
				  textArea.sendKeys(i+" "+RandomStringUtils.randomAlphabetic(10));
				  WebElement saveButton = findDynamicElement(By.xpath(".//*[text() ='Save']"),10);
				  saveButton.click();
				  
		  }
		  WebElement closeButton = findDynamicElement(By.xpath(".//*[text() ='Close']"),10);
		  closeButton.click();
		  
			  
		  }
		  if(itterator > 10){
			  scrollDown.click();
			  }
		  itterator++;
		  if(insideChecker == correctionsElements)
		  {
		   break;
		  }
	  }
	  
	
  }
  
	  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
  
  private WebElement findDynamicElement(By by, int timeOut) {
	    WebDriverWait wait = new WebDriverWait(driver, timeOut);
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	    return element;
	}

  
  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}




//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

/*public class SeleniumMachine {
	
	public void PageHolder()
	{
		String basePage = "http://www.smartclient.com/smartgwt/showcase";
		String TitleSortFiltering = "isc_SideNavTree_0_valueCell7";
	}
	
	public static void main (String[] args) throws InterruptedException {
		
	WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("http://www.smartclient.com/smartgwt/showcase");
	driver.findElement(By.id("isc_SideNavTree_0_valueCell7")).click();
	Thread.sleep(5000);
	driver.findElement(By.id("isc_29")).sendKeys("a");
	driver.findElement(By.id("isc_2W")).click();
	driver.findElement(By.id("isc_PickListMenu_0_row_1")).click();
	
	
	
	//WebElement myDynamicElement = (new WebDriverWait(driver, 10))
			//.until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
}
	
}
*/