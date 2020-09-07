package com.thecatapi.steps.votes;

import com.thecatapi.support.Base;
import com.thecatapi.support.Utils;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class VoteSteps extends Base {

    private RequestSpecification requestSpecification;
    private Response response;
    private ValidatableResponse validatableResponse;

    private int id = -1;

    @Given("that the user set image_id as {string} and sub_id as {string} and value as {string} to create the vote")
    public void that_the_user_passes_the_payload_to_create_the_vote(String image_id, String sub_id, String value) {
        Utils utils =new Utils();
        Map<String,String> map = new HashMap<String, String>();
        map.put("image_id",image_id);
        map.put("sub_id",sub_id);
        map.put("value",value);

        String payload = null;
        try {
            payload = utils.editJson("./src/test/resources/requests/vote.json",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestSpecification = given().contentType(ContentType.JSON).body(payload);
    }

    @Given("user has an authentication key")
    public void user_has_an_authentication_key() {
        requestSpecification.header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a").log().all();
    }

    @When("the user does a POST request on {string}")
    public void the_user_does_a_post_request_on(String resource) {
        response = requestSpecification.when().post(resource);
    }

    @Then("the API returns the vote_id")
    public void the_api_returns_an_id() {
        id = response.body().jsonPath().getInt("id");
    }

    @Then("status code {int}")
    public void status_code(Integer int1) {
        response.then().statusCode(int1);
    }
}
