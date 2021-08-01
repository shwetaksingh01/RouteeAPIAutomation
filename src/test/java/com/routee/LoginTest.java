package com.routee;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.Base;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LoginTest extends Base {


    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);
    WebDriver driver;

    public static final String loginUrl = PropertyManager.getInstance().getURL();
    public static final String userName = PropertyManager.getInstance().getUsername();
    public static final String password = PropertyManager.getInstance().getPassword();
    public static final String ACCOUNT_SID = PropertyManager.getInstance().getAccountId();
    public static final String AUTH_TOKEN = PropertyManager.getInstance().getToken();
    public static final String twillioUserName = PropertyManager.getInstance().getTwillioUserId();
    public static final String twillioPassword = PropertyManager.getInstance().getTwillioPassword();
    public String otpNumber;

    @DataProvider
    public Object[][] loginDP() {

        return new Object[][]{
//        Scenario,payload,expectedStatusCode
                {"Login with UserName and Password for the account that has set the pin", userName, password},
                {"Login with UserName and Password using otp", twillioUserName, twillioPassword},
        };
    }


    @BeforeTest(alwaysRun = true)
    public void setUp() {
        LOGGER.info("********Set up the driver********");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
//      chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--start-maximized");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.merge(capabilities);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(PropertyManager.getInstance().getTimeout(), TimeUnit.SECONDS);
    }

    public static String getMessage() {
        LOGGER.info("********Get the OTP from Twilio account********");
        return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
                .filter(m -> m.getTo().equals("+12015089930")).map(Message::getBody).findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }
    @Test(dataProvider = "loginDP")
    public void loginToRouteeApp(String scenario, String userId, String password) {

        LOGGER.info("********Initialize Page Objects********");
        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        HomePage homePg = PageFactory.initElements(driver, HomePage.class);

        if (scenario.equals("Login with UserName and Password for the account that has set the pin")) {
            LOGGER.info("********Hit the Login URL********");
            driver.get(loginUrl);
            LOGGER.info("********Click Login Button after entering UserName and Password********");
            loginPg.logIn(userId, password);
            LOGGER.info("********Validate that Routee Logo and UserName is displayed********");
            homePg.validateLogoAndUserDisplay();
            LOGGER.info("********Click Arrow Down Button********");
            homePg.clickArrowDown();
            LOGGER.info("********Clicked Logout Button********");
            homePg.clickLogout();

        }

        if (scenario.equals("Login with UserName and Password using otp")) {
            driver.get(loginUrl);
            loginPg.logIn(userId, password);
            LOGGER.info("********Error In Verification Message displayed********");
            loginPg.errorInVerificationDisplay();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String smsBody = LoginTest.getMessage();
            System.out.println(smsBody);
            otpNumber = smsBody.replaceAll("[^-?0-9]+", " ");
            System.out.println(otpNumber);
//           loginPg.clickSend(otpNumber);

        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
