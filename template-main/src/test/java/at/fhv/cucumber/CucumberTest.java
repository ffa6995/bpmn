package at.fhv.cucumber;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/TestProcess.feature",
        glue = "MyStepdefs")

public class CucumberTest {


}


