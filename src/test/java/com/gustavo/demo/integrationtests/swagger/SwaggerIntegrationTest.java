package com.gustavo.demo.integrationtests.swagger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

import com.gustavo.demo.configs.TestConfigs;
import com.gustavo.demo.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest{
    @Test
    public void shouldDisplaySwaggerUiPage(){
        var content = 
            given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when().get().then().statusCode(200).extract().body().asString();

        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}
