package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL;

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
                folder_name_xpath,
                "Cannot find previously created folder by name " + name_of_folder,
                5
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title) {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                saved_article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForFolderToAppearByTitle(String folder_name) {
        String saved_folder_xpath = getFolderXpathByName(folder_name);
        this.waitForElementPresent(
                saved_folder_xpath,
                "Cannot find saved article by title " + folder_name,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void waitForArticleToAppearByTitleAndClick(String article_title) {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                saved_article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }
}
