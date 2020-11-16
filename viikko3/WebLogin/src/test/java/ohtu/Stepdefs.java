package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    }    
    
    @Given("user with username {string} with password {string} is successfully created")
    public void newUserSuccessfullyCreated(String username, String password) {
        newUserIsSelected();
        createNewUser(username, password, password);
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        element = driver.findElement(By.linkText("logout"));
        element.click();
    }    
    
    @Given("user with username {string} and password {string} is tried to be created")
    public void newUserUnsuccessfullyCreated(String username, String password) {
        newUserIsSelected();
        createNewUser(username, password, password);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordGiven(String username, String password) {
        createNewUser(username, password, password);
    }    
    
    @When("a too short username {string} and a valid password {string} and matching password confirmation are entered")
    public void unvalidUsernameAndValidPasswordGiven(String username, String password) {
        createNewUser(username, password, password);
    }    
    
    @When("a valid username {string} and an unvalid password {string} and matching password confirmation are entered")
    public void validUsernameAndUnvalidPasswordGiven(String username, String password) {
        createNewUser(username, password, password);
    }    
    
    @When("a valid username {string} and a valid password {string} and a different password confirmation are entered")
    public void passwordAndPasswordConfirmationNotMatching(String username, String password) {
        createNewUser(username, password, username);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
    
    @Then("a new user is created")
    public void userIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @Then("user is not created and error {string} is reported")
    public void userNotCreated(String pageContent) throws Throwable {
        systemWillRespond(pageContent);
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createNewUser(String username, String password, String confirmation) {
        WebElement element = driver.findElement(By.name("username"));  
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(confirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    } 
}
