package com.eBanking.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@IncludeTags("UserAccount")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-report.html, json:target/cucumber-report.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)@ConfigurationParameter(key = "cucumber.glue", value = "com.eBanking.stepDefinitions")
@ConfigurationParameter(key = "cucumber.execution.verbose", value = "true")
public class TestRunner {
}