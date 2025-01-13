package soaptest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryCodeSoapApiTest {
    private static final Logger logger = LoggerFactory.getLogger(CountryCodeSoapApiTest.class);

    @Test(description = "Country code SOAP API test")
    public void testCapitalCity() {
        // Define the SOAP API endpoint
        String soapEndpoint = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";

        // Create the SOAP request XML for the CapitalCity operation
        String countryISOCode = "US"; // Replace with the desired ISO code
        String soapRequest = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + "<CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">"
                + "<sCountryISOCode>" + countryISOCode + "</sCountryISOCode>"
                + "</CapitalCity>"
                + "</soap:Body>"
                + "</soap:Envelope>";

        // Send the SOAP request
        Response response = RestAssured.given()
                .contentType("text/xml; charset=utf-8")
                .body(soapRequest)
                .post(soapEndpoint);

//        logger.info("Response Body: {}", response.getBody().asString());

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        String responseBody = response.getBody().asString();

        // Check if the response contains the expected capital city name
        Assert.assertTrue(responseBody.contains("Washington"),
                "Response does not contain the expected capital city.");
    }
}
