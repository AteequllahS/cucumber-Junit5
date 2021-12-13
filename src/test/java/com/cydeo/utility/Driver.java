package com.cydeo.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * We wanted to have a class with that only return Single object
 * no matter how many time you asked for object
 * so we are creating this class with technic we learned from Singleton pattern
 */
public class Driver {
    // InheritableThreadLocal  --> this is like a container, bag, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    private Driver() {
    }
    /*
     * Return obj with only one WebDriver instance
     * @return same WebDriver if exists , new one if null
     */
    public static WebDriver getDriver() {
        //if this thread doesn't have driver - create it and add to pool
        String browserName = ConfigReader.read("browser");
        // get method from InheritableThreadLocal will check if we have object in this thread or not
        // if not it will return null
        if (driverPool.get() == null) {
//            if we pass the driver from terminal then use that one
//           if we do not pass the driver from terminal then use the one properties file
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    driverPool = null ;
                    System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
            }

            return driverPool.get();

        }else{
            System.out.println("You have it just use existing one");
            return driverPool.get() ;
        }
        /*
         * Quitting the browser and setting the value of
         * WebDriver instance to null because you can re-use already quitted driver
         */
    }
    public static void closeBrowser() {

        // check if we have WebDriver instance or not in this thread
        // basically checking if obj is null or not
        // if not null
        if (driverPool.get() != null) {
            // quit the webdriver
            // make it null , because once quit it can not be used
            driverPool.get().quit();
            // so when ask for it again , it gives us not quited fresh driver
            driverPool.set(null);
        }
    }
}
