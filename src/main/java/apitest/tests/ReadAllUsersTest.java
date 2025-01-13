package apitest.tests;

import apitest.model.UserData;
import apitest.steps.ReadAllUserSteps;
import apitest.steps.ResponseSteps;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import apitest.steps.ReadUserSteps;

public class ReadAllUsersTest {
    private final String NAME = "Apple MacBook Pro 16";
    private final ReadUserSteps readUserSteps = new ReadUserSteps();
    private final ReadAllUserSteps readAllUserSteps = new ReadAllUserSteps();

    @Test
    public void readAllUserTest() {
        Response response = readAllUserSteps.readAll();
        ResponseSteps.checkStatusCode(response, HttpStatus.SC_OK);
        UserData[] userData = response.as(UserData[].class);
    }

    @Test
    public void readOneUserTest() {
        Response response = readUserSteps.readUser("7");
        ResponseSteps.checkStatusCode(response, HttpStatus.SC_OK);
        UserData userData = response.as(UserData.class);
        checkResponseMatchesExpected(userData, NAME);
    }

    private void checkResponseMatchesExpected(UserData userData, String expectedName) {
        Assert.assertEquals(userData.getName(), expectedName);
    }
}
