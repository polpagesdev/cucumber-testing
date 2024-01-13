package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner {
    // This class does not need any code. Its purpose is to serve as an entry point for Cucumber tests.
}
