package org.example.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;

import java.time.Duration;
import java.util.List;

public class LoginScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public LoginScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private final By inputFields = By.className("android.widget.EditText");
    private final By buttonClass = By.className("android.widget.Button");

    // Actions
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFields, 1));
        List<WebElement> inputs = driver.findElements(inputFields);
        WebElement usernameField = inputs.get(0);

        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.click();
        pause(500); // asegurar que el foco esté activo
        usernameField.clear();
        usernameField.sendKeys(username);
        pause(500);

        // Verificación extra para asegurarse que el texto fue ingresado
        if (usernameField.getText().isEmpty()) {
            // Retry una sola vez
            usernameField.click();
            pause(300);
            usernameField.sendKeys(username);
            pause(500);
        }

        if (usernameField.getText().isEmpty()) {
            throw new RuntimeException("No se pudo escribir correctamente el número de teléfono");
        }
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFields, 1));
        List<WebElement> inputs = driver.findElements(inputFields);
        WebElement passwordField = inputs.get(1);

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.click();
        pause(300);
        passwordField.clear();
        passwordField.sendKeys(password);
        pause(500);
    }

    public void tapLogin() {
        // 1. Define el localizador para el botón "Iniciar sesión"
        By loginButtonLocator = AppiumBy.accessibilityId("Iniciar sesión");

        // 2. Esperar a que el botón sea "clickeable"
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));

        // 3. y hacer clic
        driver.findElement(loginButtonLocator).click();
    }

    // Utilidad simple para pausar
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }
}