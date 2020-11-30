package test.java.restApi;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Store {
    public Logger logger = LogManager.getLogger(Store.class);
    int newId;
    RequestSpecification requestSpec = given().
            baseUri("https://petstore.swagger.io/v2");


    @Epic("Store endpoint")
    @Feature("Access to Petstore orders")
    @Story("Place an order for a pet")
    @Test
    public void createTest() {
         given().
                contentType(ContentType.JSON).
                spec(requestSpec).
                body(new File("src/test/resources/post.json")).
                when().
                post("/store/order").
                then().
                statusCode(200);

    }

    @Story("Find purchase order by ID")
    @Test
    public void getOrderTest() {
        given().
                spec(requestSpec).
                when().
                get("/store/order/9").
                then().
                assertThat().
                statusCode(200).
                assertThat().
                body(matchesJsonSchemaInClasspath("getBodyValidation.json"));

    }

    @Story("Delete purchase order by ID")
    @Test(dependsOnMethods = "createTest")
    public void deleteTest() {

        given().
                spec(requestSpec).
                when().
                delete("/store/order/" + newId).
                then().
                statusCode(200);
    }

    @Story("Returns pet inventories by status")
    @Test
    public void getInventoryTest() {
        given().
                spec(requestSpec).
                when().
                get("/store/inventory").
                then().
                statusCode(200);

    }
}
