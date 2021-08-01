package com.routee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.*;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import smsControllerUtils.SMSControllerUtils;
import utils.Base;

public class RouteeSMSTest extends Base {
    private static final String RESPONSE_CODE_MISMATCH = "Response Code is not as expected";
    private static Logger LOGGER = LoggerFactory.getLogger(RouteeSMSTest.class);
    private int actualStatusCode;
    private String scenarios;
    private static String expTrackingId="7f09f8cc-725a-4519-a1be-d867502bb732";
    private static String expStatus="Queued";
    private static String expCreatedAt = "2017-02-17T12:23:50.403Z";
    private static String expFrom ="amdTelecom";
    private static String expTo= "+306912345678";
    private static String expBody= "A new game has been posted to the MindPuzzle. Check it out";
    private static boolean expFlash=false;
    private static int expParts=1;
    private static boolean expUnicode=false;
    private static int expCharacters=58;
    private static String body="A new game has been posted to the MindPuzzle. Check it out";
    private static String toNumberWithInsuffBalance="+912250972502";
    private static String from="amdTelecom";
    private static String toNumberWithBalance="+918639388308";



    private String generatePayload(String body, String toNumber, String from) throws JsonProcessingException {
        PostMessagePojo postMsg=new PostMessagePojo(body,toNumber,from);
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postMsg);

    }

    @DataProvider
    public Object[][] sendAnSMSDP() throws JsonProcessingException {
        String payloadForPost=generatePayload(body,toNumberWithInsuffBalance,from);

        return new Object[][]{
//        Scenario,payload,expectedStatusCode
//                {"Message successfully sent", payloadForPost,HttpStatus.SC_OK},
                {"You don't have enough balance to send the SMS.", payloadForPost,HttpStatus.SC_BAD_REQUEST},
                {"The sender id is invalid.",payloadForPost, HttpStatus.SC_BAD_REQUEST},
                {"Validation Error.",payloadForPost, HttpStatus.SC_BAD_REQUEST},
                {"A required field is missing.",payloadForPost,HttpStatus.SC_BAD_REQUEST},
                {"Invalid value of a field.",payloadForPost, HttpStatus.SC_BAD_REQUEST},
                {"You are unauthorized.", payloadForPost,HttpStatus.SC_BAD_REQUEST},
//                {"Invalid access token, maybe has expired.", payloadForPost,HttpStatus.SC_UNAUTHORIZED},
                {"Validation Error.",payloadForPost, HttpStatus.SC_BAD_REQUEST},
                {"Access denied.", payloadForPost,HttpStatus.SC_BAD_REQUEST},
//                {"Your application's access token does not have the right to use SMS service.",payloadForPost, HttpStatus.SC_FORBIDDEN}

        };
    }


   @Test(priority = 1,dataProvider="sendAnSMSDP")

    public void sendAnSMSTest(String scenario, String payload, int expectedStatusCode) {

        Response response = SMSControllerUtils.postSMS(payload);
        actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode,expectedStatusCode,RESPONSE_CODE_MISMATCH);
        LOGGER.info(response.prettyPrint());

        if (actualStatusCode == HttpStatus.SC_OK && scenarios.equals("Message successfully sent")) {
            SMSSent objSMS = response.getBody().as(SMSSent.class);
            String trackingId = objSMS.getTrackingId();
            String status = objSMS.getStatus();
            String date = objSMS.getCreatedAt();
            String from = objSMS.getFrom();
            String to = objSMS.getTo();
            String body = objSMS.getBody();
            int bodyAnalysisPart = objSMS.getBodyAnalysis().getParts();
            boolean unicode = objSMS.getBodyAnalysis().getUnicode();
            int characters = objSMS.getBodyAnalysis().getCharacters();
            boolean flash = objSMS.getFlash();
            softAssert.assertEquals(trackingId,expTrackingId);
            softAssert.assertEquals(status,expStatus);
            softAssert.assertEquals(date,expCreatedAt);
            softAssert.assertEquals(from,expFrom);
            softAssert.assertEquals(to,expTo);
            softAssert.assertEquals(body,expBody);
            softAssert.assertEquals(bodyAnalysisPart,expParts);
            softAssert.assertEquals(unicode,expUnicode);
            softAssert.assertEquals(characters,expCharacters);
            softAssert.assertEquals(flash,expFlash);
        }

        if (actualStatusCode == expectedStatusCode && scenario.equals("You don't have enough balance to send the SMS.")) {
            ErrorMessagePojo eMessage = response.getBody().as(ErrorMessagePojo.class);
            int code = eMessage.getCode();
            String developerMessage=eMessage.getDeveloperMessage();
            softAssert.assertEquals(developerMessage, "Insufficient Balance");
            softAssert.assertEquals(code,400001009);
        }

        softAssert.assertAll();
    }


}
