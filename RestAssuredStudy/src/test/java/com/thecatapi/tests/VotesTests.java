package com.thecatapi.tests;

import com.thecatapi.support.Base;
import com.thecatapi.support.Utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VotesTests extends Base {

    private int id = -1;

    @BeforeAll
    public  void testCreateVote()  {

        Utils utils =new Utils();
        Map<String,String> map = new HashMap<String, String>();
        map.put("image_id","asf2");
        map.put("sub_id","my-user-1234");
        map.put("value","1");

        String payload = null;
        try {
            payload = utils.editJson("./src/test/resources/requests/vote.json",map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
                .body(payload).log().all()
                .when()
                .post("/votes");
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body();
        id = response.body().jsonPath().getInt("id");
    }

    @Test
    public void testGetYourVotes(){

        Map<String,Object> myHeader = new HashMap<String, Object>();
        myHeader.put("sub_id","my-user-1234");
        //myHeader.put("limit",2);
        //myHeader.put("page",1);

        given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
                .queryParams(myHeader)
                .log().uri()
        .when()
                .get("votes")
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body();
    }

    @Test
    public void testDeleteVote(){
        given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
                .pathParam("id", this.id)
                .log().all()
        .when()
                .delete("votes/{id}")
        .then()
                .statusCode(200)
                .body("message", equalTo("SUCCESS"));

    }

    @Test
    public void testGetVoteById(){
        given()
                .header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a")
                .contentType(ContentType.JSON)
                .pathParam("id", this.id)
                .log().all()
        .when()
                .get("votes/{id}")
        .then()
                .statusCode(200);

    }
}
