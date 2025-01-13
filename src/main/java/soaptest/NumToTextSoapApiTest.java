package soaptest;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class NumToTextSoapApiTest {

    @Test(description = "Number to word SOAP API test")
    public void numToTextApiTest() {
        String soapEndpoint = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
        int numberToConvert = 5;

        String request = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" " +
                "xmlns:web=\"http://www.dataaccess.com/webservicesserver/\">" +
                "   <soap:Header/>" +
                "   <soap:Body>" +
                "      <web:NumberToWords>" +
                "         <web:ubiNum>" + numberToConvert + "</web:ubiNum>" +
                "      </web:NumberToWords>" +
                "   </soap:Body>" +
                "</soap:Envelope>";

        ValidatableResponse response = RestAssured.given()
                .contentType("application/soap+xml; charset=utf-8")
                .body(request)
                .post(soapEndpoint)
                .then();

        response.statusCode(HttpStatus.SC_OK);
        response.body("Envelope.Body.NumberToWordsResponse.NumberToWordsResult", equalTo("five "));
    }
}
