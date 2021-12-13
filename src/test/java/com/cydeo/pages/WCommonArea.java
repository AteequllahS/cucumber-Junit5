package com.cydeo.pages;

import com.cydeo.utility.DriverPool;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WCommonArea {

    @FindBy(id = "ctl00_logout")
    public WebElement logoutLink ;

    @FindBy(linkText = "View all orders")
    public WebElement viewAllOrderTab ;

    @FindBy(linkText = "View all products")
    public WebElement viewAllProductTab ;

    @FindBy(linkText = "Order")
    public WebElement orderTab ;

    public WCommonArea(){
        PageFactory.initElements(DriverPool.getDriver(), this);
    }

}
