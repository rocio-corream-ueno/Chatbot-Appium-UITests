package org.example.model;

public class ChatbotInteraction {
    private String question;
    private String answer;

    public ChatbotInteraction(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
