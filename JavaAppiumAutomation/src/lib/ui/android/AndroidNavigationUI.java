package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";
        DECLINE_SYNC_BUTTON = "xpath://*[@text='NO THANKS']";
    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
