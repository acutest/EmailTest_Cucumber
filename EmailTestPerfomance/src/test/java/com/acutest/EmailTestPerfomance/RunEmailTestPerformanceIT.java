package com.acutest.EmailTestPerfomance;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = { "html:target/html" })
public class RunEmailTestPerformanceIT {

}
