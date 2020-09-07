package com.thecatapi.tests;

import com.thecatapi.support.Base;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class CategoriesTests extends Base {

    @Test
    public void testListTheCategories(){
        given()
                .contentType(ContentType.JSON)
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .queryParam("limit", 3)
                .queryParam("page", 1)
                .log().uri()
        .when()
                .get("/categories")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .assertThat().body("size()", is(3));

    }
}
