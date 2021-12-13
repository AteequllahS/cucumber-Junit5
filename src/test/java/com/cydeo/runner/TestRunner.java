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
//@ConfigurationParameter(key = "cucumber.glue", value = "com.cydeo.steps") -> or we can manually write it
@ConfigurationParameter(key = PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber.json, html:target/cucumber.html")
@ConfigurationParameter(key = EXECUTION_DRY_RUN_PROPERTY_NAME, value = "false") // if true => quick run for snippets
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME , value = "@database" )

public class TestRunner {


}
