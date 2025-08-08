package org.example.jobs_agent.config;

import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.model.FreelancerRequest;
import org.example.jobs_agent.model.FreelancerResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class ChatConfig {

    // Injection de votre fonction via le nom du bean
    private final Function<FreelancerRequest, FreelancerResponse> getFreelancerOffers;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                Tu es un assistant spécialisé dans la recherche de projets freelance sur freelancer.ma.
                
                RÈGLES IMPORTANTES:
                1. Pour TOUTE demande de projets freelance, tu DOIS utiliser la fonction getFreelancerOffers
                2. Ne JAMAIS inventer ou générer de faux projets
                3. Utilise UNIQUEMENT les données retournées par la fonction getFreelancerOffers
                4. Si tu ne peux pas accéder aux données, dis-le clairement

                Quand tu présentes les projets à l'utilisateur, affiche pour chaque projet :
                - le titre
                - le budget
                - la date de publication
                - le délai
                - la description
                - l’URL du projet

                Présente les résultats de façon claire et lisible.

                Paramètres:
                - "bon budget" ou "budget important" = plus de 5000 MAD
                - Limite par défaut: 3 projets maximum
                - Format de date: JJ/MM/AAAA

                Quand un utilisateur demande des projets, appelle OBLIGATOIREMENT getFreelancerOffers.
                """)
                // Utilisation de FunctionToolCallback pour Spring AI 1.0.0
                .defaultToolCallbacks(
                        FunctionToolCallback.builder("getFreelancerOffers", getFreelancerOffers)
                                .description("Récupère des projets freelance depuis freelancer.ma")
                                .inputType(FreelancerRequest.class)
                                .build()
                )
                .build();
    }
}