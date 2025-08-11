package org.example.jobs_agent.clientApi;


import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.dto.ProjectDTO;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;


@Service
@RequiredArgsConstructor
public class ClientApiFreelance {
    private static final Logger logger = LoggerFactory.getLogger(ClientApiFreelance.class);
    private final RestTemplate restTemplate;

    public List<ProjectDTO> getFreelancerProjects(int count) {
        logger.info("[MCP] Début de l'appel vers l'API freelancer avec count = {}", count);

        String url = "/tools/get_freelancer_offres";
        logger.info("[MCP] URL appelée: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Integer> requestMap = Map.of("count", count);
        HttpEntity<Map<String, Integer>> requestEntity = new HttpEntity<>(requestMap, headers);

        try {
            List<ProjectDTO> projects = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<List<ProjectDTO>>() {}
            ).getBody();

            logger.info("[MCP] Réponse reçue: {} projets", projects != null ? projects.size() : 0);
            return projects;

        } catch (Exception e) {
            logger.error("[MCP] Erreur lors de l'appel API: ", e);
            throw e;
        }
    }
}
