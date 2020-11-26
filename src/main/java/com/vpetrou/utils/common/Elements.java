package com.vpetrou.utils.common;

import com.vpetrou.BaseTest;
import com.vpetrou.utils.config.ResourcesConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Elements extends BaseTest {

    public Boolean isElementPresent(By by) {
        if (driver.findElements(by).size() > 0)
            return true;
        else
            return false;
    }

    public void hover(By by) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(by);
        actions.moveToElement(element);
    }

    public String getValue(By by) {
        WebElement element = driver.findElement(by);
        return element.getAttribute("innerHTML");
    }

    public void click(By by) {
        WebElement buttonOrLink = driver.findElement(by);
        buttonOrLink.click();
    }

    public void clickAction(By by) {
        WebElement buttonOrLink = driver.findElement(by);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(buttonOrLink));
        Actions action = new Actions(driver);
        action.click(buttonOrLink).perform();
    }

    public void clickTab(By by) {
        WebElement element = driver.findElement(by);
        element.sendKeys(Keys.TAB);
    }

    public void clickTabs(String tabText) {
        element.click(By.xpath("//li[contains(.,'" + tabText + "')]"));
        waitForLoad();
    }

    public void clickJS(By by) {
        WebElement buttonOrLink = driver.findElement(by);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(buttonOrLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonOrLink);
    }

    public void input(By by, String value) {
        if (value != null && !value.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.findElement(by).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            try {
                driver.findElement(by).sendKeys(value);
            } catch (StaleElementReferenceException ex) {
                driver.findElement(by).sendKeys(value);
            }
        }
    }

    public void inputDate(By by, String value) {
        click(by);
        input(by, value);
        closeDatePicker();
    }

    public void closeDatePicker() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('ui-datepicker-div').style.display = 'none';");
    }

    public void dropdown(By by, String value) {
        WebElement dropdown = driver.findElement(by);
        if (value != null && !value.isEmpty()) {
            dropdown.click();
            waitForLoad();
            element.click(By.xpath("//li[contains(@class,'ui-selectonemenu-item') and contains(.,'" + value + "')]"));
        }
    }

    public void clear(By by) {
        WebElement toClear = driver.findElement(by);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
    }

    public void dropdownWithScroll(By by, String value) {
        WebElement dropdown = driver.findElement(by);
        if (value != null && !value.isEmpty()) {
            dropdown.click();
            waitForLoad();
            scrollToTheElement(By.xpath("//li[contains(@class,'ui-selectonemenu-item') and contains(.,'" + value + "')]"), "down");
            element.click(By.xpath("//li[contains(@class,'ui-selectonemenu-item') and contains(.,'" + value + "')]"));
        }
    }

    public void upload(By chooseFileButton, String filename) {
        waitForLoad();
        WebElement input = driver.findElement(chooseFileButton);
        ResourcesConfig resourcesConfig = new ResourcesConfig();
        if (filename != null && !filename.isEmpty()) {
            input.sendKeys(resourcesConfig.getInputDir() + "/" + filename);
        }
    }

    public void accordionExpand(String accordionName) {
        waitForLoad();
        String accordionOptionXPath = "//div[contains(@class,'ui-accordion ui-widget')]//*[contains(text(),'" + accordionName + "')]";
        waitForElement(driver.findElement(By.xpath(accordionOptionXPath)));
        if (driver.findElement(By.xpath(accordionOptionXPath)).getAttribute("aria-expanded").equals("false")) {
            element.click(By.xpath(accordionOptionXPath));
        }
        waitForLoad();
    }

    public void clickTabInForm(String tabText) {
        element.click(By.xpath("//form[@id='preview']//li[contains(.,'" + tabText + "')]"));
        waitForLoad();
    }

    public boolean hasLabelExpectedValue(String label, String expectedValue) {
        return isElementPresent(By.xpath("//label[contains(text(),'"
                + label + "')]/following::label[contains(text(),'" + expectedValue + "')]"));
    }

    public boolean tableContainsValue(String formId, String expectedValue) {
        return isElementPresent(By.xpath("//tbody[contains(@id,'"
                + formId + "')]//td[@role='gridcell' and contains(.,'" + expectedValue + "')]"));
    }

    // SCROLL UP the page
    public void scrollUp() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,-600)", "");
        waitForLoad();
    }

    // SCROLL TO THE ELEMENT
    public void scrollToTheElement(By by, String direction) {

        if ("down".equals(direction) || "up".equals(direction)) {
            String dir = "true";
            if ("up".equals(direction)) {
                dir = "false";
            }
            WebElement element = driver.findElement(by);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(" + dir + ");", element);
            waitForElement(element);
        } else {
            System.out.println("ERROR with given directions! Acceptable Strings: 'up' or 'down'!");
        }
    }

    // SCROLL DOWN the page
    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,600)", "");
    }
}
