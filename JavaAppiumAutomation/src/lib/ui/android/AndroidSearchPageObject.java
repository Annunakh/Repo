package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON ="xpath://*[contains(@text, 'SKIP')]";
        SEARCH_INIT_ELEMENT ="xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT ="id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
        SEARCH_DESCRIPTION_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']";
        SEARCH_BACK_BUTTON ="xpath:/android.widget.ImageButton[1]";
        SEARCH_RESULTS_LIST ="id:org.wikipedia:id/search_results_list";
        SEARCH_EMPTY_RESULT ="id:org.wikipedia:id/search_empty_view";
        PAGE_LIST_ITEM_TITLE_AND_DESCRIPTION ="xpath://*android.view.ViewGroup/android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}'] and ./*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";
        PAGE_LIST_ITEM_TITLE ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";
        AMOUNT_OF_ELEMENTS ="xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@class='android.view.ViewGroup']";
        PAGE_LIST_ITEM_DESCRIPTION ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{DESCRIPTION}']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
