package org.example.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.StaleElementReferenceException;



public class ChatWithUendiScreen {

    private final AppiumDriver driver;

    // Localizadores
    private final By inputField = By.className("android.widget.EditText");
    private final By sendButtons = By.className("android.widget.ImageView");
    private final By botMessages = By.className("android.view.View");

    public ChatWithUendiScreen(AppiumDriver driver) {
        this.driver = driver;
    }

    public void sendMessage(String message) {
        // Click on the input field and send the message
        WebElement input = driver.findElement(inputField);
        input.click();
        input.sendKeys(message);

        // Locate the button
        List<WebElement> buttons = driver.findElements(sendButtons);

        //Si la lista no está vacía, haz clic en el ÚLTIMO elemento
        if (!buttons.isEmpty()) {
            //selecciona el último elemento de la lista
            buttons.get(buttons.size() - 1).click();
        } else {
            throw new RuntimeException("No se encontró ningún ImageView (botón de enviar).");
        }
    }

    public List<String> waitForNewBotMessages(List<String> previousMessages) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        return wait.until(driver -> {
            try {
                Thread.sleep(7000); // Espera inicial

                List<WebElement> allViews = driver.findElements(botMessages);

                List<String> allBotReplies = allViews.stream()
                        .map(element -> {
                            try {
                                String desc = element.getAttribute("content-desc");
                                if (desc == null || desc.trim().isEmpty()) {
                                    desc = element.getText(); // fallback
                                }
                                return desc;
                            } catch (StaleElementReferenceException e) {
                                return null;
                            }
                        })
                        .filter(desc -> desc != null && !desc.trim().isEmpty())
                        .collect(Collectors.toList());

                List<String> newReplies = allBotReplies.stream()
                        .filter(msg -> previousMessages == null || !previousMessages.contains(msg))
                        .collect(Collectors.toList());

                if (!newReplies.isEmpty()) {
                   // newReplies.forEach(msg -> System.out.println("✅ Bot reply found: " + msg));
                    return newReplies;
                }

                return null;

            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted while waiting for bot response", e);
            }
        });
    }
}