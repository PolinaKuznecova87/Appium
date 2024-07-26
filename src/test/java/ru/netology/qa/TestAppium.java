package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreenAppium;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//она означает, что JUnit создаст один экземпляр теста AppiumTest
// и будет вызывать методы для тестов в нем. Просто экономит память.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestAppium {

    private AndroidDriver driver;

    MainScreenAppium mainScreenAppium;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
        mainScreenAppium = new MainScreenAppium(driver);
    }


    @Test
    @Order(1)
    public void testToSetAnEmptyString() {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys(" ");
        String textToBeChanged = mainScreenAppium.textChangedResult.getText();
        mainScreenAppium.buttonChange.isDisplayed();
        mainScreenAppium.buttonChange.click();
        mainScreenAppium.textChangedResult.isDisplayed();
        Assertions.assertEquals(textToBeChanged, mainScreenAppium.textChangedResult.getText());

    }

    @Test
    @Order(2)
    public void testTextInANewActivity() throws InterruptedException {
        mainScreenAppium.userInput.isDisplayed();
        mainScreenAppium.userInput.click();
        mainScreenAppium.userInput.sendKeys("Hello World");
        mainScreenAppium.openTextInActivity.isDisplayed();
        mainScreenAppium.openTextInActivity.click();
        Thread.sleep(5000);
        mainScreenAppium.expectedText.isDisplayed();
        Assertions.assertEquals("Hello World", mainScreenAppium.expectedText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}