package utils;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RestAssuredUtil {

    private static Log LOGGER = LogFactory.getLog(RestAssuredUtil.class);
   public static Response post(String hostUrl, String uri, String payload,String token) {

        Header authHeader = new Header("Authorization", "Bearer "+token);
        LOGGER.info(authHeader);
        logRequestURL(hostUrl, uri);
        Response response = RestAssured.given().header(authHeader).contentType(ContentType.JSON).relaxedHTTPSValidation()
                .body(payload).when().post(hostUrl + uri).then().extract().response();
        logResponseBodyAndStatusCode(response);
        return response;

    }

    private static void logRequestURL(String url, String path) {

        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[3];// maybe this number needs to be corrected
        String methodName = e.getMethodName();
        LOGGER.info(String.format("\t%s : %s%s", methodName, url, path));
    }

    public static void logResponseBodyAndStatusCode(Response response) {
        String callingMethodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        LOGGER.info(String.format("\t%s response code: %d", callingMethodName, response.statusCode()));
        LOGGER.info(String.format("\t%s response body: %s", callingMethodName, sanitizeResponse(response)));
    }

    private static String sanitizeResponse(Response response) {
        return response.asString()
                .replaceAll("\"access_token\":\".*\",\"token_type\"", "\"access_token\":\"******\",\"token_type\"");
    }


}