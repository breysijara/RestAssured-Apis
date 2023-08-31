package Petstore;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.PublicKey;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

@SerenityTest
public class PetstoreApiTest {

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
    @Title("Obtener información de usuario")
    public void testPost() throws JsonProcessingException {

    // Crea un objeto Java para agregar una nueva mascota
    Pet newPet = new Pet();
        newPet.setId(10);
        newPet.setName("doggie");
        newPet.setStatus("available");

    Category category = new Category();
        category.setId(2);
        category.setName("felinos");
        newPet.setCategory(category);

    Tag tag = new Tag();
        tag.setId(1);
        tag.setName("gatos");

        newPet.addTag(tag);

    String[] photoUrls = {"string"};
        newPet.setPhotoUrls(photoUrls);

    // Convierte el objeto Java en JSON
    ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(newPet);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Envía una solicitud POST para agregar una nueva mascota
        RestAssured
                .given()
                .body(requestBody)
                .post("/pet")
                .then()
                .assertThat()
                .statusCode(200);
}


@Test
    public void testGetID(){

    int petId = 10;

    given()
            .pathParam("petId", petId)
            .when()
            .get("/pet/{petId}")
            .then()
            .statusCode(200)
            .log().all();


}

@Test
    public void modifyExistingPet(){

        // ID de la mascota a modificar
        int petId = 10;

    // Construir el JSON de la mascota modificada
    Pet newPet = new Pet();
    newPet.setId(petId);
    newPet.setName("doggie");
    newPet.setStatus("sold");

        /* Construir el JSON de la mascota modificada
        String requestBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"status\": \"sold\"\n" +
                "}";
         */

        // Enviar la solicitud PUT para modificar la mascota
        given()
                .body(newPet)
                .when()
                .put("/pet")
                .then()
                // Validar el status code esperado
                .assertThat()
                .statusCode(200)
                // Validar el schema del response
                .and()
                .body("id", is(petId))
                .body("name", is("doggie"))
                .body("status", is("sold"));
    }
}

