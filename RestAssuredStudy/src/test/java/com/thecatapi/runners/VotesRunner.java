package com.thecatapi.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/resources/features/votes.feature",
        glue = {"com.thecatapi.steps.votes"}
        )
public class VotesRunner {
}
