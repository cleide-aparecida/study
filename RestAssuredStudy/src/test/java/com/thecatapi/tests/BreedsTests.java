package com.thecatapi.tests;

import com.thecatapi.support.Base;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BreedsTests extends Base {

    @Test
    public void testListTheCatBreeds(){
        given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
        .when()
                .get("/breeds")
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200);

    }

    @Test
    public void testSearchForBreedsByName(){
        given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
                .queryParam("q","sib")
                .log().uri()
        .when()
                .get("/breeds/search")
        .then()
                .statusCode(200)
                .log().body();

    }
}
