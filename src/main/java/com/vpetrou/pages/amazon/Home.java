package com.vpetrou.pages.amazon;

import com.vpetrou.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home extends BaseTest {

    @FindBy(name = "field-keywords")
    WebElement keywordField;
    @FindBy(id = "nav-search-submit-text")
    WebElement searchButton;

    public Home() {
        PageFactory.initElements(driver, this);
    }

    public SearchResults searchWithKeyword(String keyword) {
        keywordField.clear();
        keywordField.sendKeys(keyword);
        searchButton.click();
        return new SearchResults();
    }

}
