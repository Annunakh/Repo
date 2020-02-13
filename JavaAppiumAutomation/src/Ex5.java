import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex5 {
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
        capabilities.setCapability("app",
                "/Users/kkudzin/Desktop/Repo/JavaAppiumAutomation/apks/org.wikipedia1.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRemoveOneOfSavedArticlesFromReadingList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'SKIP')]"),
                "Cannot find skip button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element to init search",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/create_button"),
                "Cannot create new reading list",
                5
        );

        waitForElementAndClear(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),
                "Cannot clear field with name of the list",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input'][@text='Name of this list']"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1'][@text='OK']"),
                "Cannot press 'OK' button",
                5
        );

        pressBackButton();

        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot decline sync reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find element to init search",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='Island of Indonesia']"),
                "Cannot find 'Island of Indonesia' article description in search",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='"+ name_of_folder +"']"),
                "Cannot find created folder",
                5
        );

        pressBackButton();

        waitForElementAndClick(
                By.xpath("//*[@text='NO THANKS']"),
                "Cannot decline sync reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/item_title'][@text='" + name_of_folder + "']"),
                "Cannot find previously created list",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find saved article"
        );

        assertElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot delete saved article"
        );

        String title_before = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']"),
                "text",
                "Cannot find title of article",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java']"),
                "Cannot find not removed article",
                5
        );

        String title_after = waitForElementAndGetAttribute(
                By.xpath("//android.view.View[@content-desc='Java']"),
                "content-desc",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title is not matches",
                title_before,
                title_after
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutINSec) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutINSec) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int get_amount_of_elements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = get_amount_of_elements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void pressBackButton() {
        List<MobileElement> buttons = driver.findElementsByClassName("android.widget.ImageButton");
        MobileElement backButton = buttons.get(0);
        backButton.click();
    }
}