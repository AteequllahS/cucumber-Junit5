package com.cydeo.pages;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    @FindBy( xpath = "//div[@class='display-4 card']//a")
    private List<WebElement> allLinks ;

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }



    public void goTo() {

         Driver.getDriver().get(ConfigReader.read("sp.base.url"));

    }

    public List<String> getAllLinks() {

        return  BrowserUtil.getAllText( this.allLinks  ) ;

//        List<WebElement> allLinks =
//                Driver.getDriver().findElements(By.xpath("//div[@class='display-4 card']//a"));
//        List<String> actualLinkTexts = BrowserUtil.getAllText(allLinks) ;
//        return actualLinkTexts ;
    }

}
