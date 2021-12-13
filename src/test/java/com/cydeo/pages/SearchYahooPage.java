package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://search.yahoo.com/
public class SearchYahooPage {

    @FindBy(id = "yschsp")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@id='sf']/button/span")
    public WebElement searchIcon;

    @FindBy(id = "ysignin")
    public WebElement signinLink;

    public SearchYahooPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}