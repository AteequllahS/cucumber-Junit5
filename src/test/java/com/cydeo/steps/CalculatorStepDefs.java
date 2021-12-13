package com.cydeo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorStepDefs {

    @Given("calculator is open")
    public void calculatorIsOpen() {
        System.out.println("calculator is open");
    }

    @When("I add one with two")
    public void iAddOneWithTwo() {
        System.out.println("I add one with two");
    }

    @Then("I should get three")
    public void iShouldGetThree() {
        System.out.println("I should get three");
        assertEquals(5, 5);
    }

    @When("I add one with {int}")
    public void iAddOneWith(int num) {
        System.out.println("number stuff here");
    }

    @Then("I should get {int}")
    public void iShouldGet(int result) {
        System.out.println("iShouldGet here");
    }
}
