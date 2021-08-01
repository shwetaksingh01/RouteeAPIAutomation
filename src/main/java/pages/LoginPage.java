package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import utils.PropertyManager;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    final WebDriver driver;

    //Constructor, as every page needs a Webdriver to find elements
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //Locating the username text box
    @FindAll({
            @FindBy(id="UsernameInput"),
            @FindBy(name="username")
    })
    WebElement username;

    //Locating the password text box
    @FindBy(id="PasswordInput")
    WebElement pswd;

    //Locating Login Button
    @FindBy(xpath="//button[@type='submit' and contains(.,'Login')]")
    WebElement loginBtn;

    @FindBy(id="pinInput")
    WebElement pinInput;

    @FindBy(xpath="//*[@id=\"verify\"]//button[@type='button' and contains(.,'Send')]")
    WebElement sendButton;

    @FindBy(xpath="//div[@class='ng-binding ng-scope' and contains(.,'Error during verification process.')]")
    WebElement errorInVerification;

     //Method that performs login action using the web elements
    public void logIn(String uName, String pwd){
        username.sendKeys(uName);
        pswd.sendKeys(pwd);
        loginBtn.click();
        driver.manage().timeouts().implicitlyWait(PropertyManager.getInstance().getTimeout(), TimeUnit.SECONDS);
    }

    public void clickSend(String otp){
        pinInput.sendKeys(otp);
        sendButton.click();
    }

    public void errorInVerificationDisplay(){
        errorInVerification.isDisplayed();

    }
}

