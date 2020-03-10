package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_TPL = "xpath://*[@class='android.view.View']/*[@content-desc='{article_title}']";
        FOOTER_ELEMENT_2 = "xpath:(//XCUIElementTypeToolbar[@name='Toolbar'])[1]";
        CREATE_NEW_LIST_BUTTON = "id:org.wikipedia:id/create_button";
        CREATED_FOLDER_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}'] ";

        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_ARTICLE_BUTTON = "id:Save for later";
        BACK_BUTTON = "id:Back";
        ARTICLE_IMAGE = "xpath://XCUIElementTypeImage";
    }
    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
