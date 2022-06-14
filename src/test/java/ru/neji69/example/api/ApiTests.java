package ru.neji69.example.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.neji69.example.api.pojo.pet.Pet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {

    private final static String BASE_URL = "https://petstore.swagger.io/";
    private final static String PETS_PATH = "v2/pet/";
    private final static String FILE_DATA_PET_JSON_BODY = "datapet.json";
    private final static String FILE_PET_SCHEMA_JSON = "petschema.json";
    private final static int ID = 696969;

    private RequestSpecification requestSpec;

    @BeforeEach
    void setUp() {
        requestSpec = new RequestSpecBuilder()
                .log(LogDetail.ALL)
//                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .setBasePath(PETS_PATH)
                .build();
    }

    @Test
    void postPetTest() {
        Pet requestPet = readJsonFromResources(FILE_DATA_PET_JSON_BODY);
        requestPet.setId(ID);
        Response responsePost =
                given()
                        .spec(requestSpec)
                        .body(requestPet)

                        .when()
                        .post()

                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract().response();

        System.out.println(responsePost.jsonPath().prettyPrint());

        Response responseGet =
                given()
                        .spec(requestSpec)

                        .when()
                        .get(requestPet.getId().toString())

                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .body(matchesJsonSchemaInClasspath(FILE_PET_SCHEMA_JSON))
                        .extract().response();

        responseGet.jsonPath().prettyPrint();
        Pet responsePet = responseGet.as(Pet.class);

        Assertions.assertThat(responsePet).isEqualTo(requestPet);

    }

    private static Pet readJsonFromResources(String jsonFile) {
        Gson gson = new Gson();

        Type petType = new TypeToken<Pet>() {
        }.getType();

        InputStream inputStream = ApiTests.class.getClassLoader().getResourceAsStream(jsonFile);
        assert inputStream != null;
        return gson.fromJson(new InputStreamReader(inputStream), petType);
    }
}
