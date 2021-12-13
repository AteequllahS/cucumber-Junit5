package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;


public class C2_SpartanAPISteps {

    RequestSpecification givenPart;
    Response response;
    ValidatableResponse thenPart;
    int lastId;
    int randomSpartanID;





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
                            .post(endPoint)
                            //.prettyPeek()
                            ;

        // to update its last value
        thenPart = response.then();
    }


    @Then("the success field value is A Spartan is Born!")
    public void theSuccessFieldValueIsASpartanIsBorn() {

        thenPart.body("success",  is( "A Spartan is Born!"));
    }


    @Then("the field value for {string} path should be equal to {string}")
    public void theFieldValueForPathShouldBeEqualTo(String jsonPath, String expectedValue) {

        response.then().body(jsonPath,  is( expectedValue) );

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

        thenPart.body("id" , is(lastId) )
                .body("name" , is(DB_Util.getCellValue(1,"NAME")) )
                .body("gender" , is(DB_Util.getCellValue(1,"GENDER")))
                .body("phone.toString()" , is(DB_Util.getCellValue(1,"PHONE")) )
//

        ;
    }

    @When("I send delete request to {string} endpoint")
    public void iSendDeleteRequestToEndpoint(String endpoint) {

        //any time we make new step, we need to reassign to our global variable.
        response = givenPart.when().delete(endpoint);
        thenPart = response.then();
    }

    @And("I have valid random spartan id")
    public void iHaveValidRandomSpartanId() {

        //send get request to GET /spartans and
        //for the index use 0 to allSpartanCount -1 (a random number between 0 to last index)

        // Note: if we have a new environment without data, we first need to add new data and then go with following
        //process

        List<Integer> allIds = givenPart.get("/spartans").path("id");

        //get the ID at a location from 0 to allIds.size()-1
/*
        // Random : under java.util package --> for random number
        Random random = new Random();
        //it has a method called nextInt that take a parameter for upperbound (1 to upperbound)
        int randomIndex = random.nextInt(allIds.size()-1);
        System.out.println("randomIndex = " + randomIndex);

 */
        //another way is to use Faker:
        Faker faker = new Faker();
        int randomIndex = faker.number().numberBetween(0, allIds.size()-1); // 0 to last index

        //now  use above index and get the item from the list
        randomSpartanID = allIds.get(randomIndex); // set the variable at class level

        //set this ID to path variable
        givenPart.pathParam("id", randomSpartanID);
    }

    @When("I send put request to {string} endpoint")
    public void iSendPutRequestToEndpoint(String endpoint) {

        response = givenPart.when().put(endpoint);
    }

    @When("I send patch request to {string} endpoint")
    public void iSendPatchRequestToEndpoint(String endpoint) {

        response = givenPart.when().patch(endpoint);
        thenPart = response.then() ;

    }

    @And("I search for spartan with name contains {string} and gender {string}")
    public void iSearchForSpartanWithNameContainsAndGender(String nameParam, String genderParam) {

        givenPart
                .queryParam("nameContains", nameParam)
                .queryParam("gender", genderParam);
    }


    @Then("All names in the result should contain {string} and gender should be {string}")
    public void allNamesInTheResultShouldContainAndGenderShouldBe(String expectedName, String expectedGender) {

        thenPart
                .body("content.gender", everyItem( is( expectedGender ) ) )
                .body("content.name", everyItem( containsStringIgnoringCase( expectedName  ) )) ;
    }


    @Then("The search count for name contains {string} and gender {string} should match the count in the database")
    public void theSearchCountForNameContainsAndGenderMaleShouldMatchTheCountInTheDatabase(String name, String gender) {

        String query = "SELECT count(*) FROM SPARTANS " +
                " WHERE UPPER(NAME) LIKE '%"+ name.toUpperCase()  +"%' " +
                " AND GENDER = '"+gender+"'" ;

        System.out.println("query = " + query);

        DB_Util.runQuery(query);
        DB_Util.displayAllData();

        // verify the data match
        thenPart.body("totalElement.toString()" ,  is(  DB_Util.getCellValue(1,1)   )     ) ;
        // or just convert the db result to number
        int expectedDBResult = Integer.parseInt(  DB_Util.getCellValue(1,1)  ) ;
        thenPart.body("totalElement", is(expectedDBResult) );

    }
}
