package org.example.jobs_agent.config;


import org.example.jobs_agent.model.FreelancerRequest;
import org.example.jobs_agent.model.FreelancerResponse;
import org.example.jobs_agent.service.McpClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ToolConfigTest {

    @Test
    void testGetFreelancerOffersCallback() {
        // Mock du service
        McpClientService mockService = Mockito.mock(McpClientService.class);
        Mockito.when(mockService.getFreelancerProjects(3))
                .thenReturn(java.util.List.of());

        // Instancie le bean tool
        ToolConfig config = new ToolConfig();
        Function<FreelancerRequest, FreelancerResponse> callback =
                config.getFreelancerOffers(mockService);

        // Teste le callback
        FreelancerRequest req = new FreelancerRequest(3);
        FreelancerResponse resp = callback.apply(req);

        assertNotNull(resp);
        assertNotNull(resp.getProjects());
        System.out.println("Résultat callback : " + resp.getProjects());
    }
}

