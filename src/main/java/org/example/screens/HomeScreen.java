package org.example.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomeScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By openChatButton = AppiumBy.accessibilityId("Open Chat with Uendi");

    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void tapOpenChatWithUendi() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(openChatButton));
        driver.findElement(openChatButton).click();
    }
}