package smsControllerUtils;



import io.restassured.response.Response;
import pojo.Token;
import utils.Configuration;
import utils.RestAssuredUtil;


public class SMSControllerUtils extends RestAssuredUtil {

    public static final String BASE_URI = "https://connect.routee.net";
    public static final String SMS_URI = "/sms";
    public static Token token= Configuration.routeeAuth0Login();



    public static Response postSMS(String payload) {
        return RestAssuredUtil.post(BASE_URI, SMS_URI, payload,token.getAccess_token());
    }


}
