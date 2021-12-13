package com.cydeo.steps;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    // this run before scenario with database tag
    @Before("@database")
    public void setupDBconnection(){

        System.out.println("Setting up Hook");
        String url =ConfigReader.read("sp.database.url");
        String username =  ConfigReader.read("sp.database.username");
        String password = ConfigReader.read("sp.database.password");
        DB_Util.createConnection(url, username, password);

    }

    @After("@database")
    public void destroyConnection(){
        System.out.println("Closing up Hook");
        DB_Util.destroy();
    }

    @After
    public void resetRestAssured(){
        RestAssured.reset();
    }


    @Before("@ui")
    public void setupDriver(){
        System.out.println("THIS IS FROM @Before inside hooks class");
        // set up implicit wait or all the browser related set up
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        // maximize browser here if you wanted
        Driver.getDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        // check if scenario failed or not
        if(scenario.isFailed() ){
            // this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png",scenario.getName());

        }

        System.out.println("THIS IS FROM @After inside hooks class");
        Driver.closeBrowser();

    }

}
