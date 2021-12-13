package com.cydeo.steps;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DriverPool;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C3_SpartanUIStepDefs {


    @Given("user navigate to home page")
    public void user_navigate_to_home_page() {

        DriverPool.getDriver().get(ConfigReader.read("sp.base.url"));


    }


    @Then("user should see below links")
    public void user_should_see_below_links(List<String> expectedLinks){

        System.out.println("links = " + expectedLinks);

        List<WebElement> allLinks = DriverPool.getDriver().findElements(By.xpath("//div[@class='display-4 card']//a"));

        List<String> actualLinkTexts = BrowserUtil.getAllText(allLinks) ;
        // check the equality of the link texts
        assertEquals( expectedLinks , actualLinkTexts ) ;



    }
}
