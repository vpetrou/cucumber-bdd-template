package com.vpetrou.stepdefs;

import com.vpetrou.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaseStepDefs extends BaseTest {


    @Before
    public void beforeTestUI() {
        openBrowser();
    }

    @After
    public void afterTest(Scenario scenario) {
        closeBrowser(scenario);
    }

    @Given("^the user opens the (.+) application$")
    public void userOpens(String applicationName) {
        page.openApp(applicationName);
    }

    @When("^the user searches with filter (.+)$")
    public void theUserSearchesWithFilter(String keyword) {
        page.home().searchWithKeyword(keyword);
    }

    @Then("^search results are based with filter (.+)$")
    public void searchResultsAreBasedWithFilter(String keyword) {
        page.searchResults().verifySearchResults(keyword);
    }
}
