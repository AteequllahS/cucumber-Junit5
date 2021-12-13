package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class SpartanAPISteps {

    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;
    int lastId;

    @Given("the base _uri and base_path set")
    public void the_base__uri_and_base_path_set() {

        System.out.println("the base _uri and base_path set");

        //here set up you base url base path or anything that does to given
        //and save it into variable and make it global
        givenPart = given()
                        .log().all()
                        .baseUri("http://54.174.254.49:8000")
                        .basePath("/api");

    }
    @When("I send get request to {string} endpoint")
    public void i_send_get_request_to_endpoint(String endpoint) {

        System.out.println("I send get request to {string} endpoint "+endpoint);

        //here send your request and save the result into variable and make it global

        response = givenPart.when()
                            .get(endpoint).prettyPeek();

    }
    @Then("I should get status code {int}")
    public void i_should_get_status_code(int expectedStatusCode) {
        System.out.println("the base _uri and base_path set "+expectedStatusCode);

        //here make your assertions save it into class level variable
        thenPart = response.then().statusCode(expectedStatusCode);

        //or you can just use response object and regular assertion
        //Assertions.assertEquals(200, response.statusCode());


    }

    @And("The response format should be {string}")
    public void theResponseFormatShouldBe(String expectedContentType) {

        thenPart.contentType( expectedContentType );
    }

    @And("The response payload should be Hello from Sparta")
    public void theResponsePayloadShouldBeHelloFromSparta() {

        thenPart.body(  is("Hello from Sparta")  );
    }

    @And("I ask for {string} response payload")
    public void iAskForResponsePayload(String responseFormat) {

        if (responseFormat.equals("json")) {
            givenPart.accept(ContentType.JSON);

        }else if (responseFormat.equals("xml")){
            givenPart.accept(ContentType.XML);

        }else {
            throw new RuntimeException("The content type is invalid: "+responseFormat);
        }


    }

    @Given("I send the data in json format")
    public void iSendTheDataInJsonFormat() {

        givenPart.contentType(ContentType.JSON) ;
    }


    @And("I am sending below valid spartan data")
    public void iAmSendingBelowValidSpartanData(Map<String, Object> requestPayLoadMap) {

        givenPart.body( requestPayLoadMap );
    }


    @When("I send post request to {string} endpoint")
    public void iSendPostRequestToEndpoint(String endPoint) {

        //we need to re assign as we are posting this time
        response = givenPart
                            .when()
                            .post(endPoint).prettyPeek() ;
    }


    @Then("the success field value is A Spartan is Born!")
    public void theSuccessFieldValueIsASpartanIsBorn() {

        thenPart.body("success",  is( "A Spartan is Born!"));
    }


    @Then("the field value for {string} path should be equal to {string}")
    public void theFieldValueForPathShouldBeEqualTo(String jsonPath, String expectedValue) {

        thenPart.body(jsonPath,  is( expectedValue) );

    }


    @Then("the field value for {string} path should be equal to {long}")
    public void theFieldValueForPathShouldBeEqualTo(String jsonPath, long expectedValue) {

        thenPart.body(jsonPath, is( expectedValue ) ) ;

    }

    @And("I have valid spartan id")
    public void iHaveValidSpartanId() {

        //get a valid spartan Id and make it available for all methods
        //also think about whether you need name, phone, gender

        lastId = givenPart.get("/spartans").path("id[-1]");
        //can we just set this id into path variable in here directly
        givenPart.pathParam("id", lastId);
    }

    @Then("the spartan data with that id matches value in the database")
    public void theSpartanDataWithThatIdMatchesValueInTheDatabase() {

        DB_Util.runQuery("SELECT * FROM SPARTANS WHERE SPARTAN_ID = " + lastId);
        DB_Util.displayAllData();
    }
}
