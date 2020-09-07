package com.thecatapi.steps.breeds;

import com.thecatapi.support.Base;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class BreedsCatSteps extends Base {

    private RequestSpecification requestSpecification;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Given("that there is a list of breed of cats")
    public void that_there_is_a_list_of_breed_of_cats() {
        requestSpecification = given().contentType(ContentType.JSON);
    }

    @Given("user has an authentication key")
    public void user_has_an_authentication_key() {
        requestSpecification.header("x-api-key", "a90951ad-00ef-47ec-be59-d277f883c32a").log().all();
    }

    @Given("the user wants to search a breed by name")
    public void the_user_wants_to_search_a_breed_by_name() {
        requestSpecification.queryParam("q","sib");
    }

    @When("the user does a GET request on {string}")
    public void the_does_makes_a_get_request_on_breeds_search_resource(String resource) {
        response = requestSpecification.when().get(resource);
    }

    @Then("the API returns the list of cat breeds")
    public void the_api_returns_the_list_of_cat_breeds() {
        validatableResponse = response.then().contentType(ContentType.JSON);
    }

    @Then("status code {int}")
    public void status_code(Integer int1) {
        validatableResponse.statusCode(int1);
    }
}
