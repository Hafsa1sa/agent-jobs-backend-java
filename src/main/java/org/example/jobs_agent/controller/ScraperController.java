package org.example.jobs_agent.controller;


import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.service.ScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScraperController {

    private static final Logger logger = LoggerFactory.getLogger(ScraperController.class);
    private final ScraperService agentService;

    @PostMapping("/ask")
    public String ask(@RequestBody String query) {
        logger.info("Requête reçue dans le contrôleur: {}", query);
        return agentService.processQuery(query);
    }
}