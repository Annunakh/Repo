package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

abstract public class NavigationUI extends MainPageObject {

    protected static String
        MY_LISTS_LINK,
        DECLINE_SYNC_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /* UTIL METHODS */

    public void pressBackButton() {
        List<MobileElement> buttons = driver.findElementsByClassName("android.widget.ImageButton");
        MobileElement backButton = buttons.get(0);
        backButton.click();
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to 'My lists'",
                5
        );
    }

    public void declineSyncMyLists() {
        this.waitForElementAndClick(
                DECLINE_SYNC_BUTTON,
                "Cannot decline sync reading list",
                5
        );
    }
}
