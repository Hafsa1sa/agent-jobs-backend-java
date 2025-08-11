package org.example.jobs_agent.service;



import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScraperService {

    private static final Logger logger = LoggerFactory.getLogger(ScraperService.class);
    private final ChatClient chatClient;

    public String processQuery(String query) {
        logger.info(" Requête reçue dans le service: {}", query);

        try {
            String response = chatClient.prompt()
                    .user(query)
                    .call()
                    .content();

            logger.info("Contenu de la réponse du service: {}", response);
            return response;

        } catch (Exception e) {
            logger.error(" Erreur dans le service lors du traitement: ", e);
            throw new RuntimeException("Erreur lors du traitement de la requête: " + e.getMessage(), e);
        }
    }
}
