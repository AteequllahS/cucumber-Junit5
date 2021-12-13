package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WAllOrderPage {

    @FindBy(xpath = "//h2[normalize-space(.)= 'List of All Orders']" )
    public WebElement header ;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllButton ;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement unCheckAllButton ;

    @FindBy(xpath = "//table[@class='SampleTable']/tbody/tr[1]/th")
    public List<WebElement> headerCells ;

    public WAllOrderPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Create a method to loop through all header cell
    // return true if the headers are as below false if not
    // 	Name	Product	 #	Date	Street	City State	Zip	Card	Card Number	Exp

}
