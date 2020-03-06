package homeworks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

public class Ex7 {

    private AppiumDriver<MobileElement> driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        // Set default orientation before every test run
        capabilities.setCapability("orientation", "PORTRAIT");
        capabilities.setCapability("app",
                "/Users/kkudzin/Desktop/Repo/JavaAppiumAutomation/apks/org.wikipedia1.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCompareArticleTitle() {
        // Emulate wrong screen orientation after start
        driver.rotate(ScreenOrientation.LANDSCAPE);

        // Set default orientation at the beginning of the test
        checkPortraitScreenOrientation();

        // Some test
    }

    private void checkPortraitScreenOrientation() {
        ScreenOrientation orientation = driver.getOrientation();
        if (orientation != ScreenOrientation.PORTRAIT) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
    }
}
