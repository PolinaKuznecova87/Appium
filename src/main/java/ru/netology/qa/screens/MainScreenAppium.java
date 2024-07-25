package ru.netology.qa.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MainScreenAppium {
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public MobileElement buttonChange;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public MobileElement textChangedResult;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public MobileElement userInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public MobileElement openTextInActivity;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public MobileElement expectedText;


    private AppiumDriver driver;

    public MainScreenAppium(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

}