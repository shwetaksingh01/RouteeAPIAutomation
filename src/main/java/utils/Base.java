package  utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.HashMap;


public class Base extends Configuration {

    public static SoftAssert softAssert;
    private static Logger LOGGER = LoggerFactory.getLogger(Base.class);

    @BeforeClass(alwaysRun = true)

    public void init(){
//        DOMConfigurator.configure("log4j.properties");
        Configuration.getConfig();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        softAssert = new SoftAssert();
        String string = "*";
        String testName = method.getName();
        LOGGER.info(StringUtils.repeat(string, testName.length() + 13));
        LOGGER.info("RUNNING " + testName + "...");
        LOGGER.info(StringUtils.repeat(string, testName.length() + 13));

    }
}
