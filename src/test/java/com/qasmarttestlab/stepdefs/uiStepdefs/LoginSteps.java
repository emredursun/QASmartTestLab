package com.qasmarttestlab.stepdefs.uiStepdefs;

import com.qasmarttestlab.pages.LoginPage;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class LoginSteps {
    LoginPage page;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        page = new LoginPage().open();
    }

    @When("I sign in with credentials: {string} and password: {string}")
    public void iSignInWithCredentialsAndPassword(String user, String pass) {
        page.login(user, pass);
    }

    @Then("I should see a {string} message")
    public void i_should_see_message(String expected) {
        Assertions.assertThat(page.message()).containsIgnoringCase(expected);
    }

}
