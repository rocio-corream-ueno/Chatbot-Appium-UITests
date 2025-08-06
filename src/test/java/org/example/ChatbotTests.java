package org.example;

import org.example.screens.ChatWithUendiScreen;
import org.example.screens.HomeScreen;
import org.example.screens.LoginScreen;
import org.testng.Assert;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChatbotTests extends BaseTest {

    private ChatWithUendiScreen chatbotScreen;

    @BeforeClass
    public void performLogin() {
        LoginScreen login = new LoginScreen(driver);
        login.enterUsername("983100006"); // Ajustarlo al usuario para ejecutar
        login.enterPassword("Ueno!2023");
        login.tapLogin();
        HomeScreen welcomeScreen = new HomeScreen(driver);
        welcomeScreen.tapOpenChatWithUendi();
        // Inicializa la pantalla del chatbot
        chatbotScreen = new ChatWithUendiScreen(driver);
    }

    @Test(priority = 1)
    public void chatbot_tarjetas() {
        ChatWithUendiScreen chatbot = new ChatWithUendiScreen(driver);

        chatbot.sendMessage("Hola");
        String respuesta = chatbot.waitForNewBotMessages("tarjetas");
        System.out.println(respuesta != null ? "✅ Bot respondió: " + respuesta : "❌ No se encontró respuesta con 'tarjetas'");

        chatbot.sendMessage("a");
        String respuesta2 = chatbot.waitForNewBotMessages("credito");
        System.out.println(respuesta2 != null ? "✅ Bot respondió: " + respuesta2 : "❌ No se encontró respuesta con 'credito'");

        chatbot.sendMessage("b");
        String respuesta3 = chatbot.waitForNewBotMessages("funciona");
        System.out.println(respuesta3 != null ? "✅ Bot respondió: " + respuesta3 : "❌ No se encontró respuesta con 'funciona'");
    }
}
