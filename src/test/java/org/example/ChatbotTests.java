package org.example;

import org.example.screens.ChatWithUendiScreen;
import org.example.screens.HomeScreen;
import org.example.screens.LoginScreen;
import org.example.utils.ChatbotOutputWriter;
import org.example.utils.ChatbotQuestionLoader;
import org.example.model.ChatbotInteraction;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

    @Test
    public void ChatbotCardsQuestions() throws IOException {
        ChatWithUendiScreen chatbotScreen = new ChatWithUendiScreen(driver);
        List<String> questions = ChatbotQuestionLoader.loadQuestionsFromJson();
        List<ChatbotInteraction> interactions = new ArrayList<>();
        List<String> previousReplies = new ArrayList<>();

        for (String question : questions) {
            chatbotScreen.sendMessage(question);

            List<String> newReplies;
            try {
                newReplies = chatbotScreen.waitForNewBotMessages(previousReplies);
            } catch (TimeoutException e) {
                newReplies = new ArrayList<>();
            }

            String response = newReplies != null && !newReplies.isEmpty()
                    ? newReplies.get(newReplies.size() - 1)
                    : "[No response]";

            if (newReplies != null) {
                previousReplies.addAll(newReplies);
            }

            //System.out.println("❓ Question: " + question);
            //System.out.println("🤖 Response: " + response);

            interactions.add(new ChatbotInteraction(question, response));
            Assert.assertFalse(response.isEmpty(), "The bot response should not be empty for question: " + question);

        }

        ChatbotOutputWriter.writeInteractionsToJson(interactions);
    }
}
