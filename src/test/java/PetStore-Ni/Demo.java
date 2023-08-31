import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    @Test
    public void testGetPet()
    {
        baseURI="https://petstore.swagger.io/v2";
        System.out.println("obteniendo el pet");
        String body= given().when().get("/pet/77").then().
                statusCode(200).
                extract().body().asString();
        System.out.println(body);
        this.testPutPet();

    }
    @Test
    public void testGetxPet()
    {
        baseURI="https://petstore.swagger.io/v2";
        System.out.println("obteniendo el pet");
        String body= given().when().get("/pet/77").then().
                statusCode(200).
                extract().body().asString();
        System.out.println(body);
    }
    @Test
    public void testPutPet()
    {
        baseURI="https://petstore.swagger.io/v2";

        JSONObject jo = new JSONObject();


        jo.put("id",77);
        jo.put("name","jostin");
        jo.put("status","available");

        given().accept(ContentType.JSON).
                contentType(ContentType.JSON).
                log().all().
                body(jo.toString()).
                when().put("/pet").then().
                log().all().
                statusCode(200);
        this.testGetxPet();

    }


    @Test
    public void testPostPet()
    {
        baseURI="https://petstore.swagger.io/v2";

        JSONObject jo = new JSONObject();
        JSONObject cat = new JSONObject();
        JSONObject tags = new JSONObject();
        List<String> px = new ArrayList<>();
        List<TagsClass> object = new ArrayList<TagsClass>();
        TagsClass ob=new TagsClass();

        px.add("string");
        jo.put("id",77);
        jo.put("name","firulaisx");
        jo.put("status","available");
        cat.put("id",77);
        cat.put("name","cat1");
        ob.setId(1);
        ob.setName("nii");
        object.add(ob);
        tags.put("name","t1");
        jo.put("category",cat);
        jo.put("tags",object);
        jo.put("photoUrls",px);
        given().accept(ContentType.JSON).
                log().all().
                contentType(ContentType.JSON).
                body(jo.toString()).
                when().post("/pet").then().
                log().all().
                statusCode(200);
        this.testGetPet();
    }
}
