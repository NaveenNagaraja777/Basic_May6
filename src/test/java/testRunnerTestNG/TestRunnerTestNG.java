package testRunnerTestNG;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features="src/test/java/featureFiles", glue="testingacademy.stepDefinitionsFiles", tags="@ErrorTest", monochrome=true,
plugin= {"html:target/html.report"})


public class TestRunnerTestNG extends AbstractTestNGCucumberTests{
}
