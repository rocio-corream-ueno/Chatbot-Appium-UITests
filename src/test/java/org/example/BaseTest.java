package org.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected AppiumDriver driver;

    @BeforeClass (alwaysRun = true)
    public void setUp() throws MalformedURLException {
        clearAppData();
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setApp("/Users/rociocorreamalagon/Documents/Proyectos/apks/app-debug.apk") //donde este el apk
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options); //URL por defecto cuando sube appium
        System.out.println("✔ Appium driver iniciado correctamente");
    }

    @AfterClass (alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✔ Sesión Appium cerrada correctamente");
        }
    }

    private void clearAppData() {
        try {
            System.out.println("Limpiando datos de la app...");
            Process process = Runtime.getRuntime().exec("adb shell pm clear chatbot.microapp.dev"); // para que reinicie la app en C/Prueba
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al limpiar datos de la app", e);
        }
    }
}