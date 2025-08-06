package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatbotQuestionLoader {

    public static List<String> loadQuestionsFromJson() {
        try {
            String path = ConfigReader.get("Questions_Cards");
            ObjectMapper mapper = new ObjectMapper();

            List<Map<String, String>> data = mapper.readValue(
                    new File(path),
                    new TypeReference<List<Map<String, String>>>() {}
            );

            List<String> questions = new ArrayList<>();
            for (Map<String, String> item : data) {
                if (item.containsKey("question")) {
                    questions.add(item.get("question"));
                }
            }

            return questions;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load chatbot questions.", e);
        }
    }
}