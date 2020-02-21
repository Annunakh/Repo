package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SKIP_BUTTON = "//*[contains(@text, 'SKIP')]",
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "org.wikipedia:id/search_src_text",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
        SEARCH_DESCRIPTION_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
        SEARCH_BACK_BUTTON = "//android.widget.ImageButton[1]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultDescriptionElement(String substring) {
        return SEARCH_DESCRIPTION_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

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
}
