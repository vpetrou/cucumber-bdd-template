package com.vpetrou.pages;

import com.vpetrou.BaseTest;
import com.vpetrou.pages.amazon.Home;
import com.vpetrou.pages.amazon.SearchResults;

public class Page extends BaseTest {

    private Home home;
    private SearchResults searchResults;

    public void openApp(String applicationName) {
        driver.get(envDataConfig.getURL(applicationName));
        waitForLoad();
    }

    public Home home() {
        synchronized (this) {
            if (home == null) {
                home = new Home();
            }
        }
        return home;
    }

    public SearchResults searchResults() {
        synchronized (this) {
            if (searchResults == null) {
                searchResults = new SearchResults();
            }
        }
        return searchResults;
    }
}
