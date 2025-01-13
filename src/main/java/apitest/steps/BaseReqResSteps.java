package apitest.steps;

import apitest.constants.Parameters;
import io.restassured.specification.RequestSpecification;

public class BaseReqResSteps {
    private String base;

    BaseReqResSteps() {
        this.base = Parameters.BASE_URL;
    }

    public RequestSpecification commonRequestSpec() {
        return BaseStepsClass.requestSpec(base);
    }
}
