package com.cydeo.steps;

import com.cydeo.utility.ConfigReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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

}
