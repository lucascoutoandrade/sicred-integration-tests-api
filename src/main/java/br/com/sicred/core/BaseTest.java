package br.com.sicred.core;


import com.google.common.collect.ImmutableMap;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.logging.Logger;
import static br.com.sicred.core.Constantes.*;
import static br.com.sicred.utils.AllureEnvironmentWriter.allureEnvironmentWriter;


public class BaseTest {

    private static final Logger log = Logger.getLogger( BaseTest.class.getName() );

    @BeforeAll
    public static void SetupAPI() {
        log.info("API setup...");

        RestAssured.baseURI = APP_BASE_URL;
        RestAssured.port = APP_PORT;
        //     RestAssured.basePath = APP_BASE_PATH;

        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(APP_CONTENT_TYPE);
        RestAssured.requestSpecification =requestBuilder.build();

        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = responseBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).build();


    }


    @AfterAll
    public static void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("API.Service", "Simulação/Restrição")
                        .put("API.Version", "1.0")
                        .put("Ambiente ", "Homologção")
                        .build());


    }

}
