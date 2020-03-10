package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://*[@class='android.view.View']/*[@content-desc='{article_title}']";
        TITLE = "xpath://*[@class='android.view.View'][@resource-id='pagelib_edit_section_title_description']";
        FOOTER_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']";
        ADD_ARTICLE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        CREATE_NEW_LIST_BUTTON = "id:org.wikipedia:id/create_button";
        CREATED_FOLDER_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}'] ";
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']";
        MY_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1'][@text='OK']";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
