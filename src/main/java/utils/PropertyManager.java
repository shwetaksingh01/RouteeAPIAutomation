package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Description: PropertyManager class reads global configurations and use them during test execution.
//**********************************************************************************************************
public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock = new Object();
    private static String propertyFilePath = System.getProperty("user.dir") + "/src/main/resources/uiEnv.properties";
    private static String url;
    private static String userName;
    private static String password;
    private static long implicitTimeout;
    private static String accountId;
    private static String token;
    private static  String twillioUserId;
    private static String twillioPassword;

    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }

    //Get all configuration data and assign to related fields.
    private void loadData() {
        //Declare a properties object
        Properties prop = new Properties();
        //Read configuration.properties file
        try {
            prop.load(new FileInputStream(propertyFilePath));
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
        //Get properties from configuration.properties
        url = prop.getProperty("loginUrl");
        userName = prop.getProperty("userId");
        password = prop.getProperty("password");
        implicitTimeout = Long.parseLong(prop.getProperty("implicitTimeout"));
        accountId = prop.getProperty("accountId");
        token = prop.getProperty("token");
        twillioUserId=prop.getProperty("twillioUserId");
        twillioPassword=prop.getProperty("twillioPassword");


    }

    public String getURL() {
        return url;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public long getTimeout() {
        return implicitTimeout;
    }


    public String getAccountId() {
        return accountId;
    }

    public String getToken() {
        return token;
    }

    public String getTwillioUserId() {
        return twillioUserId;
    }

    public String getTwillioPassword() {
        return twillioPassword;
    }
}
