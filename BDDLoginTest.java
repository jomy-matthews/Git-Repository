package stepImplementations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.concurrent.TimeUnit;

//import junit.framework.Assert;
import org.junit.Assert;
/// This import of junit assertion works ( The other one gave a strike through )
//Go to Window → Show View → general → Problems and you will see the list of errors. In my case, some of the JAR files were missing on the build path. Configuring Build Path solved the "Error exists in your project" issue. In the 'problems' view, right click on the error and quick fix.

public class BDDLoginTest {
	WebDriver driver;
	//Given user is on the login page
	
	@Given("^user is on the login page$")
	public void user_is_on_the_login_page () { 
		System.out.println("User is on the Login Page#####");
		//open driver 
		driver = utilities.DriverFactory.open("chrome");
		driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");

	}
	
	
	//When user enters correct username and correct password
	@When ("^user enters correct username and correct password$")
	public void user_enters_correct_username_and_password () { 
		System.out.println("User enters username and password######");
		driver.findElement(By.name("ctl00$MainContent$txtUserName")).sendKeys("tim@testemail.com");
		driver.findElement(By.name("ctl00$MainContent$txtPassword")).sendKeys("trpass");
		driver.findElement(By.name("ctl00$MainContent$btnLogin")).click();		
		
	}
	
	@When("^user enters email (.*)$")
		public void user_enters_username(String username) {
		System.out.println("TESTING: " + username);
		driver.findElement(By.name("ctl00$MainContent$txtUserName")).sendKeys(username);

	}
	
	@And ("^user enters password (.*)$")
		public void user_enters_password(String password) {
		System.out.println("TESTING: " + password);
		driver.findElement(By.name("ctl00$MainContent$txtPassword")).sendKeys(password);
		click_login();
		System.out.println("CLICCKED ON LOGIN BUTTON");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	//@And ("^user clicks login button$")	
	public void click_login() {
		driver.findElement(By.name("ctl00$MainContent$btnLogin")).click();
		
	}
	
	
	//Then user gets confirmation
	@Then("^user gets confirmation$")
	public void user_gets_confirmation () { 
		System.out.println("User gets confirmation########");
		Assert.assertTrue(driver.findElement(By.id("conf_message")).getText().contains("success"));
	}
 


	@After
	public void tearDown() {
		driver.quit();
	}
  
}
