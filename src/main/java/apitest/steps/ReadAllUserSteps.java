package apitest.steps;

import apitest.constants.Parameters;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReadAllUserSteps extends BaseReqResSteps{

    public Response readAll(RequestSpecification requestSpecification){
        return requestSpecification.get(Parameters.OBJECTS);
    }

    public Response readAll(){
        return readAll(commonRequestSpec());
    }
}
