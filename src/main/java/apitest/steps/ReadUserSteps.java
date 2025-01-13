package apitest.steps;

import apitest.constants.Parameters;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReadUserSteps extends BaseReqResSteps {

    private Response readUser(RequestSpecification requestSpecification, String userId) {
        return requestSpecification
                .get(String.format("%s/%s", Parameters.OBJECTS, userId));
    }

    public Response readUser(String userId) {
        return readUser(commonRequestSpec(), userId);
    }
}
