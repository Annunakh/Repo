package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
        SKIP_BUTTON = "//*[contains(@text, 'SKIP')]",
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
        SEARCH_DESCRIPTION_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
        SEARCH_BACK_BUTTON = "//android.widget.ImageButton[1]",
        SEARCH_RESULTS_LIST = "org.wikipedia:id/search_results_list",
        SEARCH_EMPTY_RESULT = "org.wikipedia:id/search_empty_view",
        PAGE_LIST_ITEM_TITLE = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']",
        PAGE_LIST_ITEM_DESCRIPTION = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String[] getSearchResultByTitleAndDescription(String title, String description) {
        String title_xpath = PAGE_LIST_ITEM_TITLE.replace("{TITLE}", title);
        String description_xpath = PAGE_LIST_ITEM_DESCRIPTION.replace("{DESCRIPTION}", description);
        String[] result = {title_xpath, description_xpath};
        return result;
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultDescriptionElement(String substring) {
        return SEARCH_DESCRIPTION_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /* UTIL METHODS */

    public void skipOnBoarding() {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Cannot find skip button", 5);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find element to init search", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultWithDescription(String substring) {
        String search_description_xpath = getResultDescriptionElement(substring);
        this.waitForElementPresent(By.xpath(search_description_xpath), "Cannot find search result with substring " + substring);
    }

    public void waitForBackButtonToAppear() {
        this.waitForElementPresent(By.xpath(SEARCH_BACK_BUTTON), "Cannot find 'Back' button", 5);
    }

    public void waitForBackButtonToDisappear() {
        this.waitForElementNotPresent(By.xpath(SEARCH_BACK_BUTTON), "'Back' button is still present", 5);
    }

    public void clickBackButton() {
        this.waitForElementAndClick(By.xpath(SEARCH_BACK_BUTTON), "Cannot find and click 'Back' button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void clickByArticleWithDescription(String substring) {
        String search_result_xpath = getResultDescriptionElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULTS_LIST),
                "Cannot find any search results",
                10
        );
        return this.get_amount_of_elements(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']")
        );
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.id(SEARCH_EMPTY_RESULT),
                "The result is not empty",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULTS_LIST),
                "We supposed not to find any results"
        );
    }

    public void assertThatEveryResultContainsExpectedText(String text_to_search) {
        List<MobileElement> search_results =
                driver.findElements(By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']" +
                        "/android.view.ViewGroup"));

        for (MobileElement element: search_results) {
            MobileElement text_view = (MobileElement) driver.findElement(By.id("org.wikipedia:id/page_list_item_title"));
            Assert.assertTrue(
                    "Not every result contains the expected text",
                    text_view.getAttribute("text").contains(text_to_search)
            );
        }
    }

    public void assertElementPresent() {
        String default_message = "Element is not present by xpath: ";
        if(driver.findElements(By.xpath("//*[@class='android.view.View']/*[@content-desc='Java (programming language)']")).isEmpty()) {
            throw new AssertionError(default_message);
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String[] title_and_description_xpath = getSearchResultByTitleAndDescription(title, description);
        String title_xpath = title_and_description_xpath[0];
        String description_xpath = title_and_description_xpath[1];

        waitForElementPresent(
                By.xpath(title_xpath),
                "Cannot find element with expected title: " + title,
                10
        );
        waitForElementPresent(
                By.xpath(description_xpath),
                "Cannot find element with expected description: " + description,
                10
        );
    }
}

