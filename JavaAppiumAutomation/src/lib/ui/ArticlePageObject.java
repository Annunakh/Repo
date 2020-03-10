package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITLE_TPL,
        TITLE,
        FOOTER_ELEMENT,
        FOOTER_ELEMENT_2,
        ADD_ARTICLE_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        CREATE_NEW_LIST_BUTTON,
        CREATED_FOLDER_BUTTON,
        MY_LIST_NAME_INPUT,
        BACK_BUTTON,
        MY_LIST_OK_BUTTON,
        ARTICLE_IMAGE;

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
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the page",
                    40
            );
        }
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

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(ADD_ARTICLE_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void pressIOSBackBtn() {
        this.waitForElementAndClick(BACK_BUTTON, "Cannot find back button", 5);
    }
}
