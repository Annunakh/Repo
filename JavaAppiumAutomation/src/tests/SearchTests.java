package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResultWithDescription("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForBackButtonToAppear();
        SearchPageObject.clickBackButton();
        SearchPageObject.waitForBackButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();
        String search_line = "dsgsfhjdestj";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCheckExpectedSearchResultsByTitleAndDescription() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnBoarding();
        SearchPageObject.initSearchInput();

        String text_to_search = "Linkin Park discography";
        SearchPageObject.typeSearchLine(text_to_search);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found less than 3 results",
                amount_of_search_results >= 3
        );

        Map<String, String> results_to_check = new HashMap<>();
        results_to_check.put("Linkin Park discography", "Band discography");
        results_to_check.put("Linkin Park", "American alternative rock band");
        results_to_check.put("Hybrid Theory", "2000 studio album by Linkin Park");

        for (Map.Entry<String, String> entry : results_to_check.entrySet()) {
            String title = entry.getKey();
            String description = entry.getValue();

            if (Platform.getInstance().isAndroid()) {
                SearchPageObject.waitForElementByTitleAndDescription(title, description);
            } else {
                SearchPageObject.waitForElementByFullName(title, description);
            }

        }
    }
}
