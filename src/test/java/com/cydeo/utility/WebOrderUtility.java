package com.cydeo.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

// THIS AIN'T NO TEST CLASS SO WE CAN NOT EXTEND TESTBASE
// IT SIMPLY DOES NOT MAKE SENSE
public class WebOrderUtility {

    public static void openWebOrderApp() {
        Driver.getDriver().get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
    }

    public static void login() {

        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();

    }

    public static void login(String username, String password) {

        // enter username
        // we have to update the locators based on website we are using
        Driver.getDriver().findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
        // enter password
        Driver.getDriver().findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
        // click login
        Driver.getDriver().findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    public static void logout() {

        Driver.getDriver().findElement(By.xpath("//a[@id='ctl00_logout']")).click();

    }

    public static boolean isDisplayed() {

        boolean result = false;

        //locator for the header element of tall order page
        // xpath for the text to be displayed:  ->  //h2[normalize-space(.)='List of All Orders']

        //throwing noSuchElementException
        try {
            WebElement headerText = Driver.getDriver().findElement(By.xpath("//h2[normalize-space(.)='List of All Orders']"));
            System.out.println("Element was identified");
            System.out.println("The header on the page = " + headerText.getText());
            result = true;

            //make sure to pick the exception from selenium no java as both have this exception.
        } catch (NoSuchElementException e) {
            System.out.println(" NO SUCH ELEMENT ! your are not at the right page");
        }

        return result;

    }

    public static boolean isAtAllOrderPage() {

        boolean result = false;
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 2);
        try {
            // wait.until(ExpectedConditions.urlToBe("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx"));
            wait.until(visibilityOfElementLocated(By.xpath("//h2[normalize-space(.)='List of All Orders'] ")));
            System.out.println("YOU ARE AT THE RIGHT PAGE.");
            result = true;
        } catch (TimeoutException e) {
            System.out.println("YOUR ARE NOT AT ORDER PAGE : " + e.getMessage());
        }
        //DriverUtility.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return result;
    }

    public static boolean verifyUserName(String username) {

        WebElement logInArea = Driver.getDriver().findElement(By.cssSelector("div.login_info"));
        System.out.println("logInArea.getText() = " + logInArea.getText());
        return logInArea.getText().contains(username);
    }

    public static String getUsernameFromWelcomeMessage() {

        WebElement logInArea = Driver.getDriver().findElement(By.cssSelector("div.login_info"));
        String welcomeMessage = logInArea.getText();
        //Welcome, Tester! | Logout
        return welcomeMessage.replace("Welcome, ", "").replace("! | Logout", "");

    }

    public static void selectSidebarTab(String tabName) {

        Driver.getDriver().findElement(By.linkText(tabName)).click();
/* optional

        List<String> allTabs = Arrays.asList("View all orders", "View all products", "Order");
        if(allTabs.contains(tabName)){
            DriverUtility.getDriver().findElement(By.linkText(tabName)).click();
        }else {
            DriverUtility.getDriver().findElement(By.linkText("View all orders")).click();
        }

 */

    }

    public static int getUnitPriceFromForm(String productName) {

        int price = 0;
        WebElement productDropdown = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select product = new Select(productDropdown);
        product.selectByVisibleText(productName);

        WebElement pricePerUnit = Driver.getDriver().findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice"));
        return Integer.parseInt(pricePerUnit.getAttribute("value"));

    }

    /**
     * Check for login error message is visible or not , by calling the BrowserUtil method we created
     * @return true if error message displayed , false if not
     */
    public static boolean loginErrorMsgVisible(){

        boolean elementFound =
                BrowserUtil.checkVisibilityOfElement(By.xpath("//span[. ='Invalid Login or Password.']"),2);
        return elementFound ;
    }

    //check all checkboxes by clicking Check All button
    public static boolean checkAll(){

        Driver.getDriver().findElement(By.linkText("Check All"));
        List<WebElement> checkAllBox = Driver.getDriver().findElements(By.cssSelector("input[id$='OrderSelector']"));

        boolean result = true;
        //as long as one of them are not checked, return false immediately
        for (WebElement eachCheckbox : checkAllBox) {
            if(! eachCheckbox.isSelected() ){//if not selected
                result = false;
            }
        }

        return result;

    }

    //uncheck all checkboxes by clicking Uncheck All button
    public static boolean unCheckAll(){

        Driver.getDriver().findElement(By.linkText("uncheck All"));
        List<WebElement> checkAllBox = Driver.getDriver().findElements(By.cssSelector("input[id$='OrderSelector']"));

        boolean result = true;
        //as long as one of them are not checked, return false immediately
        for (WebElement eachCheckbox : checkAllBox) {
            if(eachCheckbox.isSelected() ){//if not selected
                result = false;
            }
        }

        return result;

    }











}