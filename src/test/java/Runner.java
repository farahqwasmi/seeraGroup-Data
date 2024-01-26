

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

@CucumberOptions(

        features = "src/main/resources/features",
        glue = "stepDefs",
        tags = "@Test"

)

public class Runner extends AbstractTestNGCucumberTests {

}