package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.uiTestsUtilities;

public class HomePage {

    final WebDriver driver;

    //Constructor, as every page needs a Webdriver to find elements
    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    uiTestsUtilities uiTestsUtils=new uiTestsUtilities();

    //Locating the Routee logo
    @FindBy(xpath="//div[@class='logo-active']/img[@class='img-responsive ng-scope']")
    WebElement routeeLogo;

    @FindBy(xpath="//span[@class='account-name ng-binding']")
    WebElement userNameDisplay;

    @FindBy(xpath="//*[@id=\"UserMenu\"]//span[@class='material-icons' and contains(.,'keyboard_arrow_down')]")
    WebElement userArrowDown;

    @FindBy(xpath="//i[@class='icon-logout']")
    WebElement logout;


    public void validateLogoAndUserDisplay(){
     routeeLogo.isDisplayed();
     userNameDisplay.isDisplayed();
    }

    public void clickArrowDown(){
        userArrowDown.click();
    }
    public void clickLogout(){
        logout.click();
    }
}

