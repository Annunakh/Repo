package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT ="xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_BACK_BUTTON ="xpath://XCUIElementTypeButton[@name='Cancel']";
        SEARCH_DESCRIPTION_BY_SUBSTRING_TPL ="xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULTS_LIST ="xpath://XCUIElementTypeCollectionView//XCUIElementTypeLink";
        AMOUNT_OF_ELEMENTS ="xpath://XCUIElementTypeCollectionView";

        PAGE_LIST_ITEM_TITLE_AND_DESCRIPTION ="xpath://*android.view.ViewGroup/android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}'] and ./*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";
        PAGE_LIST_ITEM_TITLE ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";
        PAGE_LIST_ITEM_TITLE_2 ="xpath://*[@class='android.view.ViewGroup']/*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";

        PAGE_LIST_ITEM_DESCRIPTION ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
