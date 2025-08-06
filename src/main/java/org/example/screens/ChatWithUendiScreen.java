package org.example.screens;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
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

    public int getCurrentBotMessageCount() {
        return driver.findElements(botMessages).size();
    }

    public void sendMessage(String message) {
        // Clic en el input
        WebElement input = driver.findElement(inputField);
        input.click();
        input.sendKeys(message);

        // Clic en el segundo ImageView (índice 1 porque empieza en 0)
        List<WebElement> buttons = driver.findElements(sendButtons);
        List<WebElement> allElements = driver.findElements(By.xpath("//*"));
        if (buttons.size() > 1) {
            buttons.get(1).click(); // Segundo botón
        } else {
            throw new RuntimeException("Botón de envío no encontrado.");
        }
    }

    public String waitForNewBotMessages(String palabraClave) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        long startTime = System.currentTimeMillis();
        long timeout = 5000; // 5 segundos

        while (System.currentTimeMillis() - startTime < timeout) {
            try {
                // Siempre buscamos los elementos de nuevo para evitar el StaleElementReferenceException
                List<WebElement> mensajes = driver.findElements(By.className("android.view.View"));

                for (WebElement mensaje : mensajes) {
                    String texto = mensaje.getAttribute("text");
                    if (texto != null && texto.toLowerCase().contains(palabraClave.toLowerCase())) {
                        System.out.println("✅ Respuesta del bot: " + texto);
                        return texto;
                    }
                }

                Thread.sleep(500); // esperamos medio segundo antes de reintentar
            } catch (StaleElementReferenceException e) {
                // ignoramos y reintentamos en el siguiente ciclo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrumpido", e);
            }
        }

       return null;
    }
}