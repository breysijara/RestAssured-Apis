import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class petstore {

    @Before
    public void setup(){
        baseURI = "https://petstore.swagger.io/v2";
       // basePath ="/pet/addPet";
        filters(new RequestLoggingFilter(),new RequestLoggingFilter());

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }


    @Test
    public void testPostUser(){


                // Crea un cuerpo de solicitud en JSON para agregar una nueva mascota
                String requestBody = "{\n" +
                        "  \"id\": 11,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 10,\n" +
                        "    \"name\": \"gato\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 10,\n" +
                        "      \"name\": \"felinos\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}";

                // Envía una solicitud POST para agregar una nueva mascota
                        given()
                                .contentType(ContentType.JSON)
                                .body(requestBody)
                                .post("/pet")
                                .then()
                                .assertThat()
                                .statusCode(200);

                }

    @Test
    public void testPost(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id",10);
        map.put("name","Alejandra");
        map.put("status","available");

        given()
                .body(map.toString())
                .when()
                .put("/pet")
                .then()
                .statusCode(200);
    }
        }





