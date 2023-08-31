import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

public class reqres {

    @Before
    public void setup(){
        baseURI = "https://reqres.in";
        basePath ="/api";
        filters(new RequestLoggingFilter(),new RequestLoggingFilter());

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    @Test
    public void logintest(){
                given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("/login")
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("token",notNullValue());



      //  System.out.println(response);
    }

    @Test
    public void testGetUser(){

        given()
                .when()
                    .get("users")
                .then()
                    .statusCode(200)
                    .body("data[1].first_name",equalTo("Janet"));
    }

    @Test
    public void testGetSingleUser(){

                given()
                .when()
                    .get("users/2")
                .then()
                    .statusCode(200)
                    .body("data.id",equalTo(2));

    }

    @Test
    public void testPostUser(){

        Map<String, Object> map = new HashMap<String,Object>();
        map.put("name","Alejandra");
        map.put("job","Costumer Success");

        given()
                .body(map.toString())
        .when()
                .post("users")
        .then()
                .statusCode(201);
    }


    @Test
    public void testdeleteUser(){

        given()
                .delete("users/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testpathUser(){

       String nameUpdate =  given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getString("name");
       assertThat(nameUpdate,equalTo("morpheus"));
    }

    @Test
    public void testputUser(){

        String nameUpdate =  given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getString("job");
        assertThat(nameUpdate,equalTo("zion resident"));
    }
}
