package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.List;

public class NavigationUI extends MainPageObject {

    private static final String
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']",
        DECLINE_SYNC_BUTTON = "xpath://*[@text='NO THANKS']";

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
