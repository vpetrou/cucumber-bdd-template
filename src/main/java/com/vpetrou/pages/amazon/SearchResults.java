package com.vpetrou.pages.amazon;

import com.vpetrou.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchResults extends BaseTest {

    public SearchResults verifySearchResults(String keyword) {
        Assert.assertTrue(
                element.isElementPresent(By.xpath("//*[contains(@class,'desktop-toolbar')]" +
                        "//*[contains(.,'results for \"" + keyword + "\"')]")));
        return this;
    }
}
