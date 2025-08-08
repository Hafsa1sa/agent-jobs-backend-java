package org.example.jobs_agent.controller;

import lombok.RequiredArgsConstructor;
import org.example.jobs_agent.model.FreelancerRequest;
import org.example.jobs_agent.model.FreelancerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final Function<FreelancerRequest, FreelancerResponse> getFreelancerOffers;

    @GetMapping("/test-tool")
    public FreelancerResponse testTool(@RequestParam(defaultValue = "3") int count) {
        FreelancerRequest request = new FreelancerRequest(count);
        return getFreelancerOffers.apply(request);
    }
}
