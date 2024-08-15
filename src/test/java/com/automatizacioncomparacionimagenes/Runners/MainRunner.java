package com.automatizacioncomparacionimagenes.Runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com/automatizacioncomparacionimagenes/StepDefinitions","com/automatizacioncomparacionimagenes/Hooks"},
        features = "src/test/resources",
        plugin = {"pretty"}
)
public class MainRunner {
    public static void main(String[] args) {
        io.cucumber.core.cli.Main.run(args, Thread.currentThread().getContextClassLoader());
    }
}