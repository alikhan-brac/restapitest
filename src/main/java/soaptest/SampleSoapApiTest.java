package soaptest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleSoapApiTest {

    @Test
    public void testSoapApi() {
        // Define the SOAP API endpoint
        String soapEndpoint = "http://example.com/service"; // Replace with your actual endpoint

        // Create the SOAP request XML
        String soapRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<YourRequest xmlns=\"http://example.com/\">"
                + "<param1>value1</param1>"
                + "<param2>value2</param2>"
                + "</YourRequest>"
                + "</soap:Body>"
                + "</soap:Envelope>";

        // Send the SOAP request
        Response response = RestAssured.given()
                .contentType("text/xml; charset=utf-8")
                .body(soapRequest)
                .post(soapEndpoint);

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBody = response.getBody().asString();

        // Further assertions can be made based on the expected response
        Assert.assertTrue(responseBody.contains("ExpectedValue"),
                "Response does not contain expected value");
    }
}