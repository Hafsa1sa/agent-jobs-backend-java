package org.example.jobs_agent.config;

import org.example.jobs_agent.model.FreelancerRequest;
import org.example.jobs_agent.model.FreelancerResponse;
import org.example.jobs_agent.model.Project;
import org.example.jobs_agent.service.McpClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ToolConfig {

    private static final Logger logger = LoggerFactory.getLogger(ToolConfig.class);

    @Bean("getFreelancerOffers") // Nom explicite du bean
    @Description("Récupère des projets freelance depuis freelancer.ma avec un nombre spécifique de résultats")
    public Function<FreelancerRequest, FreelancerResponse> getFreelancerOffers(McpClientService client) {
        logger.info("🔧 [CONFIG] Bean getFreelancerOffers créé");

        return request -> {
            logger.info("🔧 [TOOL] getFreelancerOffers appelé avec count = {}", request.getCount());

            try {
                List<Project> projects = client.getFreelancerProjects(request.getCount());
                logger.info("✅ [TOOL] {} projets récupérés avec succès", projects.size());

                // Log des premiers projets pour vérification
                projects.stream().limit(3).forEach(p ->
                        logger.info("📝 [TOOL] Projet: {} - Budget: {}", p.getTitle(), p.getBudget())
                );

                return new FreelancerResponse(projects);

            } catch (Exception e) {
                logger.error("❌ [TOOL] Erreur lors de la récupération des projets: ", e);
                throw new RuntimeException("Erreur lors de la récupération des projets: " + e.getMessage());
            }
        };
    }
}