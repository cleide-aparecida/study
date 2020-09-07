package com.thecatapi.support;

import io.restassured.RestAssured;

public class Base {

    public Base(){
        RestAssured.baseURI = "https://api.thecatapi.com/v1";
    }
}
