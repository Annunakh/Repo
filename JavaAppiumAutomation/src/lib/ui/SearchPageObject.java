package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
        SKIP_BUTTON,
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        CLOSE_BTN,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_RESULT_ELEMENT,
        SEARCH_DESCRIPTION_BY_SUBSTRING_TPL,
        SEARCH_BACK_BUTTON,
        SEARCH_RESULTS_LIST,
        SEARCH_EMPTY_RESULT,
        PAGE_LIST_ITEM_TITLE_AND_DESCRIPTION,
        PAGE_LIST_ITEM_TITLE,
        PAGE_LIST_ITEM_FULL_NAME,
        AMOUNT_OF_ELEMENTS,
        PAGE_LIST_ITEM_DESCRIPTION;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getSearchResultByTitleAndDescription(String title, String description) {
        return PAGE_LIST_ITEM_TITLE_AND_DESCRIPTION.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    private static String getSearchResultByFullName(String title, String description) {
        String full_name = title + "\n" + description;
        System.out.println(full_name);
        return PAGE_LIST_ITEM_FULL_NAME.replace("{SUBSTRING}", full_name);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultDescriptionElement(String substring) {
        return SEARCH_DESCRIPTION_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /* UTIL METHODS */

    public void skipOnBoarding() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find skip button", 5);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find element to init search", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    public void waitForSearchResultWithDescription(String substring) {
        String search_description_xpath = getResultDescriptionElement(substring);
        this.waitForElementPresent(search_description_xpath, "Cannot find search result with substring " + substring);
    }

    public void waitForBackButtonToAppear() {
        this.waitForElementPresent(SEARCH_BACK_BUTTON, "Cannot find 'Back' button", 5);
    }

    public void waitForBackButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_BACK_BUTTON, "'Back' button is still present", 5);
    }

    public void clickBackButton() {
        this.waitForElementAndClick(SEARCH_BACK_BUTTON, "Cannot find and click 'Back' button", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void clickByArticleWithDescription(String substring) {
        String search_result_xpath = getResultDescriptionElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                SEARCH_RESULTS_LIST,
                "Cannot find any search results",
                10
        );
        return this.get_amount_of_elements(
                AMOUNT_OF_ELEMENTS
        );
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT,
                "The result is not empty",
                15
        );
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                SEARCH_RESULTS_LIST,
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
        String title_and_description_xpath = getSearchResultByTitleAndDescription(title, description);

        System.out.println(title_and_description_xpath);

        waitForElementPresent(
                title_and_description_xpath,
                "Cannot find element with expected title: " + title + ", " + description,
                10
        );
    }
    public void waitForElementByFullName(String title, String description) {
        String full_name_xpath = getSearchResultByFullName(title, description);
        System.out.println(full_name_xpath);
        waitForElementPresent(
                full_name_xpath,
                "Cannot find element with expected title: " + title + " " + description,
                20
        );
    }
}

