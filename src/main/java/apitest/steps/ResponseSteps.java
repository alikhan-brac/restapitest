package apitest.steps;

import io.qameta.allure.Step;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import java.util.List;

@UtilityClass
public class ResponseSteps {

    public void checkStatusCode(Response response, int statuscode) {
        response.then().assertThat().statusCode(statuscode);
    }

    @Step("Check that response is empty")
    public void checkResponseEmpty(Response response) {
        String body = response.body().asString();
        Assert.assertEquals(body, StringUtils.EMPTY, "Response should be empty");
    }

    @Step("Extract Multi-value header")
    public List<String> extractMultiValueHeader(Response response, String headerName) {
        return response.headers().getValues(headerName);
    }

    @Step("Extract Multi-value cookie")
    public List<String> extractMultiValueCookie(Response response, String cookieName) {
        return response.getDetailedCookies().getValues(cookieName);
    }

    @Step("Extract cookie")
    public Cookie extractCookie(Response response, String cookieName) {
        return response.getDetailedCookies().get(cookieName);
    }
}
