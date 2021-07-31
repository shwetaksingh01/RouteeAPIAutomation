package utils;

import pojo.*;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Configuration {
    static Properties prop;
    static FileInputStream fis;
    static final String envPropFilepath = System.getProperty("user.dir") + "/src/main/resources/env.properties";
    public static Response response = null;

    private static final Log LOGGER = LogFactory.getLog(Configuration.class);

    @BeforeClass(alwaysRun = true)
    public static void getPropertyFile() {
        File file = new File(envPropFilepath);
        try {
            fis = new FileInputStream(file);
            prop = new Properties();
            prop.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getConfig() {
        getPropertyFile();

    }

    public static String readProperty(String key) {
        return prop.getProperty(key);
    }

    public static Token routeeAuth0Login() {
        Configuration conf = new Configuration();
        String authUrl = conf.readProperty("oauthUrl");
        String clientId = conf.readProperty("appClientId");
        String clientSecret = conf.readProperty("appClientSecret");
        RestAssured.useRelaxedHTTPSValidation();
        String base64Secret;
        base64Secret = Base64.encodeBase64String(((clientId) + ":" + (clientSecret)).getBytes(StandardCharsets.UTF_8));
        Header h1 = new Header("Content-Type", "application/x-www-form-urlencoded");
        Header h2 = new Header("Authorization", "Basic " + base64Secret);
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        response = RestAssured.given().header(h1).header(h2).queryParams(map).post(authUrl);
        RestAssuredUtil.logResponseBodyAndStatusCode(response);
        Token token = new Token();
        JsonPath jsonPath = response.getBody().jsonPath();
        token.setAccess_token(jsonPath.getString("access_token"));
        token.setToken_type(jsonPath.getString("token_type"));
        token.setExpires_in(jsonPath.getString("expires_in"));
        token.getToken_type();
        token.getExpires_in();
        if (StringUtils.isEmpty(token.getAccess_token())) {
            LOGGER.warn("Failed to get the UAA token for {} .Response :{}");
        }
        return token;
    }

}


