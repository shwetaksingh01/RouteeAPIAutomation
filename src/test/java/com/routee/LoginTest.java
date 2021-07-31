package com.routee;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Base;
import utils.Configuration;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public class LoginTest extends Base {


    private static Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    WebDriver driver;

    String loginUrl = PropertyManager.getInstance().getURL();
    String userId = PropertyManager.getInstance().getUsername();
    String password=PropertyManager.getInstance().getPassword();


    @BeforeTest(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
 
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        //ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver(chromeOptions);
//        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(PropertyManager.getInstance().getTimeout(), TimeUnit.SECONDS);
        driver.get(loginUrl);

    }

    @Test
    public void loggedIn() {
        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.LogIn_Action(userId, password);
        System.out.println("User has clicked login button");

    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
