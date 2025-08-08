package org.example.jobs_agent.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgentController {

    private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
    private final ChatClient chatClient;

    @PostMapping("/ask")
    public String ask(@RequestBody String query) {
        logger.info("🚀 Requête reçue: {}", query);

        try {
            // Utilisation correcte pour Spring AI 1.0.0
            String response = chatClient.prompt()
                    .user(query)
                    .call()
                    .content();

            logger.info("💬 Contenu de la réponse: {}", response);
            return response;

        } catch (Exception e) {
            logger.error("❌ Erreur lors du traitement: ", e);
            return "Erreur lors du traitement de la requête: " + e.getMessage();
        }
    }
}