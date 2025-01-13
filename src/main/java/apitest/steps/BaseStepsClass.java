package apitest.steps;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

@UtilityClass
public class BaseStepsClass {

    public RequestSpecification requestSpec(String baseUri) {
        return given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8));
    }
}
