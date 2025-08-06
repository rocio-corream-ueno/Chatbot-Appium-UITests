package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ChatbotInteraction;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChatbotOutputWriter {

    private static final String OUTPUT_PATH = "test-output/chatbot_interactions_output.json";

    public static void writeToJson(List<Map<String, String>> interactions) {
        try {
            File outputDir = new File("test-output");
            if (!outputDir.exists()) {
                outputDir.mkdirs(); // Crea el directorio si no existe
            }

            File outputFile = new File(OUTPUT_PATH);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, interactions);

            System.out.println("📁 Chatbot interactions saved to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("❌ Error writing chatbot interactions to JSON", e);
        }
    }

    public static void prepareOutputFile() {
        try {
            File outputDir = new File("test-output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(OUTPUT_PATH);
            if (!outputFile.exists()) {
                outputFile.createNewFile();

                // Escribimos una lista vacía al archivo (JSON vacío válido)
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(outputFile, List.of());
            }

            System.out.println("📁 Output file prepared at: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to prepare chatbot output file", e);
        }
    }
    public static void writeInteractionsToJson(List<ChatbotInteraction> interactions) {
        try {
            File outputDir = new File("test-output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File outputFile = new File(OUTPUT_PATH);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, interactions);

            System.out.println("📁 Chatbot interactions saved to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("❌ Error writing chatbot interactions to JSON", e);
        }
    }
}
