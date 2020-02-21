package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex8Tests extends CoreTestCase {

    @Test
    public void testCheckEveryResultContainsExpectedText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        String text_to_search = "Java";
        SearchPageObject.typeSearchLine(text_to_search);
        SearchPageObject.waitForSearchResult(text_to_search);
        SearchPageObject.assertThatEveryResultContainsExpectedText(text_to_search);
    }

    @Test
    public void testRemoveOneOfSavedArticlesFromReadingList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.pressBackButton();
        NavigationUI.declineSyncMyLists();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithDescription("Island of Indonesia");

        ArticlePageObject.addArticleToCreatedList(name_of_folder);

        NavigationUI.pressBackButton();
        NavigationUI.declineSyncMyLists();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);

        MyListPageObject.openFolderByName(name_of_folder);

        String article_title_to_delete = "Appium";
        String article_title_to_save = "Java";

        MyListPageObject.swipeByArticleToDelete(article_title_to_delete);
        MyListPageObject.waitForArticleToAppearByTitleAndClick(article_title_to_save);

        String article_description_before = "Island of Indonesia";
        String article_description_after = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title is not matches",
                article_description_before,
                article_description_after
        );
    }

    @Test
    public void testAssertArticleTitleIsPresent() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        String text_to_search = "Java";
        SearchPageObject.typeSearchLine(text_to_search);
        SearchPageObject.clickByArticleWithDescription("Object-oriented programming language");
        SearchPageObject.assertElementPresent();
    }
}
