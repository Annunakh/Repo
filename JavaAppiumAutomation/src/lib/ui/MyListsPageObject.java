package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "//android.view.ViewGroup/android.widget.TextView[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{TITLE}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    /* UTIL METHODS */
    public void openFolderByName(String name_of_folder) {
        this.waitForFolderToAppearByTitle(name_of_folder);
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        System.out.println("Folder to open: " + folder_name_xpath);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find previously created folder by name " + name_of_folder,
                5
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(saved_article_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForFolderToAppearByTitle(String folder_name) {
        String saved_folder_xpath = getFolderXpathByName(folder_name);
        this.waitForElementPresent(
                By.xpath(saved_folder_xpath),
                "Cannot find saved article by title " + folder_name,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToAppearByTitleAndClick(String article_title) {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                By.xpath(saved_article_xpath),
                "Cannot find saved article by title " + article_title,
                15
        );
    }
}
