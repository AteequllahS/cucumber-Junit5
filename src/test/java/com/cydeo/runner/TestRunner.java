package com.cydeo.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

//JUnit5 Test Runner setup
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/rest") //feather file path
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.cydeo.steps")
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber.json, html:target/cucumber.html")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME , value = "@smoke" )
public class TestRunner {


}
