package org.example.jobs_agent.config;

import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.dto.FreelancerRequestDTO;
import org.example.jobs_agent.dto.FreelancerResponseDTO;
import org.example.jobs_agent.prompt.FreelancerPrompt;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    private final Function<FreelancerRequestDTO, FreelancerResponseDTO> getFreelancerOffers;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(FreelancerPrompt.PROMPT_TEXT)
                .defaultToolCallbacks(
                        FunctionToolCallback.builder("getFreelancerOffers", getFreelancerOffers)
                                .description("Récupère des projets freelance depuis freelancer.ma")
                                .inputType(FreelancerRequestDTO.class)
                                .build()
                )
                .build();
    }
}