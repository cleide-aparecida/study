package com.thecatapi.tests;

import com.thecatapi.support.Base;
import com.thecatapi.support.Utils;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CadastroTests extends Base {

    @Test
    public void testCadastro() throws IOException {
        Utils utils =new Utils();
        Map<String,String> map = new HashMap<String, String>();
        map.put("email","cleideprado05@gmail.com");
        map.put("appDescription","Desc");

        String payload = utils.editJson("./src/test/resources/requests/cadastro.json",map);

        given()
                .body(payload)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .post("/user/passwordlesssignup")
        .then()
                .statusCode(200);

    }
}
