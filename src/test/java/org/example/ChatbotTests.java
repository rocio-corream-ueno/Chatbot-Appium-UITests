package org.example;

import org.example.screens.HomeScreen;
import org.example.screens.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChatbotTests extends BaseTest {

    @BeforeClass
    public void performLogin() {
        LoginScreen login = new LoginScreen(driver);
        login.enterUsername("983100006"); //Ajustarlo al usuario para ejecutar
        login.enterPassword("Ueno!2023");
        login.tapLogin();
    }

    @Test
    public void openChatbotFromWelcomeScreen() {
        HomeScreen welcomeScreen = new HomeScreen(driver);
        welcomeScreen.tapOpenChatWithUendi();
    }
}