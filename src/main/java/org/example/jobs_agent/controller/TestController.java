package org.example.jobs_agent.controller;

import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.dto.FreelancerRequestDTO;
import org.example.jobs_agent.dto.FreelancerResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Function<FreelancerRequestDTO, FreelancerResponseDTO> getFreelancerOffers;

    @GetMapping("/test-tool")
    public FreelancerResponseDTO testTool(@RequestParam(defaultValue = "3") int count) {
        FreelancerRequestDTO request = new FreelancerRequestDTO(count);
        return getFreelancerOffers.apply(request);
    }
}
