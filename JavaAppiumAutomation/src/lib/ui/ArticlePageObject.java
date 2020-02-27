package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_TPL = "xpath://*[@class='android.view.View']/*[@content-desc='{article_title}']",
        TITLE = "xpath://*[@class='android.view.View'][@resource-id='pagelib_edit_section_title_description']",
        FOOTER_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_external_link'][@text='View page in browser']",
        ADD_ARTICLE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        CREATE_NEW_LIST_BUTTON = "id:org.wikipedia:id/create_button",
        CREATED_FOLDER_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{FOLDER}'] ",
        MY_LIST_NAME_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']",
        MY_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1'][@text='OK']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS*/

    private static String getArticleTitleXpath(String article_title) {
        return TITLE_TPL.replace("{article_title}", article_title);
    }

    private static String getCreatedFolderXpath(String name_of_folder) {
        return CREATED_FOLDER_BUTTON.replace("{FOLDER}", name_of_folder);
    }

    /* UTIL METHODS */

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        System.out.println("Title :" + title_element.getAttribute("name"));
        return title_element.getAttribute("name");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of the page",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot create new reading list",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press 'OK' button",
                5
        );
    }
    public void addArticleToCreatedList(String name_of_folder) {
        this.waitForElementAndClick(
                ADD_ARTICLE_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        String created_folder_xpath = getCreatedFolderXpath(name_of_folder);
        this.waitForElementAndClick(
                created_folder_xpath,
                "Cannot find created reading list",
                5
        );
    }
}
