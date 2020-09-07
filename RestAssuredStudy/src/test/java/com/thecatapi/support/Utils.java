package com.thecatapi.support;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Utils{

    public String editJson(String jsonPath, Map<String,String> keyValuesMap) throws IOException {
        File file =  new File(jsonPath);
        FileInputStream inputStream = new FileInputStream(file);

        JSONObject request = new JSONObject(IOUtils.toString(inputStream,"UTF-8"));

        for(int i=0; i < keyValuesMap.size(); i++){
            String atualKey = (String) keyValuesMap.keySet().toArray()[i];
            String atualValue = keyValuesMap.get(atualKey);
            request.put(atualKey,atualValue);
        }

        return request.toString();
    }

}
