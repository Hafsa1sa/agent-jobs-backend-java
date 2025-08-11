package org.example.jobs_agent.tools;


import org.example.jobs_agent.clientApi.ClientApiFreelance;
import org.example.jobs_agent.dto.FreelancerRequestDTO;
import org.example.jobs_agent.dto.FreelancerResponseDTO;
import org.example.jobs_agent.dto.ProjectDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ScrapingTool {

    private static final Logger logger = LoggerFactory.getLogger(ScrapingTool.class);

    @Bean("getFreelancerOffers")
    @Description("Récupère des projets freelance depuis freelancer.ma avec un nombre spécifique de résultats")
    public Function<FreelancerRequestDTO, FreelancerResponseDTO> getFreelancerOffers(ClientApiFreelance client) {
        logger.info(" [CONFIG] Bean getFreelancerOffers créé");

        return request -> {
            logger.info("[TOOL] getFreelancerOffers appelé avec count = {}", request.getCount());

            try {
                List<ProjectDTO> projects = client.getFreelancerProjects(request.getCount());
                logger.info(" [TOOL] {} projets récupérés avec succès", projects.size());

                // Log des premiers projets pour vérification
                projects.stream().limit(3).forEach(p ->
                        logger.info(" [TOOL] Projet: {} - Budget: {}", p.getTitle(), p.getBudget())
                );

                return new FreelancerResponseDTO(projects);

            } catch (Exception e) {
                logger.error(" [TOOL] Erreur lors de la récupération des projets: ", e);
                throw new RuntimeException("Erreur lors de la récupération des projets: " + e.getMessage());
            }
        };
    }
}