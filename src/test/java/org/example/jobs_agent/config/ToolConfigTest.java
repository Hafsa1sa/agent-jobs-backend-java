package org.example.jobs_agent.config;


import org.example.jobs_agent.clientApi.ClientApiFreelance;
import org.example.jobs_agent.dto.FreelancerRequestDTO;
import org.example.jobs_agent.dto.FreelancerResponseDTO;
import org.example.jobs_agent.tools.ScrapingTool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ToolConfigTest {

    @Test
    void testGetFreelancerOffersCallback() {
        // Mock du service
        ClientApiFreelance mockService = Mockito.mock(ClientApiFreelance.class);
        Mockito.when(mockService.getFreelancerProjects(3))
                .thenReturn(java.util.List.of());

        // Instancie le bean tool
        ScrapingTool config = new ScrapingTool();
        Function<FreelancerRequestDTO, FreelancerResponseDTO> callback =
                config.getFreelancerOffers(mockService);

        // Teste le callback
        FreelancerRequestDTO req = new FreelancerRequestDTO(3);
        FreelancerResponseDTO resp = callback.apply(req);

        assertNotNull(resp);
        assertNotNull(resp.getProjects());
        System.out.println("Résultat callback : " + resp.getProjects());
    }
}

