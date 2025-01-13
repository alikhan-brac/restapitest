package soaptest;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class CalculatorSoapApiTest {

    @Test
    public void calculatorTest() {
        String endpoint = "http://www.dneonline.com/calculator.asmx";
        int firstNum = 2;
        int secondNum = 3;
        String request =
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" " +
                        "xmlns:tem=\"http://tempuri.org/\">\n" +
                        "   <soap:Header/>\n" +
                        "   <soap:Body>\n" +
                        "      <tem:Add>\n" +
                        "         <tem:intA>" + firstNum + "</tem:intA>\n" +
                        "         <tem:intB>" + secondNum + "</tem:intB>\n" +
                        "      </tem:Add>\n" +
                        "   </soap:Body>\n" +
                        "</soap:Envelope>";

        ValidatableResponse response = RestAssured.given()
                .contentType("text/xml; charset=utf-8")
                .body(request)
                .post(endpoint)
                .then();

        response.statusCode(HttpStatus.SC_OK);
        response.body("Envelope.Body.AddResponse.AddResult", equalTo("5"));
    }
}
