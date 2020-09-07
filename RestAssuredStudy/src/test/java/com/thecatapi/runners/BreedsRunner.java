package com.thecatapi.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features/breeds.feature",
        glue = {"com.thecatapi.steps.breeds"}
        )
public class BreedsRunner {
}
